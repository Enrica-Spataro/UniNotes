import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Facolta {
    private int codiceFacoltà;
    private String nomeFacoltà;
    private LinkedList<Materia> listaMaterie;
    private  BufferedReader tastiera;


    public Facolta(int codiceFacoltà, String nomeFacoltà){
        this.codiceFacoltà=codiceFacoltà;
        this.nomeFacoltà=nomeFacoltà;
        this.listaMaterie=new LinkedList<>();
        this.tastiera=new BufferedReader(new InputStreamReader(System.in));
    }

    public int getCodiceFacoltà() {
        return codiceFacoltà;
    }

    public String getNomeFacoltà() {
        return nomeFacoltà;
    }

    public void setCodiceFacoltà(int codiceFacoltà) {
        this.codiceFacoltà = codiceFacoltà;
    }

    public void setNomeFacoltà(String nomeFacoltà) {
        this.nomeFacoltà = nomeFacoltà;
    }

    public LinkedList<Materia> getListaMaterie() {
        return listaMaterie;
    }

    public void setListaMaterie(LinkedList<Materia> listaMaterie) {
        this.listaMaterie = listaMaterie;
    }

    public Materia trovaMateria(@NotNull Facolta f, String materia, String docente) throws IOException {
        Materia trovata = null;
        LinkedList <Materia> listaMaterie = f.getListaMaterie();
        Iterator<Materia> var = listaMaterie.iterator();

        if(listaMaterie.size()==0)
            trovata = new Materia(materia, docente);

        while(var.hasNext()) {
            Materia m = (Materia)var.next();
            if(Objects.equals(materia, m.getNomeMateria()) && Objects.equals(docente, m.getDocente()))
                trovata = m;
            else
                trovata = new Materia(materia, docente);
        }
        return trovata;

    }

    public Map<String, Appunto> selezionaMateria(@NotNull Facolta f, String nomeMateria) throws IOException {
        LinkedList <Materia> listaMaterie=f.getListaMaterie();
        Map<String, Appunto> elencoAppunti = null;
        Iterator<Materia> var = listaMaterie.iterator();

        while(var.hasNext()) {
            Materia m = (Materia)var.next();
            if(Objects.equals(nomeMateria, m.getNomeMateria()))
                elencoAppunti = m.getElencoAppuntiApprovati();
            else {
                System.out.println("\nLa materia selezionata non esiste");
                System.out.println("\n Digitare nuovamente");
                nomeMateria= this.tastiera.readLine();
                selezionaMateria(f,nomeMateria);
            }
        }
        return elencoAppunti;
    }
}