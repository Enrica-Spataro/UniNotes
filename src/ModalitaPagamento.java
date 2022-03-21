import java.io.IOException;
import java.text.ParseException;
import java.util.Map;

public interface ModalitaPagamento  {
    public void pagaAppunto(Utente u, Map<String,CartaDiCredito> elencoCarte, Map <String,Utente> elencoUtenti) throws Exception;
}
