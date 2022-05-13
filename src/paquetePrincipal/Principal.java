package paquetePrincipal;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;


public class Principal extends JFrame implements ActionListener{
	// Panel principal
	static JPanel contentPane;
	// Paneles de soporte
	static JPanel conjunto;
	static JPanel vuelosDiarios;
	// Botones multiuso
	static JButton botonVuelta;
	// Fecha
	static LocalDate fecha;
	// Fecha formateada
	static JLabel fechamostrada;
	// Patrón de formateo de la fecha
	static DateTimeFormatter formateo=DateTimeFormatter.ofPattern("dd/MM/YYYY");
	// Listas de los aviones y vuelos
	static LinkedList<Avion> aviones=new LinkedList<>();
	static LinkedList<Vuelo> vuelos=new LinkedList<>();
	// Guardo duraciones de vuelos por su codigo
	static HashMap<String, Integer> duracionesVuelos=new HashMap<>();
	
 	/**
	 * Enciende la aplicación
	 */
	public static void main(String[] args) {
		// Asigno parametros iniciales
		inicio();
		// Creo GUI
		Principal frame = new Principal();
		frame.setVisible(true);
	}

	/**
	 * Creo frame
	 */
	public Principal() {
		// La pñrograma apaga al cerrarla
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Locación y tamaño de la ventana
		setBounds(100, 100, 500, 500);
		// Uso border layout porque facilita poscionamiento
		contentPane = new JPanel(new BorderLayout());
		// Margen de contenido
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		// Botton de vuelo
		JButton botonVolar=new JButton("Pasar un dia");
		contentPane.add(botonVolar, BorderLayout.CENTER);
		botonVolar.addActionListener(this);
		botonVolar.setActionCommand("Volar");
		
		JButton botonListado=new JButton("Listar vuelos");
		contentPane.add(botonListado, BorderLayout.WEST);
		botonListado.addActionListener(this);
		botonListado.setActionCommand("Lista");
		// Fecha que pintamos
		fechamostrada=new JLabel();
		
		fechamostrada.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPane.add(fechamostrada, BorderLayout.NORTH);
		
		fechamostrada.setText(fecha.format(formateo));
		fechamostrada.setFont(new Font("Verdana", 1, 25));
		
		Dimension textoDim=fechamostrada.getPreferredSize();
		//Lo ponemos como panel principal
		setContentPane(contentPane);
		//Condicions iniciales de otros paneles
		conjunto=new JPanel(new BorderLayout());
		
		conjunto.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		vuelosDiarios=new JPanel(new BorderLayout());
		
		vuelosDiarios.setBorder(new EmptyBorder(5, 5, 5, 5));
		//Texto inútil para poder removerlo desde conjunto
		conjunto.add(new JTextArea("Nada"));
		//Botton de la vuelta
		botonVuelta=new JButton("Volver");
		botonVuelta.addActionListener(this);
		botonVuelta.setActionCommand("Volver");
	}
	
	//Condiciones iniciales
	public static void inicio() {
		// Fecha de hoy
		fecha=LocalDate.now();
		// 4 aviones
		for (int i = 1; i < 5; i++) {
			aviones.add(new Avion(i, Ciudad.MADRID));
		}
	}
	
	// Listar vuelos
	public void listarVuelos(Principal frame) {
		// Lo guardo en un String
		String lista="Vuelos realizado hasta " + fecha.format(formateo);
		// Muestro vuelos
		if(vuelos.size()>0) {
			for (Vuelo vuelo : vuelos) {
				lista+="\n" + vuelo;
			}
		}
		else {
			lista+="\nTodav\u00EDa no se realizo ningun vuelo";
		}
		//Borro la lista vieja
		BorderLayout layout=(BorderLayout) conjunto.getLayout();
		conjunto.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		// Colecto basura
		System.gc();
		//Añado nuevo texto
		JScrollPane listado=new JScrollPane(new JTextArea(lista));
		conjunto.add(listado,BorderLayout.CENTER);
		// Añado boton de la vuelta
		conjunto.add(botonVuelta,BorderLayout.SOUTH);
		//Cambio panel de contenido
		frame.setContentPane(conjunto);
		conjunto.revalidate();
	}
	
