package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import packModeloControlador.Combate;
import packModeloControlador.Dificultad;
import packModeloControlador.Direccion;
import packModeloControlador.ListaJugadores;
import packModeloControlador.ModeloTablero;
import packModeloControlador.Rival;
import packModeloControlador.Tienda;

public class MainMenu extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame mainMenu = null;
	private ButtonGroup difGroup = null;
	private Controlador controlador;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainMenu()
	{
		this.mainMenu = new JFrame();
		mainMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel menu = new JPanel(new BorderLayout());
		mainMenu.setSize(600,400);
		mainMenu.add(menu);
		JPanel dificultadesPanel = new JPanel(new GridLayout(3,0));
		JPanel dificultades = new JPanel();
		dificultades.setLayout(new BoxLayout(dificultades, BoxLayout.Y_AXIS));
		ButtonGroup difGroup = new ButtonGroup();
		JRadioButton facil = new JRadioButton("Facil");
		facil.setActionCommand("Facil");
		dificultades.add(facil);
		difGroup.add(facil);
		JRadioButton medio = new JRadioButton("Medio");
		medio.setActionCommand("Medio");
		dificultades.add(medio);
		difGroup.add(medio);
		JRadioButton dificil = new JRadioButton("Dificil");
		dificil.setActionCommand("Dificil");
		dificultades.add(dificil);
		difGroup.add(dificil);
		JRadioButton demente = new JRadioButton("Demente");
		demente.setActionCommand("Demente");
		dificultades.add(demente);
		difGroup.add(demente);
		
		this.difGroup = difGroup;
		JButton b = new JButton("Play");
		b.addActionListener(getControlador());
		dificultadesPanel.add(new JPanel());
		dificultadesPanel.add(dificultades);
		dificultadesPanel.add(new JPanel());
		JPanel highscore = new JPanel();
		highscore.add(new JLabel("HighScores"));
		menu.add(b, BorderLayout.CENTER);
		menu.add(dificultadesPanel, BorderLayout.WEST);
		menu.add(highscore, BorderLayout.EAST);
		mainMenu.setVisible(true);
	}
	
	private Controlador getControlador() {
		if (controlador==null)
		{
			controlador = new Controlador();
		}
		return controlador;
	}
	
	private class Controlador implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("Play")) {
				
				String dif = "";
				try {
					dif = difGroup.getSelection().getActionCommand();
				} catch (Exception ex) {
					dif = "";
				}
				if (!dif.equals("")) {
					Rival rival = (Rival)ListaJugadores.getMiListaJ().getUnJugador(1);
					if (dif.equals("Facil")) {
						rival.setDificultad(Dificultad.Facil);
					} else if (dif.equals("Medio")) {
						rival.setDificultad(Dificultad.Medio);
					} else if (dif.equals("Difil")) {
						rival.setDificultad(Dificultad.Dificil);
					} else if (dif.equals("Demente")) {
						rival.setDificultad(Dificultad.Demente);
					}
					mainMenu.setVisible(false);
					BaseTablero frame = new BaseTablero(ListaJugadores.getMiListaJ(),Combate.getMiCombate(), Tienda.getTienda());
					frame.setVisible(true);
					//Cerrar mainMenu
			  }
			}
		}		
	}	
}
