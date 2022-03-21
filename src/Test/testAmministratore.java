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
        Amministratore amministratore = Amministratore.getInstance();
        UniNotes uniNotes = UniNotes.getInstance();
        Utente u = uniNotes.elencoUtenti.get("iscan");
        int punti = u.getPunti();

        amministratore.aggiornaPuntiUtente(u, uniNotes.elencoUtenti);

        assertEquals(punti + 2, u.getPunti());
        // mi aspetto che il test commentato fallisca
        // assertEquals(punti, u.getPunti());
    }



}
