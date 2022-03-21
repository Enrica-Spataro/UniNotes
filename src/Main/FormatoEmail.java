package Main;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface FormatoEmail {
    public void inviaEmail(String utente, String testo) throws MessagingException;
}
