import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class PagaConCarta implements ModalitaPagamento{
    @Override
    public void pagaAppunto(Utente u, Map<String,CartaDiCredito> elencoCarte, Map<String, Utente> elencoUtenti) throws Exception {
        CartaDiCredito cdc = elencoCarte.get(u.getCartaDiCredito());
        Date scad = new SimpleDateFormat("dd/MM/yyyy").parse(cdc.getScadenza());
        long millisecond = System.currentTimeMillis();
        Date data = new Date(millisecond);
        try{
        if(cdc.getCodiceCarta().length() < 19 || scad.compareTo(data) < 0) {
            System.out.println("\n\nCarta non valida, non è possibile effettuare l'acquisto.\n");
            //UniNotes uniNotes= UniNotes.getInstance();
            throw new Exception("Carta non valida");
            //uniNotes.menuUtente();
        }
        else{
            System.out.println("PAGAMENTO CARTA:\nAcquisto effettuato con successo.\n");
        }}catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
