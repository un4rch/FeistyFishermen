  package packVista;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import packModeloControlador.Arma;
import packModeloControlador.Arsenal;
import packModeloControlador.Combate;
import packModeloControlador.Dificultad;
import packModeloControlador.Direccion;
import packModeloControlador.ListaJugadores;
import packModeloControlador.ModeloTablero;
import packModeloControlador.Tienda;

import java.util.ArrayList;
import packModeloControlador.Tupla;

import java.util.Observable;
import java.util.Observer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class BaseTablero extends JFrame implements Observer {

	private JPanel main;
	private final ButtonGroup acciones = new ButtonGroup();
	private final ButtonGroup tipoBarco = new ButtonGroup();
	private ArrayList<JLabel> listaDirecciones;
	private ArrayList<JLabel> listaRival;
	private ArrayList<JLabel> listaUsuario;
	private Controlador controlador = null;
	private int fragDisp;
	private int destDisp;
	private int subDisp;
	private int portDisp;
	private JPanel barcos;
	private JLabel labelPortDisp;
	private JLabel labelSubDisp;
	private JLabel labelDestDisp;
	private JLabel labelFragDisp;
	private JLabel escudosDisp;
	private JLabel misilesDisp;
	private JLabel radaresDisp;
	private JLabel reparacionDisp;
	private JRadioButton bomba;
	private JRadioButton escudo;
	private JRadioButton misil;
	private JRadioButton radar;
	private JRadioButton reparacion;
	private JRadioButton portaaviones;
	private JRadioButton submarino;
	private JRadioButton destructor;
	private JRadioButton fragata;
	private JLabel arriba;
	private JLabel abajo;
	private JLabel izquierda;
	private JLabel derecha;
	private JPanel inventario;
	private JLabel bombasDisp;

	/**
	 * Launch the application.
	 */


	public BaseTablero(Observable gestorTablebro,Observable gestorJugadores, Observable tTienda) {
		this.fragDisp = 4;
		this.destDisp = 3;
		this.subDisp = 2;
		this.portDisp = 1;
		gestorTablebro.addObserver(this);
		gestorJugadores.addObserver(this);
		tTienda.addObserver(this);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 596, 426);
		main = new JPanel();
		main.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(main);
		main.setLayout(new BorderLayout(0, 0));
		
		this.barcos = new JPanel();
		main.add(this.barcos, BorderLayout.EAST);
		this.barcos.setLayout(new GridLayout(0, 1, 0, 0));
		
		portaaviones = new JRadioButton("Portaaviones");
		portaaviones.addActionListener(getControlador());
		tipoBarco.add(portaaviones);
		this.barcos.add(portaaviones);

		this.labelPortDisp = new JLabel("Disponibles: "+  this.portDisp +"/1");
		this.barcos.add(this.labelPortDisp);
		
		submarino = new JRadioButton("Submarino");
		submarino.addActionListener(getControlador());
		tipoBarco.add(submarino);
		this.barcos.add(submarino);
		
		this.labelSubDisp = new JLabel("Disponibles: "+  this.subDisp +"/2");
		this.barcos.add(this.labelSubDisp);

		destructor = new JRadioButton("Destructor");
		destructor.addActionListener(getControlador());
		
		tipoBarco.add(destructor);
		this.barcos.add(destructor);

		this.labelDestDisp = new JLabel("Disponibles: " + this.destDisp + "/3");
		this.barcos.add(this.labelDestDisp);
		
		fragata = new JRadioButton("Fragata");
		fragata.addActionListener(getControlador());
		
		tipoBarco.add(fragata);
		this.barcos.add(fragata);
		
		this.labelFragDisp = new JLabel("Disponibles: " + this.fragDisp + "/4");
		this.barcos.add(this.labelFragDisp);
		
		JPanel direcciones = new JPanel();
		this.barcos.add(direcciones);
		direcciones.setLayout(new GridLayout(3, 3, 0, 0));
		this.listaDirecciones = new ArrayList<JLabel>();
		
		JLabel blank1 = new JLabel("");
		direcciones.add(blank1);
		
		arriba = new JLabel("A");
		arriba.setHorizontalAlignment(SwingConstants.CENTER);
		arriba.addMouseListener(getControlador());
		direcciones.add(arriba);
		listaDirecciones.add(arriba);
		
		JLabel blank2 = new JLabel("");
		direcciones.add(blank2);
		
		izquierda = new JLabel("<--");
		izquierda.setHorizontalAlignment(SwingConstants.CENTER);
		izquierda.addMouseListener(getControlador());
		direcciones.add(izquierda);
		listaDirecciones.add(izquierda);
		
		JLabel blank3 = new JLabel("");
		direcciones.add(blank3);
		
		derecha = new JLabel("-->");
		derecha.setHorizontalAlignment(SwingConstants.CENTER);
		derecha.addMouseListener(getControlador());
		direcciones.add(derecha);
		listaDirecciones.add(derecha);
		
		JLabel blank4 = new JLabel("");
		direcciones.add(blank4);
		
		abajo = new JLabel("V");
		abajo.setHorizontalAlignment(SwingConstants.CENTER);
		abajo.addMouseListener(getControlador());
		direcciones.add(abajo);
		listaDirecciones.add(abajo);
		
		JLabel blank5 = new JLabel("");
		direcciones.add(blank5);
		
		inventario = new JPanel();
		main.add(inventario, BorderLayout.WEST);
		inventario.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton tienda = new JButton("Tienda");
		tienda.addActionListener(controlador);
		inventario.add(tienda);
		
		JLabel espacio = new JLabel("");
		inventario.add(espacio);
		
		bomba = new JRadioButton("Bomba");
		bomba.addActionListener(getControlador());
		acciones.add(bomba);
		inventario.add(bomba);
		
		escudo = new JRadioButton("Escudo");
		escudo.addActionListener(getControlador());
		
		bombasDisp = new JLabel("");
		inventario.add(bombasDisp);
		acciones.add(escudo);
		inventario.add(escudo);
		
		misil = new JRadioButton("Misil");
		misil.addActionListener(getControlador());
		
		escudosDisp = new JLabel("Disponibles: 1");
		inventario.add(escudosDisp);
		acciones.add(misil);
		inventario.add(misil);
		
		radar = new JRadioButton("Radar");
		radar.addActionListener(getControlador());
		
		misilesDisp = new JLabel("Disponibles: 2");
		inventario.add(misilesDisp);
		acciones.add(radar);
		inventario.add(radar);
		
		radaresDisp = new JLabel("Disponibles: 1");
		inventario.add(radaresDisp);
		
		reparacion = new JRadioButton("Reparacion");
		reparacion.addActionListener(getControlador());
		reparacionDisp = new JLabel("Disponibles: 2");
		acciones.add(reparacion);
		inventario.add(reparacion);
		inventario.add(reparacionDisp);
		
		JPanel tableros = new JPanel();
		main.add(tableros, BorderLayout.CENTER);
		tableros.setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel rival = new JPanel();
		tableros.add(rival);
		rival.setLayout(new GridLayout(10, 10, 0, 0));
		JPanel usuario = new JPanel();
		tableros.add(usuario);
		usuario.setLayout(new GridLayout(10, 10, 0, 0));
		listaRival = new ArrayList<JLabel>();
		listaUsuario = new ArrayList<JLabel>();
		int i,j;
		for(i=0; i<10;i++) {
			for(j=0; j<10;j++) {
				JLabel lbl1 = new JLabel("");
				lbl1.setBorder(BorderFactory.createLineBorder(Color.white));
				lbl1.setOpaque(true);
				lbl1.setBackground(Color.gray);
				listaRival.add(lbl1);
				lbl1.addMouseListener(getControlador());
				JLabel lbl2 = new JLabel("");
				lbl2.setBorder(BorderFactory.createLineBorder(Color.white));
				lbl2.setOpaque(true);
				lbl2.setBackground(Color.blue);
				listaUsuario.add(lbl2);
				lbl2.addMouseListener(getControlador());
				rival.add(lbl1);
				usuario.add(lbl2);
			}
		}
		bomba.setEnabled(false);
		escudo.setEnabled(false);
		misil.setEnabled(false);
		radar.setEnabled(false);
		reparacion.setEnabled(false);
		
		bombasDisp.setForeground(Color.LIGHT_GRAY);
		escudosDisp.setForeground(Color.LIGHT_GRAY);
		misilesDisp.setForeground(Color.LIGHT_GRAY);
		radaresDisp.setForeground(Color.LIGHT_GRAY);
		reparacionDisp.setForeground(Color.LIGHT_GRAY);
		
	}
	
	private Controlador getControlador() {
		if (controlador==null)
		{
			controlador = new Controlador();
			ModeloTablero.getMiModeloTablero().setDireccion(null); //No dejar que se ponga un barco al principio (arreglo de bug)
		}
		return controlador;
	}
	
	private class Controlador implements MouseListener,ActionListener {
		@Override
		public void mouseClicked(MouseEvent e) {
			if (((JLabel)e.getComponent()).getText().equals("A")) {
				ModeloTablero.getMiModeloTablero().setDireccion(Direccion.Arriba);
				for (JLabel direccion : listaDirecciones) {								//TODO JAVA8
					direccion.setForeground(Color.BLACK);
				}
				((JLabel)e.getComponent()).setForeground(Color.GREEN);
			} else if (((JLabel)e.getComponent()).getText().equals("-->")) {
				ModeloTablero.getMiModeloTablero().setDireccion(Direccion.Derecha);
				for (JLabel direccion : listaDirecciones) {
					direccion.setForeground(Color.BLACK);
				}
				((JLabel)e.getComponent()).setForeground(Color.GREEN);
			} else if (((JLabel)e.getComponent()).getText().equals("V")) {
				ModeloTablero.getMiModeloTablero().setDireccion(Direccion.Abajo);
				for (JLabel direccion : listaDirecciones) {
					direccion.setForeground(Color.BLACK);
				}
				((JLabel)e.getComponent()).setForeground(Color.GREEN);
			} else if (((JLabel)e.getComponent()).getText().equals("<--")) {
				ModeloTablero.getMiModeloTablero().setDireccion(Direccion.Izquierda);
				for (JLabel direccion : listaDirecciones) {
					direccion.setForeground(Color.BLACK);
				}
				((JLabel)e.getComponent()).setForeground(Color.GREEN);
			} else if (listaUsuario.contains((JLabel)e.getComponent())) {
				packModeloControlador.ModeloTablero.getMiModeloTablero().casillaUsuarioPulsada(listaUsuario.indexOf((JLabel)e.getComponent()));
			} else if (listaRival.contains((JLabel)e.getComponent())) {
				if (!ModeloTablero.getMiModeloTablero().partidaLista()) { //No se han puesto los barcos usuario
					System.out.println("Para atacar hay que poner todos los barcos");
				} else {
					packModeloControlador.ModeloTablero.getMiModeloTablero().casillaRivalPulsada(listaRival.indexOf((JLabel)e.getComponent()));
					ModeloTablero.getMiModeloTablero().setTipoArma(Arma.Bomba);
				}
			}
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

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Portaaviones")) {
				ModeloTablero.getMiModeloTablero().setTipoBarco(4);
			} else if (e.getActionCommand().equals("Submarino")) {
				ModeloTablero.getMiModeloTablero().setTipoBarco(3);
			} else if (e.getActionCommand().equals("Destructor")) {
				ModeloTablero.getMiModeloTablero().setTipoBarco(2);
			} else if (e.getActionCommand().equals("Fragata")) {
				ModeloTablero.getMiModeloTablero().setTipoBarco(1);
			} else if (e.getActionCommand().equals("Bomba")) {
				ModeloTablero.getMiModeloTablero().setTipoArma(Arma.Bomba);
			} else if (e.getActionCommand().equals("Escudo")) {
				ModeloTablero.getMiModeloTablero().setTipoArma(Arma.Escudo);
			} else if (e.getActionCommand().equals("Misil")) {
				ModeloTablero.getMiModeloTablero().setTipoArma(Arma.Misil);
			} else if (e.getActionCommand().equals("Radar")) {
				ModeloTablero.getMiModeloTablero().setTipoArma(Arma.Radar);
			} else if (e.getActionCommand().equals("Reparacion")) {
				ModeloTablero.getMiModeloTablero().setTipoArma(Arma.Reparacion);
			} else if (e.getActionCommand().equals("Tienda")) {
				try 
				{
					int ronda = ModeloTablero.getMiModeloTablero().getRonda();
					int tesoreria = ListaJugadores.getMiListaJ().getDineroUsuario();
					PopupTienda frame = new PopupTienda(ronda,tesoreria,ListaJugadores.getMiListaJ(),Combate.getMiCombate(), Tienda.getTienda());
					frame.setVisible(true);
				} catch (Exception exc) 
				{
					exc.printStackTrace();
				}	
			}
			
		}
		
	}
	
	/**
	 * Se reciben los datos del observable para actualizarlos en la vista
	 */
	public void update(Observable obserable, Object arg) 
	{
		if (arg instanceof ArrayList) 
		{
			for (Integer pos : (ArrayList<Integer>)arg) 
			{ //Update barcos en la vista
				JLabel jl = listaUsuario.get(pos);
				this.ponerImagenBarco(jl);
			}

			int tamano = ((ArrayList) arg).size();
			if (tamano==1)
			{
				this.fragDisp = this.fragDisp-1;
				this.labelFragDisp.setText("Disponibles: " + this.fragDisp + "/4");
				if (this.fragDisp == 0)
				{
					this.labelFragDisp.setForeground(Color.RED);
				}
			} else if (tamano==2)
			{
				this.destDisp = this.destDisp-1;
				this.labelDestDisp.setText("Disponibles: " + this.destDisp + "/3");
				if (this.destDisp == 0)
				{
					this.labelDestDisp.setForeground(Color.RED);
				}
			} else if (tamano==3)
			{
				this.subDisp = this.subDisp-1;
				this.labelSubDisp.setText("Disponibles: " + this.subDisp + "/2");
				if (this.subDisp == 0)
				{
					this.labelSubDisp.setForeground(Color.RED);
				}
			} else if (tamano==4)
			{
				this.portDisp = this.portDisp-1;
				this.labelPortDisp.setText("Disponibles: " + this.portDisp + "/1");
				if (this.portDisp == 0)
				{
					this.labelPortDisp.setForeground(Color.RED);
				}
			}
			if (this.fragDisp == 0 && this.subDisp == 0 && this.destDisp == 0 && this.portDisp == 0) //partida lista se ocultan los labels
			{				
				this.barcos.remove(this.labelDestDisp);
				this.barcos.remove(this.labelFragDisp);
				this.barcos.remove(this.labelPortDisp);
				this.barcos.remove(this.labelSubDisp);
				
				bomba.setEnabled(true);
				escudo.setEnabled(true);
				misil.setEnabled(true);
				radar.setEnabled(true);
				reparacion.setEnabled(true);
				
				portaaviones.setEnabled(false);
				submarino.setEnabled(false);
				destructor.setEnabled(false);
				fragata.setEnabled(false);
				
				arriba.setEnabled(false);
				abajo.setEnabled(false);
				izquierda.setEnabled(false);
				derecha.setEnabled(false);
				
				bombasDisp.setForeground(Color.BLACK);
				escudosDisp.setForeground(Color.BLACK);
				misilesDisp.setForeground(Color.BLACK);
				radaresDisp.setForeground(Color.BLACK);
				reparacionDisp.setForeground(Color.BLACK);
			}
		} 
		else if (arg instanceof Boolean) 
		{
			try 
			{
				PopupGanador frame = new PopupGanador((boolean)arg);
				frame.setVisible(true);
			} catch (Exception e) 
			{
				e.printStackTrace();
			}	
		}
		else if (arg instanceof String) {
			String[] posis = ((String)arg).split("_");
			if (posis[2].equals("A"))
			{
				if (posis[0].equals("0"))
				{
					listaUsuario.get(Integer.parseInt(posis[1])).setBackground(Color.cyan);

				}
				else
				{
					listaRival.get(Integer.parseInt(posis[1])).setBackground(Color.blue);
				}
			}
			else if (posis[2].equals("B"))
			{
				if (posis[0].equals("0"))
				{
					JLabel jl = listaUsuario.get(Integer.parseInt(posis[1]));
					this.ponerImagenBarco(jl);
				}
				else
				{
					JLabel jl = listaRival.get(Integer.parseInt(posis[1]));
					this.ponerImagenBarco(jl);
				}
			}
			else if (posis[2].equals("E"))
			{
				if (posis[0].equals("0"))
				{
					JLabel jl = listaUsuario.get(Integer.parseInt(posis[1]));
					this.ponerImagenEscudo(jl);
				}
				else
				{
					JLabel jl = listaRival.get(Integer.parseInt(posis[1]));
					this.ponerImagenEscudo(jl);
				}
			}
			else if (posis[2].equals("ED"))
			{
				if (posis[0].equals("0"))
				{
					JLabel jl = listaUsuario.get(Integer.parseInt(posis[1]));
					this.ponerImagenEscudoTocado(jl);
				}
				else
				{
					JLabel jl = listaRival.get(Integer.parseInt(posis[1]));
					this.ponerImagenEscudoTocado(jl);
				}
			}
			else if (posis[2].equals("T"))
			{
				if (posis[0].equals("0"))
				{
					JLabel jl = listaUsuario.get(Integer.parseInt(posis[1]));
					this.ponerImagenTocadoUsuario(jl);
				}
				else
				{
					JLabel jl = listaRival.get(Integer.parseInt(posis[1]));
					this.ponerImagenTocadoRival(jl);
				}
			}
			else //posis[2].equals("H")
			{
				if (posis[0].equals("0"))
				{
					JLabel jl = listaUsuario.get(Integer.parseInt(posis[1]));
					this.ponerImagenHundido(jl);
				}
				else
				{
					JLabel jl = listaRival.get(Integer.parseInt(posis[1]));
					this.ponerImagenHundido(jl);
				}
			}
		} 
		else if (arg instanceof Character)
		{
			Arsenal arsUsuario = ListaJugadores.getMiListaJ().getArsenalUsuario();
			if (arg.equals('E')) //escudo
			{
				int escudos = arsUsuario.getEscudos();
				escudosDisp.setText("Disponibles: " + escudos);
				if (escudos == 0)
				{
					this.escudosDisp.setForeground(Color.RED);
				}
				else
				{
					this.escudosDisp.setForeground(Color.BLACK);
				}
			}
			else if (arg.equals('R')) //radar
			{
				int radares = arsUsuario.getRadares();
				radaresDisp.setText("Disponibles: " + radares);
				if (radares == 0)
				{
					this.radaresDisp.setForeground(Color.RED);
				}
				else
				{
					this.radaresDisp.setForeground(Color.BLACK);
				}
			}
			else if (arg.equals('M')) //misil
			{
				int misiles = arsUsuario.getMisiles();
				misilesDisp.setText("Disponibles: " + misiles);
				if (misiles == 0)
				{
					this.misilesDisp.setForeground(Color.RED);
				}
				else
				{
					this.misilesDisp.setForeground(Color.BLACK);
				}
				
			}
			else if (arg.equals('B')) //reparacion
			{
				int reparaciones = arsUsuario.getReparaciones();
				reparacionDisp.setText("Disponibles: " + reparaciones);
				if (reparaciones == 0)
				{
					this.reparacionDisp.setForeground(Color.RED);
				}
				else
				{
					this.reparacionDisp.setForeground(Color.BLACK);
				}
			}
		}
	}

	private void ponerImagenBarco(JLabel jl)
	{
		ImageIcon cross = new ImageIcon(this.getClass().getResource("Barco.png"));
		ImageIcon crossAdj = new ImageIcon(cross.getImage().getScaledInstance(80, 50, Image.SCALE_DEFAULT));
		jl.setIcon(crossAdj);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
	}

	private void ponerImagenTocadoUsuario(JLabel jl)
	{
		ImageIcon cross = new ImageIcon(this.getClass().getResource("TocadoUsuario.gif"));
		ImageIcon crossAdj = new ImageIcon(cross.getImage().getScaledInstance(80, 50, Image.SCALE_DEFAULT));
		jl.setIcon(crossAdj);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
	}

	private void ponerImagenTocadoRival(JLabel jl)
	{
		ImageIcon cross = new ImageIcon(this.getClass().getResource("TocadoRival.gif"));
		ImageIcon crossAdj = new ImageIcon(cross.getImage().getScaledInstance(80, 50, Image.SCALE_DEFAULT));
		jl.setIcon(crossAdj);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
	}

	private void ponerImagenHundido(JLabel jl)
	{
		ImageIcon cross = new ImageIcon(this.getClass().getResource("Hundido.png"));
		ImageIcon crossAdj = new ImageIcon(cross.getImage().getScaledInstance(80, 50, Image.SCALE_DEFAULT));
		jl.setIcon(crossAdj);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
	}

	private void ponerImagenEscudo(JLabel jl)
	{
		ImageIcon cross = new ImageIcon(this.getClass().getResource("Escudo.png"));
		ImageIcon crossAdj = new ImageIcon(cross.getImage().getScaledInstance(80, 50, Image.SCALE_DEFAULT));
		jl.setIcon(crossAdj);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
	}

	private void ponerImagenEscudoTocado(JLabel jl)
	{
		ImageIcon cross = new ImageIcon(this.getClass().getResource("EscudoTocado.png"));
		ImageIcon crossAdj = new ImageIcon(cross.getImage().getScaledInstance(80, 50, Image.SCALE_DEFAULT));
		jl.setIcon(crossAdj);
		jl.setHorizontalAlignment(JLabel.CENTER);
		jl.setVerticalAlignment(JLabel.CENTER);
	}

}
