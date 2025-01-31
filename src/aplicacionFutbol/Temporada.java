package aplicacionFutbol;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



public class Temporada implements Serializable {



    private static final long serialVersionUID = 1L;

    private int ano;

    private List<Equipo> equipos;

    
    public Temporada() {
    	this.ano = 0000;
    	List<Equipo> EquiposVacios = new ArrayList<>();
    	for(int i=0;i<6;i++) {
    		EquiposVacios.add(new Equipo());
    	}
        this.equipos = (EquiposVacios);
    }

    public Temporada(int ano, List<Equipo> equipos) {

        this.ano = ano;

        this.equipos = equipos;

    }



    public int getAno() {

        return ano;

    }



    public void setAno(int ano) {

        this.ano = ano;

    }



    public List<Equipo> getEquipos() {

        return equipos;

    }



    public void setEquipos(List<Equipo> equipos) {

        this.equipos = equipos;

    }
}
//comitt