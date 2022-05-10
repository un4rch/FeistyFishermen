package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public abstract class Barco
{
	
	private ArrayList<Tupla> info;

	public Barco(ArrayList<Integer> posiciones)
	{
		this.info = new ArrayList<Tupla>();
		posiciones.stream().forEach(p-> info.add(new Tupla(p,false)));
	}
	
	public ArrayList<Integer> getPosis() {
		ArrayList<Integer> posis = new ArrayList<Integer>();
		this.info.stream().forEach(p -> posis.add(p.getPos()));
		return posis;
	}
	
	public boolean estaHundido() 
	{
		return info.stream().allMatch(Tupla::estaTocado);
	}
	
	public boolean tieneEstaPos(int pPos)
	{
		return info.stream().anyMatch(t -> t.tieneEstaPos(pPos));
	}
	
	public Tupla getPosAleatoria() 
	{
		return this.info.get(new Random().nextInt(this.info.size()));
	}
	
	public boolean tocar(int pPos)
	{
		Tupla unaTupla = buscarPos(pPos);
		boolean hecho = false;
		if (unaTupla != null && !unaTupla.estaTocado())
		{
			unaTupla.setEstado(true);
			hecho = true;
		}
		return hecho;
	}
	
	public boolean reparar(int pPos)
	{
		Tupla unaTupla = buscarPos(pPos);
		boolean hecho = false;
		if (unaTupla != null && unaTupla.estaTocado())
		{
			unaTupla.setEstado(false);
			hecho = true;
		}
		return hecho;
	}
			
	private Tupla buscarPos(int pPos)  // si la posiciones dadas coinciden con las de una Tupla, la devuelve.
	{											   // en caso contrario devuelve null.
		Iterator<Tupla> itr = this.info.iterator();
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
	
	public boolean estaTocado(Integer pPos) {
		return this.info.stream().anyMatch(Tupla::estaTocado);
	}
}
