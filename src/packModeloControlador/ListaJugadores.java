package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaJugadores
{
	private static ListaJugadores miLista;
	private ArrayList<Jugador> listaJ;
	
	private ListaJugadores()
	{
		listaJ = new ArrayList<Jugador>();
		listaJ.add(new Usuario());
		listaJ.add(new Rival());
	}
	
	public static ListaJugadores getMiListaJ()
	{
		if (ListaJugadores.miLista==null)
		{
			ListaJugadores.miLista = new ListaJugadores();
		}
		return ListaJugadores.miLista;
	}
	
	public Jugador getUnJugador(int pNum)
	{
		return ListaJugadores.getMiListaJ().listaJ.get(pNum);
	}
	
	/**
	 * se juega la ronda cuando el jugador pulsa una casilla despues de haber puesto todos los barcos
	 * @param pPos la casilla que ha pulsado el jugador
	 * @param pArma el arma que habia seleccionada
	 * @return devuelve un booleano indicando si se ha terminado la partida
	 */
	public boolean jugarRonda(int pPos, Arma pArma)	//TODO JAVA 8 (aunque sean solo 2 jugadores, hay que pensar en grande como dijo Ander)
	{
		this.listaJ.get(0).actuar(pPos,pArma);
		this.listaJ.get(1).actuar(pPos,pArma);

		return this.partidaTerminada();
	}
	
	/**
	 * comprueba si el numero de casillas restantes de algun jugador es igual a 0
	 * @return si algun jugador tiene 0 casillas restantes, se da por terminada la partida y se declara ganador
	 */
	private boolean partidaTerminada()	//TODO JAVA 8
	{
		boolean hayGanador = false;
		Iterator<Jugador> itr = this.listaJ.iterator();
		while (itr.hasNext() && !hayGanador)
		{
			Jugador act = itr.next();
			hayGanador = act.tieneLaFlotaHundida();
		}
		//al final de cada ronda se mira a ver si alguno de los jugadores se ha quedado sin casillas sin tocar/hundir
		return hayGanador;
	}
}
