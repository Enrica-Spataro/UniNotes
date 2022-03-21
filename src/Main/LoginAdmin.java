package Main;

import java.util.Map;

public class LoginAdmin implements  InterfacciaLogin{
InterfacciaLogin login;

    @Override
    public void setMetodoAccesso(InterfacciaLogin login) {
        this.login=login;
    }

    @Override
    public void login(Map<String,Utente> elencoUtenti, String identificatore, String password) throws Exception {
        if(identificatore.contains("@")){
        try {
            if(identificatore.equals("uninotes2022@gmail.com")) {
                if (password.equals("topolino")) {
                    UniNotes uniNotes = UniNotes.getInstance();
                    uniNotes.menuAmministratore();
                }else{System.out.println("Password non valida");
                    throw new Exception("Password errata");}
            }else{System.out.println("Email non valida");
            throw new Exception("Email errata");}
    }catch (Exception ignored){}
    }else login.login(elencoUtenti,identificatore,password);
    }
}
