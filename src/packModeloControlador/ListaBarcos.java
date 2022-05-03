package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaBarcos {
	private ArrayList<Barco> listaBarcos;
	private int cantCasillas;

	public ListaBarcos() {
		this.listaBarcos = new ArrayList<Barco>();
		this.cantCasillas = 0;
	}

	private Iterator<Barco> getIterador()
	{
		return this.listaBarcos.iterator();
	}

	public void sumarCasillas(int pCant)
	{
		this.cantCasillas += pCant;
	}

	public void restarCasillas(int pCant)
	{
		this.cantCasillas -= pCant;
	}

	public boolean flotaHundida()
	{
		return (this.cantCasillas <= 0);
	}
	
	public boolean partidaLista()
	{
		return (this.listaBarcos.size()==10);
	}
	
	public ArrayList<Integer> perteneceA(int pPos)
	{
		Iterator<Barco> itr = this.getIterador();
		boolean encontrado = false;
		ArrayList<Integer> posis = new ArrayList<Integer>();
		while (itr.hasNext() && !encontrado)
		{
			Barco act = itr.next();
			if (act.tieneEstaPos(pPos))
			{
				encontrado = true;
				posis = act.getPosis();
			}
		}
		return posis;
	}

	public void anadirBarco(ArrayList<Integer> pPosiciones)
	{
		int tamano = pPosiciones.size();

		Barco unBarco = BarcoFactory.getMiBarcoFactory().crearBarco(pPosiciones);
		this.listaBarcos.add(unBarco);

		this.cantCasillas = this.cantCasillas + tamano;
	}

	public boolean comprobarCantidad(int tipoBarco) //Comprueba que no haya mas barcos de los posibles de cada tipo
	{
		boolean valido=true;
		int cont=4-(tipoBarco-1);
		Iterator<Barco> itr=this.listaBarcos.iterator();
		while (itr.hasNext() && valido)
		{
			Barco unBarco = itr.next();
			if(tipoBarco==1 && Fragata.class.isInstance(unBarco))
			{
				cont--;
			}
			else if(tipoBarco==2 && Destructor.class.isInstance(unBarco))
			{
				cont--;
			}
			else if(tipoBarco==3 && Submarino.class.isInstance(unBarco))
			{
				cont--;
			}
			else if(tipoBarco==4 && Portaaviones.class.isInstance(unBarco))
			{
				cont--;
			}
			if (cont==0)
			{
				valido=false;		//No puede meter mas barcos
			}
		}
		return valido;
	}

	public boolean tocarBarco(int pPos) //Toca el barco de la pPos y devuelve un boolean que indica si en esa pos habia un Barco
	{
		//ve si hay barco en esa pos
		Iterator<Barco> itr = this.getIterador();
        boolean enc = false;
        boolean hecho = false;
        Barco unBarco = null;
        while(itr.hasNext() && !enc)
        {
            unBarco = itr.next();
            enc = unBarco.tieneEstaPos(pPos);
        }
		//si hay barco en la pos lo toca
		if(enc)
		{
			hecho = unBarco.tocar(pPos);
		}
		if(hecho)
		{
			this.restarCasillas(1);
		}
		return(enc);
	}
	
	public boolean estaHundido(int pPos)
	{
		boolean hundido = false;
		boolean encontrado = false;
		Iterator<Barco> itr = this.getIterador();
		while (itr.hasNext() && !encontrado)
		{
			Barco act = itr.next();
			if (act.tieneEstaPos(pPos))
			{
				encontrado = true;
				hundido = act.estaHundido();
			}
		}
		return hundido;
	}

	public boolean estaTocado(Integer pPos) {
		boolean tocado = false;
		boolean encontrado = false;
		Iterator<Barco> itr = this.getIterador();
		while (itr.hasNext() && !encontrado)
		{
			Barco act = itr.next();
			if (act.tieneEstaPos(pPos))
			{
				encontrado = true;
				tocado = act.estaTocado(pPos);
			}
		}
		return tocado;
	}

}
