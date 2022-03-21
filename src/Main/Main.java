package Main;

import java.io.*;

public class Main {

    private BufferedReader tastiera;

    public static void main(String[] args) throws Exception {
        BufferedReader tastiera = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Benvenuto su UniNotes");

        UniNotes uniNotes = UniNotes.getInstance();
        //uniNotes.loadUtenti();
        String identificatore;
        String password;
        InterfacciaLogin loginAdmin = new LoginAdmin();
        InterfacciaLogin loginUser = new LoginUser();
        int scelta = -1;

        do{
            try {
                System.out.println("\n\nInserisci la tua scelta:\n1) Login" +
                        " \n2) Registrati \n0) Chiudi applicazione UniNotes");
                scelta = Integer.parseInt(tastiera.readLine());
            } catch (IOException var3) {
                var3.printStackTrace();
            }


            switch (scelta){
                case 1:
                    loginAdmin.setMetodoAccesso(loginUser);
                    try {
                        System.out.println("Inserire nickname :\n");
                        identificatore = tastiera.readLine();
                        System.out.println("Inserire password :\n");
                        password = tastiera.readLine();
                        loginAdmin.login(uniNotes.elencoUtenti,identificatore,password);
                        if(identificatore==null || password ==null)
                            throw new Exception("Nickname o password null");

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    uniNotes.opzioneRegistrazione();
                    break;

            }}while(scelta !=0);

    }

    }



