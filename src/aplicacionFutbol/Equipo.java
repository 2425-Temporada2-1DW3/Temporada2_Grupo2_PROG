package aplicacionFutbol;

import java.io.Serializable;
import java.util.List;

public class Equipo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7411400281855498907L;
	private String nombre;
	private List<Jugador> jugadores;
	
	
	
	// constructor lleno
	public Equipo(String nombre, Jugador j1, Jugador j2, Jugador j3, Jugador j4, Jugador j5) {
			this.nombre= nombre;
			this.jugadores.add(j1);
			this.jugadores.add(j2);
			this.jugadores.add(j3);
			this.jugadores.add(j4);
			this.jugadores.add(j5);
	}
	

}
