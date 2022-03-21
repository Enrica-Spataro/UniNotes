import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailRegistrazione implements FormatoEmail {

    @Override
    public void inviaEmail(String utente, String testo) throws MessagingException {

        String messaggio="Ciao,\nGrazie per esserti unito a noi! Adesso puoi acquistare, recensire e caricare appunti sulla nostra applicazione!\n\n" +
                "Le tue credenziali di accesso sono: " + testo + "\n\n\n Grazie e buon studio!";
        String oggetto = "UniNotes: nuovo utente";
        Thread myThread = new MailThread(utente,oggetto,messaggio);
        myThread.start();

    }
    }

