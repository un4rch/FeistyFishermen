package proyecto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.SwingConstants;

public class BaseTablero extends JFrame {

	private JPanel main;
	private final ButtonGroup acciones = new ButtonGroup();
	private final ButtonGroup tipoBarco = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaseTablero frame = new BaseTablero();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BaseTablero() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 426);
		main = new JPanel();
		main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(main);
		main.setLayout(new BorderLayout(0, 0));
		
		JPanel barcos = new JPanel();
		main.add(barcos, BorderLayout.EAST);
		barcos.setLayout(new GridLayout(0, 1, 0, 0));
		
		JRadioButton portaaviones = new JRadioButton("Portaaviones");
		tipoBarco.add(portaaviones);
		barcos.add(portaaviones);
		
		JRadioButton sumbarino = new JRadioButton("Submarino");
		tipoBarco.add(sumbarino);
		barcos.add(sumbarino);
		
		JRadioButton destructor = new JRadioButton("Destructor");
		tipoBarco.add(destructor);
		barcos.add(destructor);
		
		JRadioButton fragata = new JRadioButton("Fragata");
		tipoBarco.add(fragata);
		barcos.add(fragata);
		
		JPanel direcciones = new JPanel();
		barcos.add(direcciones);
		direcciones.setLayout(new GridLayout(3, 3, 0, 0));
		
		JLabel blank1 = new JLabel("");
		direcciones.add(blank1);
		
		JLabel arriba = new JLabel("A");
		arriba.setHorizontalAlignment(SwingConstants.CENTER);
		direcciones.add(arriba);
		
		JLabel blank2 = new JLabel("");
		direcciones.add(blank2);
		
		JLabel izquierda = new JLabel("<--");
		izquierda.setHorizontalAlignment(SwingConstants.CENTER);
		direcciones.add(izquierda);
		
		JLabel blank3 = new JLabel("");
		direcciones.add(blank3);
		
		JLabel derecha = new JLabel("-->");
		derecha.setHorizontalAlignment(SwingConstants.CENTER);
		direcciones.add(derecha);
		
		JLabel blank4 = new JLabel("");
		direcciones.add(blank4);
		
		JLabel abajo = new JLabel("V");
		abajo.setHorizontalAlignment(SwingConstants.CENTER);
		direcciones.add(abajo);
		
		JLabel blank5 = new JLabel("");
		direcciones.add(blank5);
		
		JPanel inventario = new JPanel();
		main.add(inventario, BorderLayout.WEST);
		inventario.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton tienda = new JButton("Tienda");
		inventario.add(tienda);
		
		JLabel espacio = new JLabel("");
		inventario.add(espacio);
		
		JRadioButton bomba = new JRadioButton("Bomba");
		acciones.add(bomba);
		inventario.add(bomba);
		
		JRadioButton escudo = new JRadioButton("Escudo");
		acciones.add(escudo);
		inventario.add(escudo);
		
		JRadioButton misil = new JRadioButton("Misil");
		acciones.add(misil);
		inventario.add(misil);
		
		JRadioButton radar = new JRadioButton("Radar");
		acciones.add(radar);
		inventario.add(radar);
		
		JPanel tableros = new JPanel();
		main.add(tableros, BorderLayout.CENTER);
		tableros.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel rival = new JPanel();
		tableros.add(rival);
		rival.setLayout(new GridLayout(10, 10, 0, 0));
		JPanel usuario = new JPanel();
		tableros.add(usuario);
		usuario.setLayout(new GridLayout(10, 10, 0, 0));
		int i,j;
		for(i=0; i<10;i++) {
			for(j=0; j<10;j++) {
				JLabel lbl1 = new JLabel("");
				lbl1.setBorder(BorderFactory.createLineBorder(Color.white));
				lbl1.setOpaque(true);
				lbl1.setBackground(Color.gray);
				JLabel lbl2 = new JLabel("");
				lbl2.setBorder(BorderFactory.createLineBorder(Color.white));
				lbl2.setOpaque(true);
				lbl2.setBackground(Color.blue);
				rival.add(lbl1);
				usuario.add(lbl2);
			}
		}
	}

}
