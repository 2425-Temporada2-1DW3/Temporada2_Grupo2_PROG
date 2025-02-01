package aplicacionFutbol;

import java.io.Serializable;
import java.util.List;

public class Temporadas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5866519557344615079L;
	private List<Temporada> temporadas;
	
	public Temporadas(List<Temporada> temporadas){
		this.temporadas = temporadas;
	}

	@Override
	public String toString() {
		return "Temporadas [temporadas=" + temporadas + "]";
	}

	public List<Temporada> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(List<Temporada> temporadas) {
		this.temporadas = temporadas;
	}
	
	
	
}
