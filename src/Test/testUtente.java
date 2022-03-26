package Test;

import Main.*;
import org.junit.Test;

import javax.mail.MessagingException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class testUtente {


@Test
    public void testInserisciAppunto() throws Exception {
    //verifica che una volta creato un nuovo appunto, quest’ultimo
    //sia presente nell’elenco degli appunti in sospeso

    Materia m = null;
    UniNotes uniNotes = UniNotes.getInstance();
    Facolta f = uniNotes.elencoFacolta.get(12);
    m = f.trovaMateria(f, "materia", "docente");
    f.getListaMaterie().add(m);
    String i=uniNotes.getRandomString();
    Appunto appuntoCorrente = new Appunto(i, "linkAppunto", "titoloArgomento", "darsap");
    try {
        m.getElencoAppuntiInSospeso().put(i, appuntoCorrente);
        assertNotNull(m.getElencoAppuntiInSospeso().get(i));
    } catch (Exception e) {
        fail("Unexpected exception");
    }
}
    @Test
    public void testAcquistaAppunto() throws Exception {
        //verifica che una volta acquistato una appunto
        // viene create la nuova istanza di acquisto

    UniNotes uniNotes = UniNotes.getInstance();
        int scelta = -1;
        String nomeMateria;
        Utente u = null;

        Materia mat = null;
        Acquisto a = null;
        Facolta f = uniNotes.elencoFacolta.get(1);
        Appunto appunto = new Appunto("AAAA","link","titolo","autore");
        GestionePagamento g = new GestionePagamento();
        if (g.riscattaAppunto(u = uniNotes.elencoUtenti.get("enri"))) {
            g.setModalitaPagamento(new RiscattaConPunti());
            g.paga(u, uniNotes.elencoCarteDiCredito, uniNotes.elencoUtenti);
            Thread.sleep(5000);
            Amministratore admin = Amministratore.getInstance();
            admin.confermaAcquisto(appunto, u);
            System.out.println("\n\n\nControlla la tua casella di posta! Hai ricevuto il link richiesto!");
            a = new Acquisto(appunto.getCodiceAppunto(), "enri");
        } else {
            g.setModalitaPagamento(new PagaConCarta());
            g.paga(u, uniNotes.elencoCarteDiCredito, uniNotes.elencoUtenti);
            Thread.sleep(5000);
            Amministratore admin = Amministratore.getInstance();
            admin.confermaAcquisto(appunto, u);
            System.out.println("\n\n\nControlla la tua casella di posta! Hai ricevuto il link richiesto!");
            a = new Acquisto(appunto.getCodiceAppunto(), "enri");
        }
        assertNotNull(a);
    }

    @Test
    public void testInserisciRecensione() throws Exception{
     //   verifica che inserita una recensione,
        //   venga create l’istanza di recensione
    UniNotes uniNotes = UniNotes.getInstance();
       Utente u = uniNotes.elencoUtenti.get("darsap");
        System.out.println("__ SEZIONE RECENSIONI __");
        Materia m = null;
        Facolta f = uniNotes.elencoFacolta.get(1);
        f.getListaMaterie().add(m=new Materia("prova", "prova"));
        m.getElencoAppuntiApprovati().put("AAAA",new Appunto("AAAA","www.prova.it","prova","enri"));
        Acquisto acquisto= new Acquisto("AAAA",u.getNickname());
        uniNotes.listaAcquisti.add(acquisto);
        LinkedList<Appunto> listaAppunti = new LinkedList<Appunto>();
        for (Acquisto a : uniNotes.listaAcquisti) {
            if (Objects.equals(a.getNickName(), u.getNickname())) {
                for (Map.Entry<Integer, Facolta> facoltaEntry : uniNotes.elencoFacolta.entrySet()) {
                    if (facoltaEntry.getValue().getListaMaterie().size() != 0) {
                        LinkedList<Materia> listaMaterie = facoltaEntry.getValue().getListaMaterie();
                        for (Materia ma : listaMaterie) {
                            listaAppunti.add(ma.getElencoAppuntiApprovati().get(a.getCodiceAppunto()));
                        }
                    }
                }
            }
        }
        Appunto ap = listaAppunti.getFirst();
        ap.getListaRecensioni().add(new Recensione(u.getNickname(), 5));
        ap.setNumeroRecensioni(ap.getNumeroRecensioni() + 1);
        listaAppunti.remove(ap);
        System.out.println("\n\n RECENSIONE REGISTRATA! \n\n");
        assertNotNull(ap.getListaRecensioni().get(0));
    }

}
