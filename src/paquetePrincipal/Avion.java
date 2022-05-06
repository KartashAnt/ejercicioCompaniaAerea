package paquetePrincipal;

public class Avion {
	int numero;
	Ciudad disposicion;
	public Avion(int numero, Ciudad disposicion) {
		super();
		this.numero = numero;
		this.disposicion = disposicion;
	}
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
