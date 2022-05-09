package paquetePrincipal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vuelo {
	static final DateTimeFormatter formateo=DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm");
	static int numVuelo=0;
	int numAvion;
	String codigo;
	Ciudad origen;
	LocalDateTime fechaSalida;
	Ciudad destino;
	LocalDateTime fechaLlegada;
	
	public Vuelo(int numAvion, Ciudad origen, LocalDateTime fechaSalida, Ciudad destino,
			LocalDateTime fechaLlegada) {
		super();
		numVuelo++;
		this.numAvion = numAvion;
		this.origen = origen;
		this.fechaSalida = fechaSalida;
		this.destino = destino;
		this.fechaLlegada = fechaLlegada;
		this.codigo=generarCodigo();
	}
	
	@Override
	public String toString() {
		if(this.fechaLlegada.getDayOfMonth()==this.fechaSalida.getDayOfMonth())
		return this.codigo + " : " + origen + " -> " + destino + " : " + fechaSalida.format(formateo) + " -> " + fechaLlegada.format(formateo);
		else
			return this.codigo + " : " + origen + " -> " + destino + " : " + fechaSalida.format(formateo) + " -> " + fechaLlegada.format(formateo) + "del dia siguiente";
	}
	public String generarCodigo(){
		return numAvion+origen.name().substring(0,2)+destino.name().substring(0,2)+numVuelo;
	}
	public int getNumAvion() {
		return numAvion;
	}
	public void setNumAvion(int numAvion) {
		this.numAvion = numAvion;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Ciudad getOrigen() {
		return origen;
	}
	public void setOrigen(Ciudad origen) {
		this.origen = origen;
	}
	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public Ciudad getDestino() {
		return destino;
	}
	public void setDestino(Ciudad destino) {
		this.destino = destino;
	}
	public LocalDateTime getFechaLlegada() {
		return fechaLlegada;
	}
	public void setFechaLlegada(LocalDateTime fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}
	
	
}
