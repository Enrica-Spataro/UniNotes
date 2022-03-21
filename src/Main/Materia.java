package Main;

import java.util.*;

public class Materia {
    private Map<String, Appunto> elencoAppuntiInSospeso;
    private Map<String, Appunto> elencoAppuntiApprovati;
    private String nomeMateria;
    private String docente;

    public Materia(String nomeMateria, String docente){
        this.docente=docente;
        this.nomeMateria=nomeMateria;
        this.elencoAppuntiInSospeso = new HashMap<>();
        this.elencoAppuntiApprovati = new HashMap<>();
    }

    public String getDocente() {
        return docente;
    }

    public String getNomeMateria() {
        return nomeMateria;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public void setNomeMateria(String nomeMateria) {
        this.nomeMateria = nomeMateria;
    }

    public Map<String, Appunto> getElencoAppuntiInSospeso() {
        return elencoAppuntiInSospeso;
    }

    public void setElencoAppuntiInSospeso(Map<String, Appunto> elencoAppuntiInSospeso) {
        this.elencoAppuntiInSospeso = elencoAppuntiInSospeso;
    }

    public Map<String, Appunto> getElencoAppuntiApprovati() {
        return elencoAppuntiApprovati;
    }

    public void setElencoAppuntiApprovati(Map<String, Appunto> elencoAppuntiApprovati) {
        this.elencoAppuntiApprovati = elencoAppuntiApprovati;
    }

    @Override
    public String toString() {
        return nomeMateria;
    }

    public Appunto selezionaAppunto(Materia m, String codiceAppunto){

        Appunto appunto=null;
        Map <String, Appunto> elencoAppunti = m.getElencoAppuntiApprovati();
        appunto= (Appunto) elencoAppunti.get(codiceAppunto);
        return appunto;
    }



}
