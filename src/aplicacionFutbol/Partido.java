package aplicacionFutbol;

import java.io.Serializable;

public class Partido implements Serializable {
    private static final long serialVersionUID = 1L;
    private int temporadaNumero;
    private int jornadaNumero;
    private int marcadorLocal_1;
    private int marcadorVisitante_1;
    private int marcadorLocal_2;
    private int marcadorVisitante_2;
    private int marcadorLocal_3;
    private int marcadorVisitante_3;

    public Partido() {
    }

    public Partido(int temporadaNumero, int jornadaNumero, int marcadorLocal_1, int marcadorVisitante_1, int marcadorLocal_2, int marcadorVisitante_2, int marcadorLocal_3, int marcadorVisitante_3) {
        this.temporadaNumero = temporadaNumero;
        this.jornadaNumero = jornadaNumero;
        this.marcadorLocal_1 = marcadorLocal_1;
        this.marcadorVisitante_1 = marcadorVisitante_1;
        this.marcadorLocal_2 = marcadorLocal_2;
        this.marcadorVisitante_2 = marcadorVisitante_2;
        this.marcadorLocal_3 = marcadorLocal_3;
        this.marcadorVisitante_3 = marcadorVisitante_3;
    }

    public int getTemporadaNumero() {
        return temporadaNumero;
    }

    public void setTemporadaNumero(int temporadaNumero) {
        this.temporadaNumero = temporadaNumero;
    }

    public int getJornadaNumero() {
        return jornadaNumero;
    }

    public void setJornadaNumero(int jornadaNumero) {
        this.jornadaNumero = jornadaNumero;
    }

    public int getMarcadorLocal_1() {
        return marcadorLocal_1;
    }

    public void setMarcadorLocal_1(int marcadorLocal_1) {
        this.marcadorLocal_1 = marcadorLocal_1;
    }

    public int getMarcadorVisitante_1() {
        return marcadorVisitante_1;
    }

    public void setMarcadorVisitante_1(int marcadorVisitante_1) {
        this.marcadorVisitante_1 = marcadorVisitante_1;
    }

    public int getMarcadorLocal_2() {
        return marcadorLocal_2;
    }

    public void setMarcadorLocal_2(int marcadorLocal_2) {
        this.marcadorLocal_2 = marcadorLocal_2;
    }

    public int getMarcadorVisitante_2() {
        return marcadorVisitante_2;
    }

    public void setMarcadorVisitante_2(int marcadorVisitante_2) {
        this.marcadorVisitante_2 = marcadorVisitante_2;
    }

    public int getMarcadorLocal_3() {
        return marcadorLocal_3;
    }

    public void setMarcadorLocal_3(int marcadorLocal_3) {
        this.marcadorLocal_3 = marcadorLocal_3;
    }

    public int getMarcadorVisitante_3() {
        return marcadorVisitante_3;
    }

    public void setMarcadorVisitante_3(int marcadorVisitante_3) {
        this.marcadorVisitante_3 = marcadorVisitante_3;
    }
