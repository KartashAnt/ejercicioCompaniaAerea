package paquetePrincipal;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Vuelo {
	// Elementos que realzian formateo a la hora de mostrar fecha o/y hora
	static final DateTimeFormatter formateo = DateTimeFormatter.ofPattern("dd/MM/YYYY HH:mm");
	static final DateTimeFormatter formateoHoras = DateTimeFormatter.ofPattern("HH:mm");
	// Contador de los vuelos
	static int numVuelo = 0;
	// Datos guardados sobre un vuelo(numero de avión,codigo del vuelo,origen/destino,cuando salia/cuando llegaba);
	private int numAvion;
	private String codigo;
	private Ciudad origen;
	private LocalDateTime fechaHoraSalida;
	private Ciudad destino;
	private LocalDateTime fechaHoraLlegada;

	// Constructor cuando no sabemos la hora de la llegada
	public Vuelo(int numAvion, Ciudad origen, LocalDateTime fechaHoraSalida, Ciudad destino) {
		super();
		numVuelo++;
		this.numAvion = numAvion;
		this.origen = origen;
		this.fechaHoraSalida = fechaHoraSalida;
		this.destino = destino;
		this.codigo = generarCodigo();
	}

	// Constructor completo
	public Vuelo(int numAvion, Ciudad origen, LocalDateTime fechaHoraSalida, Ciudad destino,
			LocalDateTime fechaHoraLlegada) {
		super();
		numVuelo++;
		this.numAvion = numAvion;
		this.origen = origen;
		this.fechaHoraSalida = fechaHoraSalida;
		this.destino = destino;
		this.fechaHoraLlegada = fechaHoraLlegada;
		this.codigo = generarCodigo();
	}

	// El toString
	@Override
	public String toString() {
		// Si sabemos la hora de llegada
		if (fechaHoraLlegada != null) {
			return this.codigo + " : " + origen + " -> " + destino + " : " + fechaHoraSalida.format(formateo) + " -> "+ fechaHoraLlegada.format(formateo);
		}
		// Si no la sabemos
		else {
			return this.codigo + " : " + origen + " -> " + destino + "-----------------------------"+ fechaHoraSalida.format(formateoHoras);
		}

	}

	// Muestro solo la hora de la llegada
	public String mostrarHoraLLegada() {
		// Si llegaba el otro dia
		if (fechaHoraSalida.getDayOfYear() != fechaHoraLlegada.getDayOfYear()) {
			return "Hora de la llegada de avion" + this.numAvion + ": " + fechaHoraLlegada.format(formateoHoras) + " de la dia siguiente";
		}
		// Si llega el dia de la salida
		else {
			return "Hora de la llegada de avion" + this.numAvion + ": " + fechaHoraLlegada.format(formateoHoras);
		}
	}

	// Generador de codigo de un vuelo
	public String generarCodigo() {
		return numAvion + origen.name().substring(0, 2) + destino.name().substring(0, 2) + numVuelo;
	}

	// Getters y Setters básicos
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

	public Ciudad getDestino() {
		return destino;
	}

	public void setDestino(Ciudad destino) {
		this.destino = destino;
	}

	public LocalDateTime getFechaHoraSalida() {
		return fechaHoraSalida;
	}

	public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
		this.fechaHoraSalida = fechaHoraSalida;
	}

	public LocalDateTime getFechaHoraLlegada() {
		return fechaHoraLlegada;
	}

	public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
		this.fechaHoraLlegada = fechaHoraLlegada;
	}

}
