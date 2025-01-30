package aplicacionFutbol;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Equipo implements Serializable {

    private static final long serialVersionUID = -7411400281855498907L;
    private String nombre_e; // Cambiado a variable de instancia
    private List<Jugador> jugadores;
    private int ano;
    private String rutaImagen; // Añadido para la ruta de la imagen

    // Constructor equipo lleno
    public Equipo(String nombre, Jugador j1, Jugador j2, Jugador j3, Jugador j4, Jugador j5) {
        this.nombre_e = nombre;
        this.jugadores = new ArrayList<>(); // Inicializar la lista de jugadores
        this.jugadores.add(j1);
        this.jugadores.add(j2);
        this.jugadores.add(j3);
        this.jugadores.add(j4);
        this.jugadores.add(j5);
    }

    // Constructor equipo vacío
    public Equipo(String nombre, int anodefundacion) {
        this.nombre_e = nombre;
        this.ano = anodefundacion;
        this.jugadores = new ArrayList<>(); // Inicializar la lista de jugadores
    }

    @Override
    public String toString() {
        return "Equipo [nombre=" + nombre_e + ", Año de fundacion=" + ano + "]";
    }

    public String getNombre() {
        return nombre_e;
    }

    public void setNombre(String nombre) {
        this.nombre_e = nombre;
    }

    public List<Jugador> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }
}