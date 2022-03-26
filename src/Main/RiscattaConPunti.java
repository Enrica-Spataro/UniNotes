package Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class RiscattaConPunti implements ModalitaPagamento{
    @Override
    public void pagaAppunto(Utente u, Map<String,CartaDiCredito> elencoCarte, Map <String,Utente> elencoUtenti) throws IOException {
        u.setPunti(u.getPunti() - 5);
        elencoUtenti.replace(u.getNickname(), u);

        FileWriter file = new FileWriter("C:\\Users\\Enrica Spataro\\Dropbox\\Magistrale\\Progetto Tomarchio\\Elaborazione\\UniNotes - IT3\\src\\utenti.txt");
        BufferedWriter filebuf = new BufferedWriter(file);
        PrintWriter printout = new PrintWriter(filebuf);
        elencoUtenti.forEach((key, value) -> printout.println(key + "\n" + value.getNome()
                + "\n" + value.getCognome()
                + "\n" + value.getEmail()
                + "\n" + value.getPassword()
                + "\n" + value.getCartaDiCredito()
                + "\n" + value.getPunti()));

        printout.flush();
        printout.close();
        System.out.println("\n\nControlla la tua casella di posta! Hai ricevuto il link richiesto!\n\n");
    }
}
