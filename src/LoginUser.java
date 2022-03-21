import java.util.Map;
import java.util.Objects;

public class LoginUser implements InterfacciaLogin{
    private InterfacciaLogin login;

    @Override
    public void setMetodoAccesso(InterfacciaLogin login) {
        this.login=login;
    }

    @Override
    public void login(Map<String, Utente> elencoUtenti,String identificatore, String password) throws Exception {
    UniNotes uniNotes=UniNotes.getInstance();
        Utente u = elencoUtenti.get(identificatore);
        try {
            if (u != null) {
                if (Objects.equals(password, u.getPassword())) {
                    System.out.println("Login effettuato con successo");
                    uniNotes.setUtenteCorrente(u);
                    uniNotes.menuUtente();
                } else {
                    System.out.println("Password errata");
                    throw new Exception("Password errata");
                }
            } else {
                System.out.println("Nickname errato");
                throw new Exception("Identificatore errato");
            }
        }catch (Exception ignored) {
        }

    }

}
