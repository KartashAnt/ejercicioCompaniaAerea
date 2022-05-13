package paquetePrincipal;

// Clase de un avíon
public class Avion {
	// Caracterisiticas de un avión es su numero y donde está
	private int numero;
	private Ciudad disposicion;

	// Constructor con todas las caracteristicas
	public Avion(int numero, Ciudad disposicion) {
		super();
		this.numero = numero;
		this.disposicion = disposicion;
	}

	// Getters y Setters basícos
	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public Ciudad getDisposicion() {
		return disposicion;
	}

	public void setDisposicion(Ciudad disposicion) {
		this.disposicion = disposicion;
	}

}
