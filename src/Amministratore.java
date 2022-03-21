


import org.jetbrains.annotations.NotNull;

import javax.mail.MessagingException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import static java.lang.Thread.sleep;

public class Amministratore {

    private static Amministratore amministratore;
    private String password;


    private Amministratore() {
        this.password = "topolino";
    }

    public static Amministratore getInstance() {
        if (amministratore == null)
            amministratore = new Amministratore();
        return amministratore;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void confermaAcquisto(@NotNull Appunto a, @NotNull Utente u) throws MessagingException {
        ServizioEmail mail =new ServizioEmail();
        mail.setFormatoEmail(new EmailLinkAppunto());
        mail.invia(u.getEmail(),a.getLinkAppunto());

    }

    public void confermaApprovazione(String approvato, String autore) throws MessagingException{
        ServizioEmail mail =new ServizioEmail();
        mail.setFormatoEmail(new EmailApprovazione());
        mail.invia(autore,approvato);
    }

    public void accetta(Appunto entry, @NotNull Utente u, @NotNull Materia m) throws MessagingException, IOException{
        m.getElencoAppuntiApprovati().put(entry.getCodiceAppunto(), entry);
        m.getElencoAppuntiInSospeso().remove(entry.getCodiceAppunto());
        confermaApprovazione("Approvato",u.getEmail());

    }

    public void rifiuta(@NotNull Appunto entry, @NotNull Utente u, @NotNull Materia m) throws MessagingException {
        m.getElencoAppuntiInSospeso().remove(entry.getCodiceAppunto());
        confermaApprovazione(" Non Approvato", u.getEmail());
    }

    public void aggiornaPuntiUtente(@NotNull Utente utenteCorrente, @NotNull Map<String,Utente> elencoUtenti) throws IOException {

        switch (utenteCorrente.getValutazione()) {
            case 0:utenteCorrente.setPunti(utenteCorrente.getPunti() + 2);
                break;
            case 1: utenteCorrente.setPunti(utenteCorrente.getPunti() + 1);
                break;
            case 2:utenteCorrente.setPunti(utenteCorrente.getPunti() + 3);
                break;
            case 3:utenteCorrente.setPunti(utenteCorrente.getPunti() + 5);
                break;
            case 4:utenteCorrente.setPunti(utenteCorrente.getPunti() + 8);
                break;
            case 5:utenteCorrente.setPunti(utenteCorrente.getPunti() + 12);
                break;
        }

        elencoUtenti.replace(utenteCorrente.getNickname(),utenteCorrente);

        FileWriter file=new FileWriter("C:\\Users\\Enrica Spataro\\Dropbox\\Magistrale\\Progetto Tomarchio\\Elaborazione\\UniNotes-IT4\\src\\utenti.txt");
        BufferedWriter filebuf= new BufferedWriter(file);
        PrintWriter printout=new PrintWriter(filebuf);
        elencoUtenti.forEach((key, value) -> printout.println(key + "\n" + value.getNome()
                + "\n" + value.getCognome()
                + "\n" + value.getEmail()
                + "\n" + value.getPassword()
                + "\n" + value.getCartaDiCredito()
                + "\n" + value.getPunti()));

        printout.flush();
        printout.close();

    }


    public void confermaRegistrazione(String email, String testo) throws MessagingException {
        ServizioEmail servizioEmail = new ServizioEmail();
        servizioEmail.setFormatoEmail(new EmailRegistrazione());
        servizioEmail.invia(email,testo);

    }
}
