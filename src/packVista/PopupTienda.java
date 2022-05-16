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
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
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
import javax.swing.JTextField;
import java.awt.SystemColor;

public class PopupTienda extends JFrame implements Observer{

	private JPanel contentPane;
	private Controlador controlador = null;
	private JLabel labelTesoreria;
	private JLabel labelRonda;
	private JTextField textoEscudo;
	private JTextField textoMisil;
	private JTextField textoRadar;
	private JTextField textoReparacion;
	private int cantEscudos;
	private int cantMisiles;
	private int cantRadares;
	private int cantReparaciones;

	/**
	 * Create the frame.
	 */
	public PopupTienda(int pRonda, int pTesoreria,Observable gestorTablebro,Observable gestorJugadores, Observable tienda) {
		gestorTablebro.addObserver(this);
		gestorJugadores.addObserver(this);
		tienda.addObserver(this);
		
		Controlador c = this.getControlador();
		this.cantEscudos = c.stockEscudosTienda();
		this.cantMisiles = c.stockMisilesTienda();
		this.cantRadares = c.stockRadaresTienda();
		this.cantReparaciones = c.stockReparacionesTienda();
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new GridLayout(3, 4, 0, 0));
		
		labelRonda = new JLabel("Ronda: " + pRonda);
		labelRonda.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelRonda.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(labelRonda);
		
		JLabel labelTienda1 = new JLabel("TIE");
		labelTienda1.setFont(new Font("Tahoma", Font.BOLD, 20));
		labelTienda1.setHorizontalAlignment(SwingConstants.RIGHT);
		getContentPane().add(labelTienda1);
		
		JLabel labelTienda2 = new JLabel("NDA");
		labelTienda2.setHorizontalAlignment(SwingConstants.LEFT);
		labelTienda2.setFont(new Font("Tahoma", Font.BOLD, 20));
		getContentPane().add(labelTienda2);
		
		labelTesoreria = new JLabel("Tesoreria: " + pTesoreria);
		labelTesoreria.setFont(new Font("SansSerif", Font.PLAIN, 16));
		labelTesoreria.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(labelTesoreria);
		
		JLabel fotoEscudo = new JLabel();
		getContentPane().add(fotoEscudo);
		
		JLabel fotoMisil = new JLabel();
		getContentPane().add(fotoMisil);
		
		JLabel fotoRadar = new JLabel();
		getContentPane().add(fotoRadar);
		
		JLabel fotoReparar = new JLabel();
		getContentPane().add(fotoReparar);
		
		JPanel panelEscudo = new JPanel();
		getContentPane().add(panelEscudo);
		
		JButton botonEscudo = new JButton("500 \uD83D\uDCB0");
		panelEscudo.add(botonEscudo);
		botonEscudo.setForeground(new Color(0, 100, 0));
		botonEscudo.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		textoEscudo = new JTextField();
		textoEscudo.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textoEscudo.setBackground(SystemColor.menu);
		textoEscudo.setHorizontalAlignment(SwingConstants.CENTER);
		textoEscudo.setText("Stock: "+this.cantEscudos);
		panelEscudo.add(textoEscudo);
		textoEscudo.setColumns(8);
		botonEscudo.addActionListener(getControlador());
		
		JPanel panelMisil = new JPanel();
		getContentPane().add(panelMisil);
		
		JButton botonMisil = new JButton("200 \uD83D\uDCB0");
		panelMisil.add(botonMisil);
		botonMisil.addActionListener(getControlador());
		botonMisil.setForeground(new Color(0, 100, 0));
		botonMisil.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		textoMisil = new JTextField();
		textoMisil.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textoMisil.setBackground(SystemColor.menu);
		textoMisil.setText("Stock: "+this.cantMisiles);
		textoMisil.setHorizontalAlignment(SwingConstants.CENTER);
		panelMisil.add(textoMisil);
		textoMisil.setColumns(8);
		
		JPanel panelRadar = new JPanel();
		getContentPane().add(panelRadar);
		
		JButton botonRadar = new JButton("600 \uD83D\uDCB0");
		panelRadar.add(botonRadar);
		botonRadar.addActionListener(getControlador());
		botonRadar.setForeground(new Color(0, 100, 0));
		botonRadar.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		textoRadar = new JTextField();
		textoRadar.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textoRadar.setBackground(SystemColor.menu);
		textoRadar.setText("Stock: "+this.cantRadares);
		textoRadar.setHorizontalAlignment(SwingConstants.CENTER);
		panelRadar.add(textoRadar);
		textoRadar.setColumns(8);
		
		this.ponerImagenEscudo(fotoEscudo);
		this.ponerImagenMisil(fotoMisil);
		this.ponerImagenRadar(fotoRadar);
		this.ponerImagenReparacion(fotoReparar);
		
		JPanel panelReparacion = new JPanel();
		getContentPane().add(panelReparacion);
		
