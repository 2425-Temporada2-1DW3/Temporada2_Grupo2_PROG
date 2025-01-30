package aplicacionFutbol;

import java.io.Serializable;
import java.util.List;

public class Temporada implements Serializable {

    private static final long serialVersionUID = 1L;
    private int ano;
    private List<Equipo> equipos;

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