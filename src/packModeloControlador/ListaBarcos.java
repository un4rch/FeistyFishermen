package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class ListaBarcos {
	private ArrayList<Barco> listaBarcos;

	public ListaBarcos() {
		this.listaBarcos = new ArrayList<Barco>();
	}

	public void anadirBarco(ArrayList<Integer> posiciones) {
		this.listaBarcos.add(new Barco(posiciones));
	}

	private Iterator<Barco> getIterador()
	{
		return this.listaBarcos.iterator();
	}

	public boolean comprobarCantidad(int tipoBarco) //Comprueba que no haya mÃ¡s barcos de los posibles de cada tipo
	{
		boolean valido=true;
		int cont=4-(tipoBarco-1);
		Iterator<Barco> itr=this.listaBarcos.iterator();
		while (itr.hasNext() && valido)
		{
			Barco act=itr.next();
			if (tipoBarco==act.getTamano())
			{
				cont--;
			}
			if (cont==0)
			{
				valido=false;
			}
		}
		return valido;
	}
	
	public Barco getBarcoAleatorio() {
		return this.listaBarcos.get(new Random().nextInt(this.listaBarcos.size()-1));
	}
	
	public boolean flotaHundida()
	{
		boolean derrotado = true;
		Iterator<Barco> itr = this.getIterador();
		while (itr.hasNext()&&derrotado)
		{
			Barco act = itr.next();
			derrotado=act.estaHundido();
		}
		return derrotado;
	}

	public boolean tocarBarco(int pPos) //Toca el barco de la pPos y devuelve un boolean que indica si en esa pos había un Barco
	{
		//ve si hay barco en esa pos
		Iterator<Barco> itr = this.getIterador();
        boolean enc = false;
        Barco unBarco = null;
        while(itr.hasNext() && !enc)
        {
            unBarco = itr.next();
            if(unBarco.tieneEstaPos(pPos))
            {
                enc = true;
            }
        }
		//si hay barco en la pos lo toca
		if(enc)
		{
			unBarco.tocar(pPos);
		}

		return(enc);
	}

	public boolean tieneAlgunAdyacente(ArrayList<Integer> barco, Direccion dir) { //pos = yx
		boolean tieneAdyacente = false;
		ArrayList<Integer> posicionesAdyacentes = new ArrayList<Integer>();
		Integer pos = barco.get(0); //Primera posicion
		if (dir == Direccion.Arriba) {
			int x = pos%10;
			int y = pos/10;

			if (y!=9)
			{
				if (x!=0) { posicionesAdyacentes.add(10*(y+1)+x-1);} //Posicion abajo-izquierda
				posicionesAdyacentes.add(10*(y+1)+x); //Posicion abajo
				if (x!=9) { posicionesAdyacentes.add(10*(y+1)+x+1); } //Posicion abajo-derecha
			}
			for (Integer posM : barco) {
				x = posM%10;
				y = posM/10;
				if (x !=0) {posicionesAdyacentes.add(10*(y)+x-1); }//Posicion de la izquierda

				if (x!=9){ 	posicionesAdyacentes.add(10*(y)+x+1); } //Posicion de la derecha

			}
			pos = barco.get(barco.size()-1); //Ultima posicion
			x = pos%10;
			y = pos/10;
			if (y!=0)
			{
				if (x!=0) { posicionesAdyacentes.add(10*(y-1)+x-1); } //Posicion arriba-izquierda
				posicionesAdyacentes.add(10*(y-1)+x); //Posicion arrriba
				if (x!=9) { posicionesAdyacentes.add(10*(y-1)+x+1); }//Posicion arriba-derecha
			}

		} else if (dir == Direccion.Derecha) {
			int x = pos%10;
			int y = pos/10;
			if (x!=0)
			{
				if (y!=0) { posicionesAdyacentes.add(10*(y-1)+x-1); } //Posicion arriba-izquierda
				posicionesAdyacentes.add(10*(y)+x-1); //Posicion izquierda
				if (y!=9) {posicionesAdyacentes.add(10*(y+1)+x-1); } //Posicion abajo-izquierda
			}
			for (Integer posM : barco) {
				x = posM%10;
				y = posM/10;
				if (y!=0) {posicionesAdyacentes.add(10*(y-1)+x); }//Posicion de arriba
				if (y!=9) {posicionesAdyacentes.add(10*(y+1)+x); }//Posicion de abajo

			}
			pos = barco.get(barco.size()-1); //Ultima posicion
			x = pos%10;
			y = pos/10;
			if (x!=9)
			{
				if (y!=9) {posicionesAdyacentes.add(10*(y+1)+x+1);} //Posicion abajo-derecha
				posicionesAdyacentes.add(10*(y)+x+1); //Posicion de la derecha
				if (y!=0) { posicionesAdyacentes.add(10*(y-1)+x+1); }//Posicion arriba-derecha
			}

		} else if (dir == Direccion.Abajo) {
			int x = pos%10;
			int y = pos/10;
			if (y!=0)
			{
				if (x!=0) { posicionesAdyacentes.add(10*(y-1)+x-1); } //Posicion arriba-izquierda
				posicionesAdyacentes.add(10*(y-1)+x); //Posicion arrriba
				if (x!=9) { posicionesAdyacentes.add(10*(y-1)+x+1); }//Posicion arriba-derecha
			}
			for (Integer posM : barco) {
				x = posM%10;
				y = posM/10;
				if (x !=0) {posicionesAdyacentes.add(10*(y)+x-1); }//Posicion de la izquierda

				if (x!=9){ 	posicionesAdyacentes.add(10*(y)+x+1); } //Posicion de la derecha

			}
			pos = barco.get(barco.size()-1); //Ultima posicion
			x = pos%10;
			y = pos/10;
			if (y!=9)
			{
				if (x!=0) { posicionesAdyacentes.add(10*(y+1)+x-1);} //Posicion abajo-izquierda
				posicionesAdyacentes.add(10*(y+1)+x); //Posicion abajo
				if (x!=9) { posicionesAdyacentes.add(10*(y+1)+x+1); } //Posicion abajo-derecha
			}
		} else {
			int x = pos%10;
			int y = pos/10;
			if (x!=9)
			{
				if (y!=9) {posicionesAdyacentes.add(10*(y+1)+x+1);} //Posicion abajo-derecha
				posicionesAdyacentes.add(10*(y)+x+1); //Posicion de la derecha
				if (y!=0) { posicionesAdyacentes.add(10*(y-1)+x+1); }//Posicion arriba-derecha
			}
			for (Integer posM : barco) {
				x = posM%10;
				y = posM/10;
				if (y!=0) {posicionesAdyacentes.add(10*(y-1)+x); }//Posicion de arriba
				if (y!=9) {posicionesAdyacentes.add(10*(y+1)+x); }//Posicion de abajo

			}
			pos = barco.get(barco.size()-1); //Ultima posicion
			x = pos%10;
			y = pos/10;
			if (x!=0)
			{
				if (y!=0) { posicionesAdyacentes.add(10*(y-1)+x-1); } //Posicion arriba-izquierda
				posicionesAdyacentes.add(10*(y)+x-1); //Posicion izquierda
				if (y!=9) {posicionesAdyacentes.add(10*(y+1)+x-1); } //Posicion abajo-izquierda
			}
		}
		Iterator<Barco> itrBarco = listaBarcos.iterator();
		while (itrBarco.hasNext()&&!tieneAdyacente) {
			Barco actB = itrBarco.next();
			Iterator<Integer> itrPos = posicionesAdyacentes.iterator();
			while (itrPos.hasNext()&&!tieneAdyacente) {
				Integer actP = itrPos.next();
				tieneAdyacente=actB.tieneEstaPos(actP);
			}
		}
		return tieneAdyacente;
	}

}
