package Main;

import org.jetbrains.annotations.NotNull;

import javax.mail.MessagingException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.Observer;
import java.util.Observable;

public class Utente implements Observer {
    private String nickname, nome, cognome, email, password, cartaDiCredito;
    private int punti;
    private final BufferedReader tastiera;
    private int valutazione;


    public Utente(String nickname, String nome, String cognome, String email, String password, String cartaDiCredito, int punti) {
        this.nickname = nickname;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.password = password;
        this.cartaDiCredito = cartaDiCredito;
        this.punti = punti;
        this.tastiera = new BufferedReader(new InputStreamReader(System.in));
        this.valutazione=0; //cambiare file e caricamento

    }

    public int getValutazione() {
        return valutazione;
    }

    public void setValutazione(int valutazione) {
        this.valutazione = valutazione;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCartaDiCredito() {
        return cartaDiCredito;
    }

    public void setCartaDiCredito(String cartaDiCredito) {
        this.cartaDiCredito = cartaDiCredito;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPunti() {
        return punti;
    }

    public void setPunti(int punti) {
        this.punti = punti;
    }

    public void inserisciAppunto(String linkAppunto, String titoloArgomento, Map<Integer, Facolta> elencoFacolta) throws Exception {
        int scelta = -1;
        String materia;
        String docente;
        Materia m = null;
        UniNotes uniNotes = UniNotes.getInstance();

        uniNotes.mostraFacolta();
        do {
            System.out.println("Scegli la facoltà: \n");
            scelta = Integer.parseInt(tastiera.readLine());
            if (scelta < 1 || scelta > 24) {
                System.out.println("Scelta non valida, inserire facoltà esistente");
            }
        } while (scelta < 1 || scelta > 24);

        Facolta f = elencoFacolta.get(scelta);

        System.out.println("Inserisci il nome della materia per intero: \n");
        materia = this.tastiera.readLine();
        System.out.println("Inserisci il cognome del docente della materia: \n");
        docente = this.tastiera.readLine();
        m = f.trovaMateria(f, materia, docente);

        f.getListaMaterie().add(m);
        String i=uniNotes.getRandomString();
        Appunto appuntoCorrente = new Appunto(i, linkAppunto, titoloArgomento, this.nickname);

        appuntoCorrente.addObserver(this);

        try {
            m.getElencoAppuntiInSospeso().put(i, appuntoCorrente);
            System.out.println("Il tuo appunto è stato preso in carico dal Sistema. ");
        } catch (Exception e) {
            throw new Exception("Errore nella creazione dell'appunto");
        }

    }

    public Acquisto acquistaAppunto(Map<Integer, Facolta> elencoFacolta, Map<String, CartaDiCredito> elencoCarteDiCredito, Map<String, Utente> elencoUtenti) throws Exception {
        UniNotes uniNotes = UniNotes.getInstance();
        int scelta = -1;
        int media = 0;
        String nomeMateria;
        uniNotes.mostraFacolta();
        Materia mat = null;
        LinkedList<Materia> listaMaterie;
        do {
            System.out.println("Scegli la facoltà: \n");
            scelta = Integer.parseInt(tastiera.readLine());
            if (scelta < 1 || scelta > 24) {
                System.out.println("Scelta non valida, inserire facoltà esistente");
            }
        } while (scelta < 1 || scelta > 24);
        listaMaterie = uniNotes.materiePerFacolta(scelta);
        Facolta f = elencoFacolta.get(scelta);
        System.out.println("\n----Materie disponibili---- \n");
        listaMaterie.forEach(System.out::println);
        System.out.println("Digita il nome completo della materia: \n");
        nomeMateria = this.tastiera.readLine();
        Map<String, Appunto> elencoAppunti = f.selezionaMateria(f, nomeMateria);
        if (elencoAppunti == null || elencoAppunti.size() == 0) {
            System.out.println("\n Non ci sono appunti approvati per questa materia");
            System.out.println("\nScegli una delle opzioni: \n1) acquista un appunto di un'altra facoltà\n" +
                    "2) torna al menù utente");
            scelta = Integer.parseInt(this.tastiera.readLine());
            if (scelta == 1) {
                this.acquistaAppunto(elencoFacolta, elencoCarteDiCredito, elencoUtenti);
            }
            else
                return null;
        } else {
            System.out.println("\nAppunti della materia selezionata: \n");
            elencoAppunti.forEach((key, value) -> System.out.println("\ncodice: " + key + " = " + value.getTitoloArgomento()+"\nL'appunto ha ricevuto " + value.getListaRecensioni().size() + " recensioni"));
            System.out.println("\nSeleziona appunto - inserisci il codice: \n");
            String codice = this.tastiera.readLine();

            for (Materia m : listaMaterie) {
                if (Objects.equals(m.getNomeMateria(), nomeMateria))
                    mat = m;
            }

            assert mat != null;
            Appunto appunto = mat.selezionaAppunto(mat, codice);
            if(appunto.getListaRecensioni().size()!=0) {
                for(Recensione r : appunto.getListaRecensioni())
                    media = media + r.getVoto();
                System.out.println("La media delle valutazioni del tuo appunto è: "+ media/appunto.getNumeroRecensioni());
                System.out.println("Procedere con l'acquisto? digita 1 per confermare");
                if(Integer.parseInt(tastiera.readLine())==1) {
                    GestionePagamento g = new GestionePagamento();
                    if (g.riscattaAppunto(this)) {
                        g.setModalitaPagamento(new RiscattaConPunti());
                        g.paga(this, elencoCarteDiCredito, elencoUtenti);
                    } else {
                        g.setModalitaPagamento(new PagaConCarta());
                        g.paga(this, elencoCarteDiCredito, elencoUtenti);
                    }

                    Amministratore admin = Amministratore.getInstance();
                    admin.confermaAcquisto(appunto, this);
                    System.out.println("\n\n\nControlla la tua casella di posta! Hai ricevuto il link richiesto!");
                    return new Acquisto(appunto.getCodiceAppunto(), this.nickname);
                }
                else {
                    System.out.println("Acquisto non completato");
                    return null;
                }
            }
            else {
                GestionePagamento g = new GestionePagamento();
                if (g.riscattaAppunto(this)) {
                    g.setModalitaPagamento(new RiscattaConPunti());
                    g.paga(this, elencoCarteDiCredito, elencoUtenti);
                } else {
                    g.setModalitaPagamento(new PagaConCarta());
                    g.paga(this, elencoCarteDiCredito, elencoUtenti);
                }

                Amministratore admin = Amministratore.getInstance();
                admin.confermaAcquisto(appunto, this);
                System.out.println("\n\n\nControlla la tua casella di posta! Hai ricevuto il link richiesto!");
                return new Acquisto(appunto.getCodiceAppunto(), this.nickname);
            }
            }
        return null;
    }

    public void inserisciRecensione(Map<Integer, Facolta> elencoFacolta, LinkedList<Acquisto> listaAcquisti, Map<String,Utente> elencoUtenti) throws Exception {
        System.out.println("__ SEZIONE RECENSIONI __");
        LinkedList<Appunto> listaAppuntiAcquistati = acquistiPerUtente(listaAcquisti, elencoFacolta);
        Appunto a = null;
        UniNotes uniNotes= UniNotes.getInstance();

        if(listaAppuntiAcquistati.size() != 0) {
            for (Appunto ap : listaAppuntiAcquistati)
                System.out.println("Codice:" + ap.getCodiceAppunto() + " Titolo " + ap.getTitoloArgomento());

            System.out.println("Inserisci il codice dell'appunto che vuoi recensire");
            String scelta = tastiera.readLine();
            for (Appunto app : listaAppuntiAcquistati)
                if (app.getCodiceAppunto().equals(scelta)) {
                    a = app;
                    for (Recensione r : app.getListaRecensioni())
                        if (Objects.equals(r.getNickName(), this.nickname)) {
                            System.out.println("Hai già aggiunto una recensione per questo appunto");
                            uniNotes.menuUtente();
                        }
                }
            int voto = inserisciVotazione();
            assert a != null;
            a.getListaRecensioni().add(new Recensione(this.nickname, voto));
            a.setNumeroRecensioni(a.getNumeroRecensioni() + 1);
            listaAppuntiAcquistati.remove(a);
            System.out.println("\n\n RECENSIONE REGISTRATA! \n\n");
            if (listaAppuntiAcquistati.size() != 0) {
                System.out.println("Vuoi recensire un altro appunto?\nPremi 1 per continuare");
                int scelta1 = Integer.parseInt(this.tastiera.readLine());
                if (scelta1 == 1)
                    inserisciRecensione(elencoFacolta, listaAcquisti, elencoUtenti);
            }
        }else{
            System.out.println("Non hai acquistato nulla");
            uniNotes.menuUtente();
        }

    }

    private int inserisciVotazione() throws IOException {
        System.out.println("Inserisci un voto da 1 a 5");
        int scelta = Integer.parseInt(this.tastiera.readLine());
        if(scelta<1 || scelta>5)
        {
            System.out.println("Scelta non valida");
            inserisciVotazione();
        }
        return scelta;
    }

    private LinkedList<Appunto> acquistiPerUtente(@NotNull LinkedList<Acquisto> listaAcquisti, Map<Integer, Facolta> elencoFacolta) throws Exception {
        LinkedList<Appunto> listaAppunti = new LinkedList<Appunto>();
        for (Acquisto a : listaAcquisti) {
            if (Objects.equals(a.getNickName(), this.nickname)) {
                for (Map.Entry<Integer, Facolta> facoltaEntry : elencoFacolta.entrySet()) {
                    if (facoltaEntry.getValue().getListaMaterie().size() != 0) {
                        LinkedList<Materia> listaMaterie = facoltaEntry.getValue().getListaMaterie();
                        for (Materia m : listaMaterie) {
                            listaAppunti.add(m.getElencoAppuntiApprovati().get(a.getCodiceAppunto()));
                        }
                    }
                }
            }
        }
        return listaAppunti;
    }

    @Override
    public void update(Observable obs, Object arg) {
        if(((Appunto) obs).getAutore().equals(this.nickname)) {
            if(this.valutazione==0)
                this.valutazione= ((Appunto) obs).getListaRecensioni().get(((Appunto) obs).getNumeroRecensioni() - 1).getVoto();
            else
                this.valutazione = (this.valutazione + ((Appunto) obs).getListaRecensioni().get(((Appunto) obs).getNumeroRecensioni()).getVoto())/2;
            System.out.println(this.valutazione);
            ServizioEmail mail = new ServizioEmail();
            try {
                mail.setFormatoEmail(new EmailRecensione());
                mail.invia(this.email,String.valueOf(this.valutazione));
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
}
