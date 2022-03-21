import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;

public class GestionePagamento {
    ModalitaPagamento mode;

    public boolean riscattaAppunto(@NotNull Utente u){
        return u.getPunti() > 5;
    }
    public void setModalitaPagamento(ModalitaPagamento mp){
        mode=mp;
    }
    public void paga(Utente u, Map<String,CartaDiCredito> elencoCarte, Map <String,Utente> elencoUtenti) throws Exception {
        mode.pagaAppunto(u, elencoCarte, elencoUtenti);
    }
}
