package Test;

import Main.InterfacciaLogin;
import Main.LoginAdmin;
import Main.LoginUser;
import Main.UniNotes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class testLogin {

    @Test
    public void testLoginAdminPassword(){
        UniNotes uniNotes= UniNotes.getInstance();
        InterfacciaLogin loginAdmin = new LoginAdmin();
        InterfacciaLogin loginUser = new LoginUser();
        try {
            loginAdmin.setMetodoAccesso(loginUser);
            loginAdmin.login(uniNotes.elencoUtenti,"uninotes2022@gmail.com","password");
        }catch (Exception e){
            assertEquals("Password errata",e.getMessage());
        }
    }


    @Test
    public void testLoginAdminEmail(){
        UniNotes uniNotes= UniNotes.getInstance();
        InterfacciaLogin loginAdmin = new LoginAdmin();
        InterfacciaLogin loginUser = new LoginUser();
        try {
            loginAdmin.setMetodoAccesso(loginUser);
            loginAdmin.login(uniNotes.elencoUtenti,"uninotes2022@gmail.com","password");
        }catch (Exception e){
            assertEquals("Email errata",e.getMessage());
        }
    }
    @Test
    public void testLoginUserPassword(){
        UniNotes uniNotes= UniNotes.getInstance();
        InterfacciaLogin loginAdmin = new LoginAdmin();
        InterfacciaLogin loginUser = new LoginUser();
        try {
            loginAdmin.setMetodoAccesso(loginUser);
            loginAdmin.login(uniNotes.elencoUtenti,"darsap","password");
        }catch (Exception e){
            assertEquals("Password errata",e.getMessage());
        }
    }
    @Test
    public void testLoginUserNickname(){
        UniNotes uniNotes= UniNotes.getInstance();
        InterfacciaLogin loginAdmin = new LoginAdmin();
        InterfacciaLogin loginUser = new LoginUser();
        try {
            loginAdmin.setMetodoAccesso(loginUser);
            loginAdmin.login(uniNotes.elencoUtenti,"identificatore","password");
        }catch (Exception e){
            assertEquals("Identificatore errato",e.getMessage());
        }
    }



}
