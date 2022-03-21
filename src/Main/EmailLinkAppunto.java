package Main;

import javax.mail.MessagingException;

public class EmailLinkAppunto implements FormatoEmail{

    @Override
    public void inviaEmail(String utente, String testo) throws MessagingException {
        String messaggio="Ecco il link dell'appunto da te acquistato\n"+testo+"\n\n\nGrazie e buon studio!";
        String oggetto = "UniNotes: link appunto acquistato";
        Thread myThread = new MailThread(utente,oggetto,messaggio);
        myThread.start();

    }
}
