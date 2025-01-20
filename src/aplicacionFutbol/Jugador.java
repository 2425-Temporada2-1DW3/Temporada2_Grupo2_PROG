package aplicacionFutbol;

import java.io.Serializable;

public class Jugador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8717467141179283763L;
	/**
	 * 
	 */
	private String nombre;
	private String apellidos;
	
	
	// constructor manual
	public Jugador(String nombre, String apellidos) {
			this.nombre = nombre;
			this.apellidos = apellidos;
		}
	
	

}
