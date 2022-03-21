import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailApprovazione implements FormatoEmail{



    @Override
    public void inviaEmail(String utente, String testo) throws MessagingException {
       String messaggio="Ciao,\n l'amministratore ha elaborato il tuo appunto. \n Il tuo appunto è:  "+testo+ "\n\n\n Grazie e buon studio!";
       String oggetto = "UniNotes: risultato approvazione appunto" ;
       Thread myThread = new MailThread(utente,oggetto,messaggio);
       myThread.start();
    }
}
