package packVista;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import packModeloControlador.Dificultad;
import packModeloControlador.ModeloTablero;
//import packVista.BaseTablero.Controlador;

public class MainMenu extends JFrame implements Observer
{
	private JFrame mainMenu = null;
	private ButtonGroup difGroup = null;
	private Controlador controlador;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu(ModeloTablero.getMiModeloTablero());
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public MainMenu(Observable gestorTablebro)
	{
		gestorTablebro.addObserver(this);
		
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
	
	public void update(Observable obserable, Object arg) {
		
	}
	
	private Controlador getControlador() {
		if (controlador==null)
		{
			controlador = new Controlador();
		}
		return controlador;
	}
	
	private class Controlador implements MouseListener,ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getActionCommand().equals("Play")) {
				
				String dif = "";
				try {
					dif = difGroup.getSelection().getActionCommand();
				} catch (Exception ex) {
					dif = "";
				}
				if (!dif.equals("")) {
					if (dif.equals("Facil")) {
						ModeloTablero.getMiModeloTablero().setDificultad(Dificultad.Facil);
					} else if (dif.equals("Medio")) {
						ModeloTablero.getMiModeloTablero().setDificultad(Dificultad.Medio);
					} else if (dif.equals("Difil")) {
						ModeloTablero.getMiModeloTablero().setDificultad(Dificultad.Dificil);
					} else if (dif.equals("Demente")) {
						ModeloTablero.getMiModeloTablero().setDificultad(Dificultad.Demente);
					}
					mainMenu.setVisible(false);
					BaseTablero frame = new BaseTablero(ModeloTablero.getMiModeloTablero());
					frame.setVisible(true);
					//Cerrar mainMenu
					
				
			  }
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
	
}
