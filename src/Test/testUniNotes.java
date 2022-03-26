package Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


import Main.*;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class  testUniNotes {
    static UniNotes uniNotes;
    private Map<String, Utente> elencoUtenti;


    @BeforeClass
    public static void initTest()  {
        uniNotes = UniNotes.getInstance();
    }


    @Test
    public void testLoadUtenti() {
    //verifica che una volta caricato dal file (non vuoto) l’elenco degli utenti
        // le istanze di utenti non risultino “NULL”
        try{
            String file = "C:\\Users\\Enrica Spataro\\Dropbox\\Magistrale\\Progetto Tomarchio\\Elaborazione\\UniNotes-IT4\\src\\Main\\utenti.txt";
            BufferedReader fp = new BufferedReader(new FileReader(file));
            for (String nickname = fp.readLine(); nickname != null; nickname = fp.readLine()) {
                String nome = fp.readLine();
                String cognome = fp.readLine();
                String email = fp.readLine();
                String password = fp.readLine();
                String cartaDiCredito = fp.readLine();
                int punti = Integer.parseInt(fp.readLine());
                Utente u = new Utente(nickname, nome, cognome, email, password, cartaDiCredito, punti);
                assertNotNull(u);
            }}catch (IOException e){
            fail("Unexpected exception!");
        }
    }

    @Test
    public void testLoadFacolta(){
        //verifica che una volta caricato dal file (non vuoto) l’elenco delle facoltà
        // le istanze di facoltà non risultino “NULL”
        try {

            String file = "C:\\Users\\Enrica Spataro\\Dropbox\\Magistrale\\Progetto Tomarchio\\Elaborazione\\UniNotes-IT4\\src\\Main\\elencoFacolta.txt";
            BufferedReader fp = new BufferedReader(new FileReader(file));

            for (int codiceFacolta = Integer.parseInt(fp.readLine()); codiceFacolta != 0; codiceFacolta = Integer.parseInt(fp.readLine())) {
                String nomeFacolta = fp.readLine();
                Facolta f = new Facolta(codiceFacolta, nomeFacolta);
                assertNotNull(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMateriePerFacolta() throws Exception {
        //verifica che una volta inserita una nuova materia per una determinate facoltà
        //l’elenco delle materie ritornate dal metodo MateriePerFacolta non risulti “NULL”
        UniNotes uniNotes=UniNotes.getInstance();
        Facolta f = uniNotes.elencoFacolta.get(1);
        f.getListaMaterie().add(new Materia("prova","prova"));
        assertNotNull(uniNotes.materiePerFacolta(1));
    }

    @Test
    public void testSetUtenteCorrente(){
        uniNotes.setUtenteCorrente(uniNotes.elencoUtenti.get("darsap"));
        assertNotNull(uniNotes.utenteCorrente);
    }

    @Test
    public void testLoadCarta() {
        //verifica che una volta caricato dal file (non vuoto) l’elenco delle carte di credito
        // le istanze di CartaDiCredito non risultino “NULL”
        try {
            String file = "C:\\Users\\Enrica Spataro\\Dropbox\\Magistrale\\Progetto Tomarchio\\Elaborazione\\UniNotes-IT4\\src\\Main\\carteDiCredito.txt";
            BufferedReader fp = new BufferedReader(new FileReader(file));

            for (String codiceCarta = fp.readLine(); codiceCarta!= null ; codiceCarta = fp.readLine()) {
                String scadenza = fp.readLine();
                CartaDiCredito cdc = new CartaDiCredito(codiceCarta, scadenza);
                assertNotNull(cdc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testRegistrazione() throws IOException {
        //verifica che una volta inseriti i dati per la registrazione:
        //-	Se la carta non è valida si generi l’eccezione carta non valida
        //-	Se tutti i dati sono corretti viene aggiunto correttamente all’elenco utenti l’utente registrato
        UniNotes uniNotes= UniNotes.getInstance();
        try {
            uniNotes.registrazione("ciao", "ciao", "ciao", "ciao", "ciao", "ciao", "12/03/2022");
        }catch (Exception e)
        {
            assertEquals("carta non valida", e.getMessage());
        }
        uniNotes.registrazione("ciao", "ciao", "ciao", "ciao", "ciao", "ciao-ciao-ciao-ciao", "12/04/2022");
        assertNotNull(uniNotes.elencoUtenti.get("ciao"));

    }

}


