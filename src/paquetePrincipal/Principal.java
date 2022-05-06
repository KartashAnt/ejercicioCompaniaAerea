package paquetePrincipal;

import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Random;
import java.awt.Dimension;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Principal extends JFrame {

	static JPanel contentPane;
	static LocalDate fecha;
	static JLabel fechamostrada;
	static DateTimeFormatter formateo=DateTimeFormatter.ofPattern("dd/MM/YYYY");
	static LinkedList<Avion> aviones=new LinkedList<>();
	static LinkedList<Vuelo> vuelos=new LinkedList<>();
	
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
		contentPane = new JPanel();
		fechamostrada=new JLabel();
		fechamostrada.setText(fecha.format(formateo));
		fechamostrada.setFont(new Font("Verdana", 1, 25));
		Dimension texto=fechamostrada.getPreferredSize();
		fechamostrada.setBounds(225-texto.width/2, 50-texto.height/2, texto.width, texto.height);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(fechamostrada);
		fechamostrada.setVisible(true);
		setContentPane(contentPane);
	}
	public static void inicio() {
		fecha=LocalDate.now();
		for (int i = 1; i < 5; i++) {
			aviones.add(new Avion(i, Ciudad.MADRID));
		}
	}
	public static void listarVuelos() {
		String vuelos;
	}
	public static Ciudad[] generarDestinos(){
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
}
