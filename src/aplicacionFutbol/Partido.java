package aplicacionFutbol;

import java.io.Serializable;

public class Partido implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4919146540552823915L;
	private int temporada;
	private int jornada;
	private int marcadorLocal_1;
	private int marcadorVisitante_1;
	private int marcadorLocal_2;
	private int marcadorVisitante_2;
	private int marcadorLocal_3;
	private int marcadorVisitante_3;
	
	// constructor manual
	public Partido(int t, int j, int ml1, int mv1, int ml2, int mv2, int ml3, int mv3) {
		this.temporada= t;
		this.jornada = j;
		this.marcadorLocal_1 = ml1;
		this.marcadorLocal_2 = ml2;
		this.marcadorLocal_3 = ml3;
		this.marcadorVisitante_1 = mv1;
		this.marcadorVisitante_2 = mv2;
		this.marcadorVisitante_3 = mv3;
	}

	public Partido() {
		temporada= 1;
		jornada = 0;
		marcadorLocal_1 = 0;
		marcadorLocal_2 = 0;
		marcadorLocal_3 = 0;
		marcadorVisitante_1 = 0;
		marcadorVisitante_2 = 0;
		marcadorVisitante_3 = 0;
	}

	@Override
	public String toString() {
		return marcadorLocal_1 +" - "+ marcadorVisitante_1+"\n"+marcadorLocal_2 +" - "+ marcadorVisitante_2+"\n"+marcadorLocal_3 +" - "+ marcadorVisitante_3+"\n";
	}

	//Getters
	public int getTemporadaNumero() {
		return temporada;
	}
	
	public int getJornadaNumero() {
		return jornada;
	}

	public int getMarcadorLocal_1() {
		return marcadorLocal_1;
	}

	public int getMarcadorVisitante_1() {
		return marcadorVisitante_1;
	}
	
	public int getMarcadorLocal_2() {
		return marcadorLocal_2;
	}

	public int getMarcadorVisitante_2() {
		return marcadorVisitante_2;
	}
	
	public int getMarcadorLocal_3() {
		return marcadorLocal_3;
	}

	public int getMarcadorVisitante_3() {
		return marcadorVisitante_3;
	}
	
	
	//Setters
	public void setTemporadaNumero(int t) {
		this.temporada = t;
	}
	
	public void setJornadaNumero(int j) {
		this.jornada = j;
	}

	public void setMarcadorLocal1(int m) {
		this.marcadorLocal_1 = m;
	}
	
	public void setMarcadorLocal2(int m) {
		this.marcadorLocal_2 = m;
	}
	
	public void setMarcadorLocal3(int m) {
		this.marcadorLocal_3 = m;
	}

	public void setMarcadorVisitante1(int m) {
		this.marcadorVisitante_1 = m;
	}
	
	public void setMarcadorVisitante2(int m) {
		this.marcadorVisitante_2 = m;
	}
	
	public void setMarcadorVisitante3 (int m) {
		this.marcadorVisitante_3 = m;
	}
}