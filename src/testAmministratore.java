import org.jetbrains.annotations.NotNull;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.mail.MessagingException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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
