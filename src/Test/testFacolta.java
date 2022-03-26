package Test;

import Main.Appunto;
import Main.Facolta;
import Main.Materia;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class testFacolta {

    @Test
    public void testTrovaMateria(){
    //verifica che una volta invocato il metodo TrovaMateria la materia ritornata
        // non sia “NULL” (se la materia non esiste viene creata)
        Facolta f= new Facolta(23,"prova");
        try{
            assertNotNull(f.trovaMateria(f,"Chimica","Sapienza"));
        }catch (Exception e){
            fail("Unexpected error: Errore nella creazione della materia");
        }
    }

    @Test
    public void testSelezionaMateria() throws IOException {
        //verifica che se sono inseriti appunti, la lista di appunti ritornata non sia “NULL”
        Facolta f = new Facolta(12,"facolta di prova");
        Materia m = null;
        f.getListaMaterie().add(m=new Materia("prova","doc"));
        m.getElencoAppuntiInSospeso().put("AAAA",new Appunto("AAAA","www.prova.it","Prova prova", "enri"));
        assertNotNull(f.selezionaMateria(f,"prova"));
    }

}
