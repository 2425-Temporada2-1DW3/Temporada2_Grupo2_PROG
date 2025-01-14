package aplicacionFutbol;

import java.io.Serializable;

public class Usuario implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -399378772072609192L;
	private String nombre;
	private String pwd;
	private String rol;
	
	// constructor manual
	public Usuario(String user, String password, String rol) {
		this.nombre= user;
		this.pwd = password;
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", rol=" + rol + "]";
	}



	
	
	
	

}
