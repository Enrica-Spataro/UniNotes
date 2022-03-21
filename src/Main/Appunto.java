package Main;

import java.util.LinkedList;
import java.util.Observable;

public class Appunto extends Observable {

    private String codiceAppunto;
    private String linkAppunto;
    private String titoloArgomento;
    private int numeroRecensioni;
    private String autore;
    private LinkedList<Recensione> listaRecensioni;



    public Appunto(String codiceAppunto, String linkAppunto, String titoloArgomento, String autore){
        this.codiceAppunto=codiceAppunto;
        this.linkAppunto=linkAppunto;
        this.titoloArgomento=titoloArgomento;
        this.autore=autore;
        this.numeroRecensioni=0;
        this.listaRecensioni= new LinkedList<Recensione>();
    }

    public String getLinkAppunto() {
        return linkAppunto;
    }

    public String getCodiceAppunto() {
        return codiceAppunto;
    }

    public String getTitoloArgomento() {
        return titoloArgomento;
    }

    public String getAutore() {
        return autore;
    }

    public void setAutore(String autore) {
        this.autore = autore;
    }

    public void setCodiceAppunto(String codiceAppunto) {
        this.codiceAppunto = codiceAppunto;
    }

    public void setLinkAppunto(String linkAppunto) {
        this.linkAppunto = linkAppunto;
    }

    public void setTitoloArgomento(String titoloArgomento) {
        this.titoloArgomento = titoloArgomento;
    }

    public int getNumeroRecensioni() {
        return numeroRecensioni;
    }

    public LinkedList<Recensione> getListaRecensioni() {
        return listaRecensioni;
    }


    public void setListaRecensioni(LinkedList<Recensione> listaRecensioni) {
        this.listaRecensioni = listaRecensioni;
    }

    public void setNumeroRecensioni(int numeroRecensioni) {
        this.numeroRecensioni = numeroRecensioni;
        this.setChanged();
        this.notifyObservers();
    }


}
