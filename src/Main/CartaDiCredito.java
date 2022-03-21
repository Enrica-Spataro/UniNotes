package Main;

import java.text.DateFormat;
import java.util.Date;

public class CartaDiCredito {

    private String codiceCarta;
    private String scadenza;

    public CartaDiCredito(String codiceCarta, String scadenza){
        this.codiceCarta=codiceCarta;
        this.scadenza=scadenza;
    }

    public String getScadenza() {
        return scadenza;
    }

    public void setScadenza(String scadenza) {
        this.scadenza = scadenza;
    }

    public String getCodiceCarta() {
        return codiceCarta;
    }

    public void setCodiceCarta(String codiceCarta) {
        this.codiceCarta = codiceCarta;
    }


}
