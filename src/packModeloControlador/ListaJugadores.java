package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class ListaJugadores extends Observable
{
	private static ListaJugadores miLista;
	private ArrayList<Jugador> listaJ;
	
	private ListaJugadores()
	{
		listaJ = new ArrayList<Jugador>();
		listaJ.add(new Usuario());
		listaJ.add(new Rival());

		this.listaJ.get(1).anadirBarco(1, Direccion.Abajo, 1);
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
	public boolean jugarRonda(int pPos, Arma pArma)
	{
		listaJ.stream().forEach(p->p.actuar(pPos,pArma));
		setChanged();
		notifyObservers('a');
		return this.partidaTerminada();	
	}
	
	/**
	 * comprueba si el numero de casillas restantes de algun jugador es igual a 0
	 * @return si algun jugador tiene 0 casillas restantes, se da por terminada la partida y se declara ganador
	 */
	private boolean partidaTerminada()
	{
		boolean hayGanador = false;
		Iterator<Jugador> itr = this.listaJ.iterator();
		int cont = 0;
		while (itr.hasNext() && !hayGanador)
		{
			Jugador act = itr.next();
			hayGanador = act.tieneLaFlotaHundida();
			if (!hayGanador)
			{
				cont++;
			}
		}
		//al final de cada ronda se mira a ver si alguno de los jugadores se ha quedado sin casillas sin tocar/hundir
		if (hayGanador)
		{
			setChanged();
			if (cont == 0)
			{
				notifyObservers(false);
			}
			else
			{
				notifyObservers(true);
			}
		}
		return hayGanador;
	}

	public void repartirDinero(int pRonda)
	{
		this.listaJ.stream().forEach(j -> j.anadirDinero(pRonda));
	}
	
	public boolean comprobarYRestarDineroUsuario(int pPrecio)
	{
		Usuario unUsuario = (Usuario) this.getUnJugador(0);
		Integer dineroActualUsuario = unUsuario.comprobarYRestarDineroUsuario(pPrecio);
		
		setChanged();
		if (dineroActualUsuario<0)
		{
			return false;
		}
		else
		{
			notifyObservers(dineroActualUsuario);
			return true;
		}
		
	}
	
	public int getDineroUsuario()
	{
		Usuario unUsuario = (Usuario) this.getMiListaJ().getUnJugador(0);
		return unUsuario.getDineroUsuario();
	}
	
	public Arsenal getArsenalUsuario()
	{
		Usuario unUsuario = (Usuario) this.getMiListaJ().getUnJugador(0);
		return unUsuario.getArsenal();		
	}
	
	public boolean consumirArma(Arma pArma, boolean pEsUsuario)
	{
		if (pEsUsuario)
		{
			return this.getUnJugador(0).consumirArma(pArma);
		}
		else
		{
			return  this.getUnJugador(1).consumirArma(pArma);
		}
	}
	
	public void comprarArma(Arma pArma, boolean pEsUsuario)
	{
		if (pEsUsuario)
		{
			this.getUnJugador(0).comprarArma(pArma);
		}
		else
		{
			this.getUnJugador(1).comprarArma(pArma);			
		}
	}
}
