package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import packModeloControlador.Arma;
import packModeloControlador.Arsenal;
import packModeloControlador.Combate;
import packModeloControlador.ListaJugadores;
import packModeloControlador.ModeloTablero;
import packModeloControlador.Tienda;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.Font;
import java.awt.Color;

public class PopupTienda extends JFrame implements Observer{

	private JPanel contentPane;
	private Controlador controlador = null;
	private JLabel labelTesoreria;
	private JLabel labelRonda;

	/**
	 * Create the frame.
	 */
	public PopupTienda(int pRonda, int pTesoreria,Observable gestorTablebro,Observable gestorJugadores, Observable tienda) {
		
		gestorTablebro.addObserver(this);
		gestorJugadores.addObserver(this);
		tienda.addObserver(this);
		
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new GridLayout(3, 3, 0, 0));
		
		labelRonda = new JLabel("Ronda: " + pRonda);
		labelRonda.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(labelRonda);
		
		JLabel lblNewLabel_4 = new JLabel("TIENDA");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel_4);
		
		labelTesoreria = new JLabel("Tesoreria: " + pTesoreria);
		labelTesoreria.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(labelTesoreria);
		
		JLabel fotoEscudo = new JLabel("ESCUDO");
		fotoEscudo.setForeground(new Color(0, 100, 0));
		fotoEscudo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		fotoEscudo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(fotoEscudo);
		
		JLabel fotoMisil = new JLabel("MISIL");
		fotoMisil.setForeground(new Color(0, 100, 0));
		fotoMisil.setFont(new Font("SansSerif", Font.PLAIN, 18));
		fotoMisil.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(fotoMisil);
		
		JButton botonEscudo = new JButton("40 \uD83D\uDCB0");
		botonEscudo.setForeground(new Color(0, 100, 0));
		botonEscudo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		botonEscudo.addActionListener(getControlador());
		
		JLabel fotoRadar = new JLabel("RADAR");
		fotoRadar.setForeground(new Color(0, 100, 0));
		fotoRadar.setFont(new Font("SansSerif", Font.PLAIN, 18));
		fotoRadar.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(fotoRadar);
		getContentPane().add(botonEscudo);
		
		JButton botonMisil = new JButton("100 \uD83D\uDCB0");
		botonMisil.addActionListener(getControlador());
		botonMisil.setForeground(new Color(0, 100, 0));
		botonMisil.setFont(new Font("SansSerif", Font.PLAIN, 18));
		getContentPane().add(botonMisil);
		
		JButton botonRadar = new JButton("300 \uD83D\uDCB0");
		botonRadar.addActionListener(getControlador());
		botonRadar.setForeground(new Color(0, 100, 0));
		botonRadar.setFont(new Font("SansSerif", Font.PLAIN, 18));
		getContentPane().add(botonRadar);
	}

	private Controlador getControlador() 
	{
		if (controlador==null)
		{
			controlador = new Controlador();
		}
		return controlador;
	}
	
	private class Controlador implements MouseListener, ActionListener 
	{

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("40 \uD83D\uDCB0")) 
			{
				Tienda.getTienda().comprar("Escudo", true);
			} 
			else if (e.getActionCommand().equals("100 \uD83D\uDCB0"))
			{
				Tienda.getTienda().comprar("Misil", true);
			}
			else if (e.getActionCommand().equals("300 \uD83D\uDCB0")) {
				Tienda.getTienda().comprar("Radar", true);
			}
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
	@Override
	public void update(Observable o, Object arg) {
		if (arg instanceof Integer)
		{
			labelTesoreria.setText("Tesoreria: " + arg);
		}
		else if(arg instanceof Character)
		{
			if (arg.equals('a')) //cada vez que acaba una ronda, se actualiza el contador de Ronda de la tienda y el dinero del jugador
			{
				labelRonda.setText("Ronda: " + ModeloTablero.getMiModeloTablero().getRonda());
				labelTesoreria.setText("Tesoreria: " + ListaJugadores.getMiListaJ().getDineroUsuario());
			}
		}
		
	}

}
