package Test;

import Main.*;
import org.junit.Test;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

public class testUtente {


@Test
    public void testInserisciAppunto() throws Exception {
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
/*
    @Test
    public void testObserver() throws InterruptedException {
    UniNotes uniNotes = UniNotes.getInstance();
    Appunto a = new Appunto("ASDF","prova.it","prova prova", "enri");
    a.getListaRecensioni().add(new Recensione("enri",4));
    a.setNumeroRecensioni(1);
    Utente u= uniNotes.elencoUtenti.get("enri");
    assertNotNull(u);
    assertEquals(4,u.getValutazione());
    }
*/
}
