package Test;

import Main.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class testGestionePagamento {

    @Test
    public void testPagaConPunti() throws Exception {
        GestionePagamento g = new GestionePagamento();
        UniNotes uniNotes= UniNotes.getInstance();
        Utente u = uniNotes.elencoUtenti.get("darsap");
        int punti = u.getPunti();
        g.riscattaAppunto(u);
        g.setModalitaPagamento(new RiscattaConPunti());
        g.paga(u, uniNotes.elencoCarteDiCredito, uniNotes.elencoUtenti);
        assertEquals(punti-5, u.getPunti());
    }
    @Test
    public void testPagaConCarta() throws Exception {
        GestionePagamento g = new GestionePagamento();
        UniNotes uniNotes= UniNotes.getInstance();
        Utente u = uniNotes.elencoUtenti.get("darsap");
        CartaDiCredito c = uniNotes.elencoCarteDiCredito.get(u.getCartaDiCredito());
        c.setScadenza("12/01/2019");
        try {
            g.setModalitaPagamento(new PagaConCarta());
            g.paga(u, uniNotes.elencoCarteDiCredito, uniNotes.elencoUtenti);
            fail("Expected exception");
        }catch (Exception e){
            assertEquals(e.getMessage(),"Carta non valida");
        }
    }
}
