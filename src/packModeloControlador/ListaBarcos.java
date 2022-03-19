package packModeloControlador;

import java.util.ArrayList;

public class ListaBarcos {
	private ArrayList<Barco> listaBarcos;
	
	public ListaBarcos() {
		this.listaBarcos = new ArrayList<Barco>();
	}
	
	public void anadirBarco(Barco barco) {
		this.listaBarcos.add(barco);
	}
	
	public boolean tieneAlgunAdyacente(Barco barco, Direccion dir) { //pos = yx
		boolean tieneAdyacente = false;
		ArrayList<Integer> posicionesAdyacentes = new ArrayList<Integer>();
		if (dir == Direccion.Arriba) {
			Integer pos = barco.getPosiciones().get(0); //Primera posicion
			int x = pos%10;
	    	int y = pos/10;
			posicionesAdyacentes.add(10*(y+1)+x-1); //Posicion abajo-izquierda
			posicionesAdyacentes.add(10*(y+1)+x); //Posicion abajo
			posicionesAdyacentes.add(10*(y+1)+x+1); //Posicion abajo-derecha
			for (Integer posM : barco.getPosiciones()) {
				x = posM%10;
		    	y = posM/10;
		    	posicionesAdyacentes.add(10*(y)+x-1); //Posicion de la izquierda
		    	posicionesAdyacentes.add(10*(y)+x+1); //Posicion de la derecha
				
			}
			pos = barco.getPosiciones().get(barco.getPosiciones().size()-1); //Ultima posicion
			x = pos%10;
	    	y = pos/10;
			posicionesAdyacentes.add(10*(y-1)+x-1); //Posicion arriba-izquierda
			posicionesAdyacentes.add(10*(y-1)+x); //Posicion arrriba
			posicionesAdyacentes.add(10*(y-1)+x+1); //Posicion arriba-derecha
		} else if (dir == Direccion.Derecha) {
			Integer pos = barco.getPosiciones().get(0); //Primera posicion
			int x = pos%10;
	    	int y = pos/10;
	    	posicionesAdyacentes.add(10*(y-1)+x-1); //Posicion arriba-izquierda
	    	posicionesAdyacentes.add(10*(y)+x-1); //Posicion izquierda
	    	posicionesAdyacentes.add(10*(y+1)+x-1); //Posicion abajo-izquierda
			for (Integer posM : barco.getPosiciones()) {
				x = posM%10;
		    	y = posM/10;
		    	posicionesAdyacentes.add(10*(y-1)+x); //Posicion de arriba
		    	posicionesAdyacentes.add(10*(y+1)+x); //Posicion de abajo
				
			}
			pos = barco.getPosiciones().get(barco.getPosiciones().size()-1); //Ultima posicion
			x = pos%10;
	    	y = pos/10;
	    	posicionesAdyacentes.add(10*(y+1)+x+1); //Posicion abajo-derecha
			posicionesAdyacentes.add(10*(y)+x+1); //Posicion de la derecha
			posicionesAdyacentes.add(10*(y-1)+x+1); //Posicion arriba-derecha
		} else if (dir == Direccion.Abajo) {
			Integer pos = barco.getPosiciones().get(0); //Primera posicion
			int x = pos%10;
	    	int y = pos/10;
	    	posicionesAdyacentes.add(10*(y-1)+x-1); //Posicion arriba-izquierda
			posicionesAdyacentes.add(10*(y-1)+x); //Posicion arrriba
			posicionesAdyacentes.add(10*(y-1)+x+1); //Posicion arriba-derecha
			for (Integer posM : barco.getPosiciones()) {
				x = posM%10;
		    	y = posM/10;
		    	posicionesAdyacentes.add(10*(y)+x-1); //Posicion de la izquierda
		    	posicionesAdyacentes.add(10*(y)+x+1); //Posicion de la derecha
				
			}
			pos = barco.getPosiciones().get(barco.getPosiciones().size()-1); //Ultima posicion
			x = pos%10;
	    	y = pos/10;
	    	posicionesAdyacentes.add(10*(y+1)+x-1); //Posicion abajo-izquierda
			posicionesAdyacentes.add(10*(y+1)+x); //Posicion abajo
			posicionesAdyacentes.add(10*(y+1)+x+1); //Posicion abajo-derecha
		} else {
			Integer pos = barco.getPosiciones().get(0); //Primera posicion
			int x = pos%10;
	    	int y = pos/10;
	    	posicionesAdyacentes.add(10*(y+1)+x+1); //Posicion abajo-derecha
			posicionesAdyacentes.add(10*(y)+x+1); //Posicion de la derecha
			posicionesAdyacentes.add(10*(y-1)+x+1); //Posicion arriba-derecha
			for (Integer posM : barco.getPosiciones()) {
				x = posM%10;
		    	y = posM/10;
		    	posicionesAdyacentes.add(10*(y-1)+x); //Posicion de arriba
		    	posicionesAdyacentes.add(10*(y+1)+x); //Posicion de abajo
				
			}
			pos = barco.getPosiciones().get(barco.getPosiciones().size()-1); //Ultima posicion
			x = pos%10;
	    	y = pos/10;
	    	posicionesAdyacentes.add(10*(y-1)+x-1); //Posicion arriba-izquierda
	    	posicionesAdyacentes.add(10*(y)+x-1); //Posicion izquierda
	    	posicionesAdyacentes.add(10*(y+1)+x-1); //Posicion abajo-izquierda
		}
		for (Barco barcoTablero : listaBarcos) {
			for (Integer posTablero : barcoTablero.getPosiciones()) {
				if (posicionesAdyacentes.contains(posTablero)) {
					tieneAdyacente = true;
				}
			}
		}
		return tieneAdyacente;
	}

}
