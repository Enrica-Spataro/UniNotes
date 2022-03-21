package Test;

import Main.Appunto;
import Main.Facolta;
import Main.Materia;
import org.junit.Test;

import static org.junit.Assert.*;

public class testMateria {


    @Test
    public void testSelezionaAppunto(){
        Facolta f = new Facolta(12,"facolta di prova");
        Materia m = null;
        Appunto a=null;
        f.getListaMaterie().add(m=new Materia("prova","doc"));
        m.getElencoAppuntiApprovati().put("AAAA",a=new Appunto("AAAA","www.prova.it","Prova prova","enri"));
        assertEquals(m.selezionaAppunto(m,"AAAA"),a);
    }

}
