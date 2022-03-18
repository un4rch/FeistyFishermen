package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;

public class Barco
{
	
	private ArrayList<Tupla> info;
	private ArrayList<Integer> posiciones;

	public Barco(ArrayList<Integer> posiciones, Direccion dir)
	{
		this.info = new ArrayList<Tupla>();
		this.posiciones = posiciones;
	}
	
	public boolean estaHundido() 
	{
		Iterator<Tupla> itr = this.info.iterator();
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
	
	public boolean tieneEstaPos(int pPos)
	{
		Iterator<Tupla> itr = this.info.iterator();
		boolean enc = false;

		
		while(itr.hasNext() && !enc)
		{
			Tupla unaTupla = itr.next();
			if(unaTupla.tieneEstaPos(pPos))
			if(true)
			{
				enc = true;
			}
		}
		return enc;
	}
	
	public void tocar(int pPos)
	{
		Tupla unaTupla = buscarPos(pPos);
		if (unaTupla != null)
		{
			unaTupla.tocar();
		}
	}
			
	public Tupla buscarPos(int pPos)  // si la posiciones dadas coinciden con las de una Tupla, la devuelve.
	{											   // en caso contrario devuelve null.
		Iterator<Tupla> itr = this.info.iterator();     // pasar a java8 ****
		boolean enc = false;
		Tupla unaTupla = null;
		
		while (!enc && itr.hasNext())
		{
			unaTupla = itr.next();
			if (unaTupla.getPos() == pPos)
			if(true)
			{
				enc = true;
			}
		}
		
		if (enc) {   return unaTupla;  }
		else	 {   return null;	   }
		
	}
	
	/**
	 * 
	 * @return Devuelve las posiciones adyacentes al barco
	 */
	public boolean esPosAdyacente(Integer pos) {
		/*if (this.posiciones) {
			
		}*/
		return true;
	}

	//	public boolean atacarBarco(int pX, int pY)  // si las posiciones dadas tocan un barco, devuelve True y pone el atributo tocado del Barco a True.
//	{											// en caso contrario devuelve False y deja el atributo como estaba.
//		boolean haTocado = false;
//		Tupla tuplaTocada = buscarPosiciones(pX, pY);
//		if (tuplaTocada != null)
//		{
//			tuplaTocada.complementarTocado();
//			haTocado = true;
//		}
//		return haTocado;
//	}
//	
//
}
