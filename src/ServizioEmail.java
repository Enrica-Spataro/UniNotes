
import java.util.Properties;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.*;
import javax.swing.*;


public class ServizioEmail {

    FormatoEmail mode;
    public void setFormatoEmail(FormatoEmail fe){
        mode=fe;
    }

    public void invia(String utente,String testo) throws MessagingException {
        mode.inviaEmail(utente,testo);
    }

}
