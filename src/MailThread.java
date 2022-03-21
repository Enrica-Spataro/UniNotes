import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailThread extends Thread{
    private final String autore;
    private final String oggetto;
    private final String messaggio;

    public MailThread(String autore, String oggetto, String messaggio){
        this.autore=autore;
        this.oggetto=oggetto;
        this.messaggio=messaggio;
    }

    @Override
    public void run() {
        String username = "uninotes2022@gmail.com";
        String password = "ciao!!!123";

        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.from", "uninotes2022@gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        //props.setProperty("mail.debug", "true");

        Session session = Session.getInstance(props, null);
        MimeMessage msg = new MimeMessage(session);

        try {
            msg.setRecipients(Message.RecipientType.TO, autore);
            msg.setSubject(oggetto);
            msg.setText(messaggio);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        Transport transport = null;
        try {
            transport = session.getTransport("smtp");
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }

        try {

            transport.connect(username, password);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
