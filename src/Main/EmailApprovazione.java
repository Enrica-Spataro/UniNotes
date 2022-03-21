package Main;

import javax.mail.MessagingException;

public class EmailApprovazione implements FormatoEmail{



    @Override
    public void inviaEmail(String utente, String testo) throws MessagingException {
       String messaggio="Ciao,\n l'amministratore ha elaborato il tuo appunto. \n Il tuo appunto è:  "+testo+ "\n\n\n Grazie e buon studio!";
       String oggetto = "UniNotes: risultato approvazione appunto" ;
       Thread myThread = new MailThread(utente,oggetto,messaggio);
       myThread.start();
    }
}
