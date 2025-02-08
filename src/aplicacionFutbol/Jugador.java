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
	private String rutaImagen;
	
	public Jugador() {
		this.nombre = "Jugador";
		this.apellidos = "Inexistente";
		this.rutaImagen = "";
	}
	
	
	// constructor sin imagen
	public Jugador(String nombre, String apellidos) {
			this.nombre = nombre;
			this.apellidos = apellidos;
			this.rutaImagen = "";
		}
	
	// constructor con imagen
	public Jugador(String nombre, String apellidos, String path) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.rutaImagen = path;
	}


	


	@Override
	public String toString() {
		String ImagenAsignada;
		if (this.rutaImagen == "") {
			ImagenAsignada = " SI ";
		}else {
			ImagenAsignada = " NO ";
		}
		return "" + nombre + ", " + apellidos + " Imagen: [" + rutaImagen+ "]";
	}

	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getRutaImagen() {
		return rutaImagen;
	}

	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	

}
