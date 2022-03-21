package Main;

import java.util.Map;

public interface InterfacciaLogin {

    void setMetodoAccesso(InterfacciaLogin login);

    void login(Map<String, Utente> elencoUtenti, String identificatore, String password) throws Exception;
}
