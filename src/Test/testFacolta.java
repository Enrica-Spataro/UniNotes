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

        Facolta f= new Facolta(23,"prova");
        try{
            assertNotNull(f.trovaMateria(f,"Chimica","Sapienza"));
        }catch (Exception e){
            fail("Unexpected error: Errore nella creazione della materia");
        }
    }

    @Test
    public void testSelezionaMateria() throws IOException {
        Facolta f = new Facolta(12,"facolta di prova");
        Materia m = null;
        f.getListaMaterie().add(m=new Materia("prova","doc"));
        m.getElencoAppuntiInSospeso().put("AAAA",new Appunto("AAAA","www.prova.it","Prova prova", "enri"));
        assertNotNull(f.selezionaMateria(f,"prova"));
    }

}
