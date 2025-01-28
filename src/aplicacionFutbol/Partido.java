package aplicacionFutbol;

import java.io.Serializable;

public class Partido implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4919146540552823915L;
	private int temporada;
	private int jornada;
	private int partido;
	private int marcadorLocal;
	private int marcadorVisitante;
	
	// constructor manual
	public Partido(int t, int j, int p, int ml, int mv) {
		this.temporada= t;
		this.jornada = j;
		this.partido = p;
		this.marcadorLocal = ml;
		this.marcadorVisitante = mv;
	}

	public Partido() {
		temporada= 1;
		jornada = 0;
		partido = 0;
		marcadorLocal = 0;
		marcadorVisitante = 0;
	}

	@Override
	public String toString() {
		return "Marcador local "+marcadorLocal+" - "+marcadorVisitante+" Marcador visitante";
	}

	//Getters
	public int getTemporadaNumero() {
		return temporada;
	}
	
	public int getJornadaNumero() {
		return jornada;
	}

	public int getPartidoNumero() {
		return partido;
	}

	public int getMarcadorLocal() {
		return marcadorLocal;
	}

	public int getMarcadorVisitante() {
		return marcadorVisitante;
	}
	
	
	//Setters
	public void setTemporadaNumero(int t) {
		this.temporada = t;
	}
	
	public void setJornadaNumero(int j) {
		this.jornada = j;
	}

	public void setPartidoNumero(int p) {
		this.partido = p;
	}

	public void setMarcadorLocal(int ml) {
		this.marcadorLocal = ml;
	}

	public void setMarcadorVisitante(int mv) {
		this.marcadorVisitante = mv;
	}
}