		JButton botonReparacion = new JButton("700 \uD83D\uDCB0");
		panelReparacion.add(botonReparacion);
		botonReparacion.addActionListener(getControlador());
		botonReparacion.setForeground(new Color(0, 100, 0));
		botonReparacion.setFont(new Font("SansSerif", Font.PLAIN, 18));
		
		textoReparacion = new JTextField();
		textoReparacion.setFont(new Font("SansSerif", Font.PLAIN, 14));
		textoReparacion.setBackground(SystemColor.menu);
		textoReparacion.setText("Stock: "+this.cantReparaciones);
		textoReparacion.setHorizontalAlignment(SwingConstants.CENTER);
		panelReparacion.add(textoReparacion);
		textoReparacion.setColumns(8);
		
		if(cantEscudos == 0)
		{
			textoEscudo.setForeground(Color.RED);
		}
		
		if(cantMisiles == 0)
		{
			textoMisil.setForeground(Color.RED);
		}
		
		if(cantRadares == 0)
		{
			textoRadar.setForeground(Color.RED);
		}
		
		if(cantReparaciones == 0)
		{
			textoReparacion.setForeground(Color.RED);
		}
	}
	
	private void ponerImagenEscudo(JLabel jl)
	{
		ImageIcon cross = new ImageIcon(this.getClass().getResource("EscudoTienda.gif"));
		ImageIcon crossAdj = new ImageIcon(cross.getImage().getScaledInstance(176, 110, Image.SCALE_DEFAULT));
		jl.setIcon(crossAdj);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
	}
	
	private void ponerImagenMisil(JLabel jl)
	{
		ImageIcon cross = new ImageIcon(this.getClass().getResource("Misil.gif"));
		ImageIcon crossAdj = new ImageIcon(cross.getImage().getScaledInstance(160, 100, Image.SCALE_DEFAULT));
		jl.setIcon(crossAdj);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
	}
	
	private void ponerImagenRadar(JLabel jl)
	{
		ImageIcon cross = new ImageIcon(this.getClass().getResource("Radar.gif"));
		ImageIcon crossAdj = new ImageIcon(cross.getImage().getScaledInstance(288, 180, Image.SCALE_DEFAULT));
		jl.setIcon(crossAdj);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
	}
	
	private void ponerImagenReparacion(JLabel jl)
	{
		ImageIcon cross = new ImageIcon(this.getClass().getResource("Reparacion.gif"));
		ImageIcon crossAdj = new ImageIcon(cross.getImage().getScaledInstance(192, 120, Image.SCALE_DEFAULT));
		jl.setIcon(crossAdj);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
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
			if (e.getActionCommand().equals("500 \uD83D\uDCB0")) 
			{
				Tienda.getTienda().comprar("Escudo", true);
			} 
			else if (e.getActionCommand().equals("200 \uD83D\uDCB0"))
			{
				Tienda.getTienda().comprar("Misil", true);
			}
			else if (e.getActionCommand().equals("600 \uD83D\uDCB0")) {
				Tienda.getTienda().comprar("Radar", true);
			}
			else if (e.getActionCommand().equals("700 \uD83D\uDCB0")) {
				Tienda.getTienda().comprar("Reparacion", true);
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
		
		public int stockEscudosTienda()
		{
			return Tienda.getTienda().getCantEscudos();
		}
		
		public int stockMisilesTienda()
		{
			return Tienda.getTienda().getCantMisiles();
		}
		
		public int stockRadaresTienda()
		{
			return Tienda.getTienda().getCantRadares();
		}
		
		public int stockReparacionesTienda()
		{
			return Tienda.getTienda().getCantReparaciones();
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
		else if(arg instanceof Arma)
		{
			if (arg.equals(Arma.Escudo))
			{
				this.cantEscudos = this.cantEscudos - 1;
				textoEscudo.setText("Stock: " + this.cantEscudos);
				if(this.cantEscudos == 0)
				{
					textoEscudo.setForeground(Color.RED);
				}
				else
				{
					textoEscudo.setForeground(Color.BLACK);
				}
			}
			else if (arg.equals(Arma.Misil))
			{
				this.cantMisiles = this.cantMisiles - 1;
				textoMisil.setText("Stock: " + this.cantMisiles);
				if(this.cantMisiles == 0)
				{
					textoMisil.setForeground(Color.RED);
				}
				else
				{
					textoMisil.setForeground(Color.BLACK);
				}
			}
			else if (arg.equals(Arma.Radar))
			{
				this.cantRadares = this.cantRadares - 1;
				textoRadar.setText("Stock: " + this.cantRadares);
				if(this.cantRadares == 0)
				{
					textoRadar.setForeground(Color.RED);
				}
				else
				{
					textoRadar.setForeground(Color.BLACK);
				}
			}
			else if (arg.equals(Arma.Reparacion))
			{
				this.cantReparaciones = this.cantReparaciones - 1;
				textoReparacion.setText("Stock: " + this.cantReparaciones);
				if(this.cantReparaciones == 0)
				{
					textoReparacion.setForeground(Color.RED);
				}
				else
				{
					textoReparacion.setForeground(Color.BLACK);
				}
			}
		}
			
	}

}
