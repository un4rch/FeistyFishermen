package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;

public class ListaBarcos {
	private ArrayList<Barco> listaBarcos;
	
	public ListaBarcos() {
		this.listaBarcos = new ArrayList<Barco>();
	}
	
	public void anadirBarco(ArrayList<Integer> posiciones) {
		this.listaBarcos.add(new Barco(posiciones));
	}
	
	public boolean comprobarCantidad(int tipoBarco) //Comprueba que no haya más barcos de los posibles de cada tipo
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
	
	public boolean tieneAlgunAdyacente(ArrayList<Integer> barco, Direccion dir) { //pos = yx
		boolean tieneAdyacente = false;
		ArrayList<Integer> posicionesAdyacentes = new ArrayList<Integer>();
		Integer pos = barco.get(0); //Primera posicion
		if (dir == Direccion.Arriba) {
			int x = pos%10;
	    	int y = pos/10;
			posicionesAdyacentes.add(10*(y+1)+x-1); //Posicion abajo-izquierda
			posicionesAdyacentes.add(10*(y+1)+x); //Posicion abajo
			posicionesAdyacentes.add(10*(y+1)+x+1); //Posicion abajo-derecha
			for (Integer posM : barco) {
				x = posM%10;
		    	y = posM/10;
		    	posicionesAdyacentes.add(10*(y)+x-1); //Posicion de la izquierda
		    	posicionesAdyacentes.add(10*(y)+x+1); //Posicion de la derecha
				
			}
			pos = barco.get(barco.size()-1); //Ultima posicion
			x = pos%10;
	    	y = pos/10;
			posicionesAdyacentes.add(10*(y-1)+x-1); //Posicion arriba-izquierda
			posicionesAdyacentes.add(10*(y-1)+x); //Posicion arrriba
			posicionesAdyacentes.add(10*(y-1)+x+1); //Posicion arriba-derecha
		} else if (dir == Direccion.Derecha) {
			int x = pos%10;
	    	int y = pos/10;
	    	posicionesAdyacentes.add(10*(y-1)+x-1); //Posicion arriba-izquierda
	    	posicionesAdyacentes.add(10*(y)+x-1); //Posicion izquierda
	    	posicionesAdyacentes.add(10*(y+1)+x-1); //Posicion abajo-izquierda
			for (Integer posM : barco) {
				x = posM%10;
		    	y = posM/10;
		    	posicionesAdyacentes.add(10*(y-1)+x); //Posicion de arriba
		    	posicionesAdyacentes.add(10*(y+1)+x); //Posicion de abajo
				
			}
			pos = barco.get(barco.size()-1); //Ultima posicion
			x = pos%10;
	    	y = pos/10;
	    	posicionesAdyacentes.add(10*(y+1)+x+1); //Posicion abajo-derecha
			posicionesAdyacentes.add(10*(y)+x+1); //Posicion de la derecha
			posicionesAdyacentes.add(10*(y-1)+x+1); //Posicion arriba-derecha
		} else if (dir == Direccion.Abajo) {
			int x = pos%10;
	    	int y = pos/10;
	    	posicionesAdyacentes.add(10*(y-1)+x-1); //Posicion arriba-izquierda
			posicionesAdyacentes.add(10*(y-1)+x); //Posicion arrriba
			posicionesAdyacentes.add(10*(y-1)+x+1); //Posicion arriba-derecha
			for (Integer posM : barco) {
				x = posM%10;
		    	y = posM/10;
		    	posicionesAdyacentes.add(10*(y)+x-1); //Posicion de la izquierda
		    	posicionesAdyacentes.add(10*(y)+x+1); //Posicion de la derecha
				
			}
			pos = barco.get(barco.size()-1); //Ultima posicion
			x = pos%10;
	    	y = pos/10;
	    	posicionesAdyacentes.add(10*(y+1)+x-1); //Posicion abajo-izquierda
			posicionesAdyacentes.add(10*(y+1)+x); //Posicion abajo
			posicionesAdyacentes.add(10*(y+1)+x+1); //Posicion abajo-derecha
		} else {
			int x = pos%10;
	    	int y = pos/10;
	    	posicionesAdyacentes.add(10*(y+1)+x+1); //Posicion abajo-derecha
			posicionesAdyacentes.add(10*(y)+x+1); //Posicion de la derecha
			posicionesAdyacentes.add(10*(y-1)+x+1); //Posicion arriba-derecha
			for (Integer posM : barco) {
				x = posM%10;
		    	y = posM/10;
		    	posicionesAdyacentes.add(10*(y-1)+x); //Posicion de arriba
		    	posicionesAdyacentes.add(10*(y+1)+x); //Posicion de abajo
				
			}
			pos = barco.get(barco.size()-1); //Ultima posicion
			x = pos%10;
	    	y = pos/10;
	    	posicionesAdyacentes.add(10*(y-1)+x-1); //Posicion arriba-izquierda
	    	posicionesAdyacentes.add(10*(y)+x-1); //Posicion izquierda
	    	posicionesAdyacentes.add(10*(y+1)+x-1); //Posicion abajo-izquierda
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
