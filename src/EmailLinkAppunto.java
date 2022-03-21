import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailLinkAppunto implements FormatoEmail{

    @Override
    public void inviaEmail(String utente, String testo) throws MessagingException {
        String messaggio="Ecco il link dell'appunto da te acquistato\n"+testo+"\n\n\nGrazie e buon studio!";
        String oggetto = "UniNotes: link appunto acquistato";
        Thread myThread = new MailThread(utente,oggetto,messaggio);
        myThread.start();

    }
}
