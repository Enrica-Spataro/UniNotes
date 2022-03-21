package Main;

import javax.mail.MessagingException;

public class EmailRecensione implements FormatoEmail{

    @Override
    public void inviaEmail(String utente, String testo) throws MessagingException {
        String messaggio="Ciao,\n uno dei tuoi appunti è stato recensito! Il tuo punteggio utente adesso è: "+testo+ "\n\nGrazie e buon studio!";
        String oggetto = "UniNotes: nuovo punteggio utente";
        Thread myThread = new MailThread(utente,oggetto,messaggio);
        myThread.start();

    }
}
