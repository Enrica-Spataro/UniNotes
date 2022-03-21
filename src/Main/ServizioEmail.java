package Main;

import javax.mail.MessagingException;


public class ServizioEmail {

    FormatoEmail mode;
    public void setFormatoEmail(FormatoEmail fe){
        mode=fe;
    }

    public void invia(String utente,String testo) throws MessagingException {
        mode.inviaEmail(utente,testo);
    }

}
