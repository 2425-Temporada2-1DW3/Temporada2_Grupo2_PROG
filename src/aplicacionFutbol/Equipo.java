package aplicacionFutbol;

public class Equipo {
    private String nombre;
    private int golesFavor;
    private int golesContra;
    private int puntos;

    public Equipo(String nombre) {
        this.nombre = nombre;
        this.golesFavor = 0;
        this.golesContra = 0;
        this.puntos = 0;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getGolesFavor() {
        return golesFavor;
    }

    public void setGolesFavor(int golesFavor) {
        this.golesFavor = golesFavor;
    }

    public int getGolesContra() {
        return golesContra;
    }

    public void setGolesContra(int golesContra) {
        this.golesContra = golesContra;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getDiferenciaGoles() {
        return golesFavor - golesContra;
    }

    public void agregarResultado(int golesFavor, int golesContra) {
        this.golesFavor += golesFavor;
        this.golesContra += golesContra;
    }

    public void agregarPuntos(int puntos) {
        this.puntos += puntos;
    }
}