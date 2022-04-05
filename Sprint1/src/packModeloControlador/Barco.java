package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class Barco
{
	
	private ArrayList<Tupla> info;

	public Barco(ArrayList<Integer> posiciones)
	{
		this.info = new ArrayList<Tupla>();
		Iterator<Integer> itr = posiciones.iterator();
		while(itr.hasNext())
		{
			Integer act = itr.next();
			info.add(new Tupla(act,false));
		}
	}
	
	public int getTamano() {
		return (this.info.size());
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
			enc = unaTupla.tieneEstaPos(pPos);
		}
		return enc;
	}
	
	public Tupla getPosAleatoria() {
		return this.info.get(new Random().nextInt(this.info.size()));
	}
	
	public void tocar(int pPos)
	{
		Tupla unaTupla = buscarPos(pPos);
		if (unaTupla != null)
		{
			unaTupla.tocar();
		}
	}
			
	private Tupla buscarPos(int pPos)  // si la posiciones dadas coinciden con las de una Tupla, la devuelve.
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
	public boolean estaEnPosAdyacente(ArrayList<Integer> posisBarco) {
		boolean esAdyacente = false;
		/*if (this.posiciones) {
			
		}*/
		return esAdyacente;
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
