package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;

public class Barco
{
	
	private ArrayList<Tupla> info;
	
	public Barco()
	{
		this.info = new ArrayList<Tupla>();
	}
	
	public boolean atacarBarco(int pX, int pY)  // si las posiciones dadas tocan un barco, devuelve True y pone el atributo tocado del Barco a True.
	{											// en caso contrario devuelve False y deja el atributo como estaba.
		boolean haTocado = false;
		Tupla tuplaTocada = buscarPosiciones(pX, pY);
		if (tuplaTocada != null)
		{
			tuplaTocada.complementarTocado();
			haTocado = true;
		}
		return haTocado;
	}
	
	
	public Tupla buscarPosiciones(int pX, int pY)  // si la posiciones dadas coinciden con las de una Tupla, la devuelve.
	{											   // en caso contrario devuelve null.
		Iterator<Tupla> itr = this.info.iterator();     // pasar a java8 ****
		boolean enc = false;
		Tupla unaTupla = null;
		
		while (!enc && itr.hasNext())
		{
			unaTupla = itr.next();
			if ((unaTupla.getX() == pX) && (unaTupla.getY() == pY))
			{
				enc = true;
			}
		}
		
		if (enc) {   return unaTupla;  }
		else	 {   return null;	   }
		
	}
	
	public boolean estaHundido() 
	{
		Iterator<Tupla> itr = this.info.iterator()
		boolean salir = false;
		
		while(itr.hasNext() && !salir)
		{
			Tupla unaTupla = itr.next();
			if(! unaTupla.estaTocado())
			{
				salir = true;
			}
		}
		return !salir; 
	}

}