	// Metodo para generar ciudades destinos
	public Ciudad[] generarDestinos(){
		// Generamos tanto destinos como aviones tenemos
		Ciudad[] destinos= new Ciudad[aviones.size()];
		//Random
		Random r=new Random();
		//Rellenamos destinos
		for (int i = 0; i < destinos.length; i++) {
			boolean invalido=true;
			//Vamos a usar ordinal de enum
			int ord=0;
			do {
				//Suponemos que es valido
				invalido=false;
				// Generamos numero de cualquiera ciudad
				ord=r.nextInt(Ciudad.values().length);
				// Si avión ya está alli repetimos
				if(aviones.get(i).getDisposicion().ordinal()==ord) invalido=true;
				// Recoremos destinos generados anterioramente
				// Si tiene el mismo ordinal entonces es el mismo, entonces no nos vale
				else {
					for (int j = 0; j < i; j++) {
						if(destinos[j].ordinal()==ord) invalido=true;
					}
				}
			} while (invalido);
			// Asignamos valor de ciudad con ese ordinal
			destinos[i]=Ciudad.values()[ord];
		}
		return destinos;
	}
	
	//Metodo para pasar un dia
	public void pasarElDia() {
		//Aqui guardamos el texto
		JTextArea vuelosHoy=new JTextArea();
		//Random para tiempo
		Random r=new Random();
		//generamos destinos
		Ciudad[] destinos=generarDestinos();
		//Añadimos boton
		vuelosDiarios.add(botonVuelta,BorderLayout.SOUTH);
		this.setContentPane(vuelosDiarios);
		//Quitamos texto viejo
		BorderLayout layout=(BorderLayout) vuelosDiarios.getLayout();
		if(layout.getLayoutComponent(BorderLayout.CENTER)!=null)
		vuelosDiarios.remove(layout.getLayoutComponent(BorderLayout.CENTER));
		//Añadimos nuevo
		vuelosDiarios.add(vuelosHoy,BorderLayout.CENTER);
		//Revalidamos
		vuelosDiarios.revalidate();
		//Cabecero
		vuelosHoy.append("Vuelos:\t" + fechamostrada.getText() + "\t\t" + "Hora de la salida");
		//Generamos vuelos
		for (int i = 0; i < destinos.length; i++) {
			//Genera hora/minuto
			int hora=r.nextInt(17)+7;
			int minuto=r.nextInt(12)*5;
			//Construit hora de la salida
			LocalTime horaSalida=LocalTime.of(hora, minuto);
			//Crear vuelo incopmleto
			vuelos.add(new Vuelo(aviones.get(i).getNumero(), aviones.get(i).getDisposicion(), LocalDateTime.of(fecha, horaSalida), destinos[i]));
			//Imprimir vuelo
			vuelosHoy.append("\n"+vuelos.getLast());
			//Sumar duración del vuelo
			vuelos.getLast().setFechaHoraLlegada(LocalDateTime.of(fecha, horaSalida).plusMinutes(obtenerDuracionDeUnVuelo((aviones.get(i).getDisposicion().name().substring(0,2) + destinos[i].name().substring(0,2)))));
			//Cambiar disposición de avión por la ciudad nueva
			aviones.get(i).setDisposicion(destinos[i]);
		}
		//Imprimir la hora de llegada de cada uno de los aviones
		for (int i = 0; i < aviones.size(); i++) {
			
			vuelosHoy.append("\n"+vuelos.get(vuelos.size()-aviones.size()+i).mostrarHoraLLegada());
			
		}
		// Sumar un dia a la fecha
		fecha=fecha.plusDays(1);
	}
	
	//Obtener duración de un vuelo
	public int obtenerDuracionDeUnVuelo(String codigo) {
		// Vemos si no contiene el codigo HashMap
		if(!duracionesVuelos.containsKey(codigo)) {
			//Preguntamos uno hasta obtenerlo valido
			int duracion=0;
			do {
				try {
					duracion=Integer.parseInt(JOptionPane.showInputDialog("Introduzca duración de último vuelo en la\nlista en minutos con un maxímo de 300:"));
				} catch (NumberFormatException e) {
					duracion=0;
				}
			} while (duracion<=0 || duracion>300);
			// Lo añadiomos a la mapa
			duracionesVuelos.put(codigo, duracion);
		}
		// lo devolvemos
		return duracionesVuelos.get(codigo);
	}

	// Manejo botones
	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		switch (comando) {
		case "Lista":
			listarVuelos(this);
			break;
		case "Volver":
			fechamostrada.setText(fecha.format(formateo));
			this.setContentPane(contentPane);
			contentPane.revalidate();
			break;
		case "Volar":
			pasarElDia();
			break;
		default:
			break;
		}
	}
}
