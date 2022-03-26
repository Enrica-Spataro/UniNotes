package Main;

import org.jetbrains.annotations.NotNull;

import javax.mail.MessagingException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class UniNotes {
    private static UniNotes uniNotes;
    private final BufferedReader tastiera;
    public Map <String, Utente> elencoUtenti;
    public final Map <Integer, Facolta> elencoFacolta;
    public final Map <String, CartaDiCredito> elencoCarteDiCredito;
    public Utente utenteCorrente;
    public final LinkedList<Acquisto> listaAcquisti;



    private UniNotes() {

        this.tastiera = new BufferedReader(new InputStreamReader(System.in));
        this.elencoUtenti = new HashMap<>();
        this.elencoFacolta = new HashMap<>();
        this.listaAcquisti = new LinkedList<>();
        this.elencoCarteDiCredito = new HashMap<>();
        loadUtenti();
        loadFacolta();
        loadCarteDiCredito();

    }

    public static UniNotes getInstance()  {
        if (uniNotes == null)
            uniNotes = new UniNotes();
        return uniNotes;
    }

    public Map<String, Utente> getElencoUtenti() {
        return elencoUtenti;
    }

    public void loadUtenti() {

        try {
            String file = "C:\\Users\\Enrica Spataro\\Dropbox\\Magistrale\\Progetto Tomarchio\\Elaborazione\\UniNotes-IT4\\src\\Main\\utenti.txt";
            BufferedReader fp = new BufferedReader(new FileReader(file));

            for (String nickname = fp.readLine(); nickname != null; nickname = fp.readLine()) {
                String nome = fp.readLine();
                String cognome = fp.readLine();
                String email = fp.readLine();
                String password = fp.readLine();
                String cartaDiCredito = fp.readLine();
                int punti = Integer.parseInt(fp.readLine());
                Utente u = new Utente(nickname, nome, cognome, email, password, cartaDiCredito
                        , punti);
                this.elencoUtenti.put(nickname, u);
                if(this.elencoUtenti ==null)
                    throw new Exception("Errore caricamento utenti");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setUtenteCorrente(Utente u){
        this.utenteCorrente=u;
    }
    public void menuUtente () throws Exception {
        int scelta = -1;
        do {
            try {
                System.out.println("****MENU UTENTE****\n" +
                        "\nInserisci la tua scelta:" +
                        "\n1) Inserisci un nuovo appunto;" +
                        "\n2) Acquista appunto;" +
                        "\n3) Inserisci recensione;"+
                        "\n0) Esci");
                scelta = Integer.parseInt(tastiera.readLine());
                if (scelta < 0 || scelta > 3) {
                    System.out.println("Scelta non valida");
                    throw new Exception("Scelta non valida");
                }
            } catch (Exception ignored) {
            }
            switch (scelta) {
                case 1:System.out.println("Inserisci il link dell'appunto: \n");
                    String linkAppunto = this.tastiera.readLine();
                    System.out.println("Inserisci il titolo dell'argomento: \n");
                    String argomento = this.tastiera.readLine();
                    if(linkAppunto == null || argomento == null)
                        throw new Exception("Paramentri null");
                    utenteCorrente.inserisciAppunto(linkAppunto,argomento,this.elencoFacolta);
                    break;
                case 2: Acquisto a = utenteCorrente.acquistaAppunto(elencoFacolta,elencoCarteDiCredito,elencoUtenti);
                if(a!=null)
                    listaAcquisti.add(a);
                    break;
                case 3: utenteCorrente.inserisciRecensione(elencoFacolta,listaAcquisti,elencoUtenti);
                    break;
            }
        } while (scelta != 0);
    }

    public void loadFacolta() {
        try {

            String file = "C:\\Users\\Enrica Spataro\\Dropbox\\Magistrale\\Progetto Tomarchio\\Elaborazione\\UniNotes-IT4\\src\\Main\\elencoFacolta.txt";
            BufferedReader fp = new BufferedReader(new FileReader(file));

            for (int codiceFacolta = Integer.parseInt(fp.readLine()); codiceFacolta != 0; codiceFacolta = Integer.parseInt(fp.readLine())) {
                String nomeFacolta = fp.readLine();
                Facolta f = new Facolta(codiceFacolta, nomeFacolta);
                this.elencoFacolta.put(codiceFacolta, f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostraFacolta(){
        this.elencoFacolta.forEach((key, value) -> System.out.println(key + " = " + value.getNomeFacoltà()));
    }

    public LinkedList<Materia> materiePerFacolta(Integer codiceFacolta) throws Exception {
        LinkedList<Materia> listaMaterie;
        int scelta = -1;
        Facolta f = this.elencoFacolta.get(codiceFacolta);
        listaMaterie = f.getListaMaterie();
        if (listaMaterie.size() == 0) {
            System.out.println("\n-----Non sono presenti appunti per questa facolta-----\n");
            System.out.println("\nScegli una delle opzioni: \n1) acquista un appunto di un'altra facoltà\n" +
                    "2) torna al menù utente");
            scelta = Integer.parseInt(this.tastiera.readLine());
            if (scelta == 1)
                utenteCorrente.acquistaAppunto(elencoFacolta,elencoCarteDiCredito,elencoUtenti);
            else if (scelta==2)
                menuUtente();
        }
        return listaMaterie;
    }


    public String getRandomString(){
        String theAlphaNumericS;
        StringBuilder builder;

        theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        builder = new StringBuilder(4);

        for (int m = 0; m < 4; m++) {

            int myindex
                    = (int)(theAlphaNumericS.length()
                    * Math.random());

            builder.append(theAlphaNumericS
                    .charAt(myindex));
        }

        return builder.toString();
    }


    public void menuAmministratore() throws IOException, MessagingException{
        int scelta = -1;
        do {
            try {
                System.out.println("****MENU AMMINISTRATORE****\n" +
                        "\nInserisci la tua scelta:" +
                        "\n1) Approva appunti;" +
                        "\n0) Esci");
                scelta = Integer.parseInt(tastiera.readLine());
                if (scelta < 0 || scelta > 1) {
                    System.out.println("Scelta non valida");
                    throw new Exception("Scelta non valida");
                }
            } catch (Exception ignored) {
            }
            if (scelta == 1) {
                approvaAppunti();
            }
        } while (scelta != 0);
    }

    public void approvaAppunti() throws IOException, MessagingException{
        for (Map.Entry<Integer, Facolta> facoltaEntry : elencoFacolta.entrySet()) {
            if (facoltaEntry.getValue().getListaMaterie().size() != 0) {
                LinkedList<Materia> listaMaterie = facoltaEntry.getValue().getListaMaterie();
                for (Materia m : listaMaterie) {
                    if (m.getElencoAppuntiInSospeso().size() != 0) {
                        approvaAppuntiInSospeso(m);
                    }
                }
            }
        }
        System.out.println("Non ci sono più appunti da approvare");}

    private void approvaAppuntiInSospeso(@NotNull Materia m ) throws IOException, MessagingException{
        Amministratore a= Amministratore.getInstance();
        int i = -1;
        for (var entry : m.getElencoAppuntiInSospeso().entrySet()) {
            System.out.println("\nTitolo argomento    "+ entry.getValue().getTitoloArgomento()+
                    "\nLink Appunto    "+ entry.getValue().getLinkAppunto()
            );
            System.out.println("\n\nDigita 1 per approvare un appunto, 0 per disapprovare");
            i= Integer.parseInt(this.tastiera.readLine());
            Utente u = elencoUtenti.get(entry.getValue().getAutore());
            if(i==1){
                a.accetta(entry.getValue(),u,m);
                a.aggiornaPuntiUtente(u,this.elencoUtenti);}
            else
                a.rifiuta(entry.getValue(),u,m);
        }
    }

    public void loadCarteDiCredito(){
        try {

            String file = "C:\\Users\\Enrica Spataro\\Dropbox\\Magistrale\\Progetto Tomarchio\\Elaborazione\\UniNotes-IT4\\src\\Main\\carteDiCredito.txt";
            BufferedReader fp = new BufferedReader(new FileReader(file));

            for (String codiceCarta = fp.readLine(); codiceCarta!= null ; codiceCarta = fp.readLine()) {
                String scadenza = fp.readLine();
                CartaDiCredito cdc = new CartaDiCredito(codiceCarta, scadenza);
                this.elencoCarteDiCredito.put(codiceCarta , cdc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void opzioneRegistrazione() throws Exception {
        System.out.println("Inserisci il nickName che vuoi utilizzare (non sono ammessi caratteri speciali)");
        String nickName = tastiera.readLine();
        if(elencoUtenti.get(nickName)!=null) {
            System.out.println("NickName già in uso, inserisci un altro nickname");
            opzioneRegistrazione();
        }
        System.out.println("Inserisci nome");
        String nome = tastiera.readLine();
        System.out.println("Inserisci cognome");
        String cognome = tastiera.readLine();
        System.out.println("Inserisci email");
        String email = tastiera.readLine();
        System.out.println("Inserisci password");
        String password = tastiera.readLine();
        System.out.println("Inserisci Carta di credito");
        String carta = tastiera.readLine();
        System.out.println("Inserisci scadenza carta di credito");
        String scadenza = tastiera.readLine();

        if(!verificaValiditaCarta(carta,scadenza)) {
            System.out.println("Carta di credito non valida");
            System.out.println("Inserisci Carta di credito");
            String carta1 = tastiera.readLine();
            System.out.println("Inserisci scadenza carta di credito");
            String scadenza1 = tastiera.readLine();
            if(!verificaValiditaCarta(carta1,scadenza1)) {
                System.out.println("Siamo spiacenti, anche questa carta non è valida. \nREINSERIRE TUTTI I DATI");
            }else {
                registrazione(nickName, nome, cognome, email, password, carta1, scadenza1);
                System.out.println("***Grazie per esserti unito a noi!***\n\n\n");
                ServizioEmail servizioEmail = new ServizioEmail();
                servizioEmail.setFormatoEmail(new EmailRegistrazione());
                String testo = "NickName:  " + nickName + "\nPassword"+ password;
                servizioEmail.invia(email,testo);
                menuUtente();
            }

        }
        else {
            registrazione(nickName, nome, cognome, email, password, carta, scadenza);
            System.out.println("\n\n***Grazie per esserti unito a noi!***\n\n\n");
            String testo = "NickName:  " + nickName + "\nPassword:  "+ password;
            Amministratore admin = Amministratore.getInstance();
            admin.confermaRegistrazione(email,testo);
            menuUtente();
        }
    }

    public void registrazione(String nickName, String nome, String cognome, String email, String password, String carta, String scadenza) throws IOException {
        utenteCorrente=new Utente(nickName,nome,cognome,email,password,carta,0);
        elencoUtenti.put(nickName, utenteCorrente);
        FileWriter file=new FileWriter("C:\\Users\\Enrica Spataro\\Dropbox\\Magistrale\\Progetto Tomarchio\\Elaborazione\\UniNotes-IT4\\src\\Main\\utenti.txt");
        BufferedWriter filebuf= new BufferedWriter(file);
        PrintWriter printout=new PrintWriter(filebuf);
        elencoUtenti.forEach((key, value) -> printout.println(key + "\n" + value.getNome()
                + "\n" + value.getCognome()
                + "\n" + value.getEmail()
                + "\n" + value.getPassword()
                + "\n" + value.getCartaDiCredito()
                + "\n" + value.getPunti()));

        printout.flush();
        printout.close();

        elencoCarteDiCredito.put(carta,new CartaDiCredito(carta,scadenza));
        FileWriter file1=new FileWriter("C:\\Users\\Enrica Spataro\\Dropbox\\Magistrale\\Progetto Tomarchio\\Elaborazione\\UniNotes-IT4\\src\\Main\\carteDiCredito.txt");
        BufferedWriter filebuf1= new BufferedWriter(file1);
        PrintWriter printout1=new PrintWriter(filebuf1);
        elencoCarteDiCredito.forEach((key, value) -> printout1.println(key + "\n" + value.getScadenza()));
        printout1.flush();
        printout1.close();
    }

    private boolean verificaValiditaCarta(String carta, String scadenza) throws Exception {
        Date scad = new SimpleDateFormat("dd/MM/yyyy").parse(scadenza);
        long millisecond = System.currentTimeMillis();
        Date data = new Date(millisecond);
        try{
        if(scad.compareTo(data) <= 0 && new CartaDiCredito(carta, scadenza).getCodiceCarta().length() <= 19) {
            throw new Exception("carta non valida");
        }}catch (Exception ignored){
            return false;
        }
        return true;
    }
}



