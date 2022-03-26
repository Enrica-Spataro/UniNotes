package Test;

import Main.Amministratore;
import Main.UniNotes;
import Main.Utente;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class testAmministratore {

    @BeforeClass
    public static void initTest()  {
        Amministratore amministratore = Amministratore.getInstance();
    }

    @Test
    public void testAggiornaPuntiUtente() throws Exception {
        //verifica che, una volta invocato il metoto AggiornaPuntiUtente
        //i punti dell’utente subiscono effettivamente una modifica
        Amministratore amministratore = Amministratore.getInstance();
        UniNotes uniNotes = UniNotes.getInstance();
        Utente u = uniNotes.elencoUtenti.get("darsap");
        int punti = u.getPunti();

        amministratore.aggiornaPuntiUtente(u, uniNotes.elencoUtenti);

        assertEquals(punti + 2, u.getPunti());
        // mi aspetto che il test commentato fallisca
        // assertEquals(punti, u.getPunti());
    }

}
