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

	static JPanel contentPane;
	static JButton botonListado;
	static JButton botonVolar;
	static LocalDate fecha;
	static JLabel fechamostrada;
	static DateTimeFormatter formateo=DateTimeFormatter.ofPattern("dd/MM/YYYY");
	static LinkedList<Avion> aviones=new LinkedList<>();
	static LinkedList<Vuelo> vuelos=new LinkedList<>();
	static HashMap<String, Integer> duracionesVuelos=new HashMap<>();
	
 	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		inicio();
		Principal frame = new Principal();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public Principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel(new BorderLayout());
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		botonVolar=new JButton("Pasar un dia");
		contentPane.add(botonVolar, BorderLayout.EAST);
		botonVolar.addActionListener(this);
		botonVolar.setActionCommand("Volar");
		botonListado=new JButton("Listar vuelos");
		contentPane.add(botonListado, BorderLayout.WEST);
		botonListado.addActionListener(this);
		botonListado.setActionCommand("Lista");
		fechamostrada=new JLabel();
		fechamostrada.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(fechamostrada, BorderLayout.NORTH);
		fechamostrada.setText(fecha.format(formateo));
		fechamostrada.setFont(new Font("Verdana", 1, 25));
		Dimension texto=fechamostrada.getPreferredSize();
		fechamostrada.setBounds(225-texto.width/2, 50-texto.height/2, texto.width, texto.height);
		setContentPane(contentPane);
	}
	public static void inicio() {
		fecha=LocalDate.now();
		for (int i = 1; i < 5; i++) {
			aviones.add(new Avion(i, Ciudad.MADRID));
		}
	}
	public void listarVuelos(Principal frame) {
		JPanel conjunto=new JPanel(new BorderLayout());
		conjunto.setBorder(new EmptyBorder(5, 5, 5, 5));
		String lista="Vuelos realizado hasta " + fecha.format(formateo);
		if(vuelos.size()>0) {
			for (Vuelo vuelo : vuelos) {
				lista+="\n" + vuelo;
			}
		}
		else {
			lista+="\nTodav\u00EDa no se realizo ningun vuelo";
		}
		JTextArea texto= new JTextArea(lista);
		JScrollPane listado=new JScrollPane(texto);
		conjunto.add(listado, BorderLayout.CENTER);
		JButton botonVuelta=new JButton("Volver");
		botonVuelta.addActionListener(this);
		botonVuelta.setActionCommand("Volver");
		conjunto.add(botonVuelta, BorderLayout.SOUTH);
		frame.setContentPane(conjunto);
		conjunto.revalidate();
	}
	public Ciudad[] generarDestinos(){
		Ciudad[] destinos= new Ciudad[4];
		Random r=new Random();
		for (int i = 0; i < destinos.length; i++) {
			boolean invalido=true;
			int ord=0;
			do {
				invalido=false;
				ord=r.nextInt(Ciudad.values().length);
				if(aviones.get(i).getDisposicion().ordinal()==ord) invalido=true;
				else {
					for (int j = 0; j < i; j++) {
						if(destinos[j].ordinal()==ord) invalido=true;
					}
				}
			} while (invalido);
			destinos[i]=Ciudad.values()[ord];
		}
		return destinos;
	}
	
	public void pasarElDia() {
		Random r=new Random();
		int hora=r.nextInt(17)+7;
		int minuto=r.nextInt(12)*5;
		LocalTime horaSalida=LocalTime.of(hora, minuto);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String comando=e.getActionCommand();
		switch (comando) {
		case "Lista":
			listarVuelos(this);
			break;
		case "Volver":
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
