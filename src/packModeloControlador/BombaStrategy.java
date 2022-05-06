package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;

public class BombaStrategy implements ActuarStrategy {
	
	public BombaStrategy() {}
	
	public void actuar(Integer pPos, boolean esUsuario) {
    	ArrayList<Integer> barco;
		if (esUsuario) {
			barco = ListaJugadores.getMiListaJ().getUnJugador(1).perteneceA(pPos);
			if (!barco.isEmpty()) {
				if (Combate.getMiCombate().esCasilla(!esUsuario, pPos, Casilla.Barco))
    			{
					Combate.getMiCombate().setCasilla(!esUsuario, pPos, Casilla.Tocado);
    				ListaJugadores.getMiListaJ().getUnJugador(1).tocar(pPos);
    				if (ListaJugadores.getMiListaJ().getUnJugador(1).estaHundido(pPos)) {
    					for (Integer pos : barco) {
        					Combate.getMiCombate().setCasilla(!esUsuario, pos, Casilla.Hundido);
        				}
    				}
    			} else if (Combate.getMiCombate().esCasilla(!esUsuario, pPos, Casilla.EscudoDanado)) {
    				for (Integer pos : barco) {
    					if (ListaJugadores.getMiListaJ().getUnJugador(1).estaTocado(pos)) {
    						Combate.getMiCombate().setCasilla(!esUsuario, pos, Casilla.Tocado);
    					} else {
    						Combate.getMiCombate().setCasilla(!esUsuario, pos, Casilla.Barco);
    					}
    				}
    			} else if (Combate.getMiCombate().esCasilla(!esUsuario, pPos, Casilla.Escudo)) {
    				for (Integer pos : barco) {
    					Combate.getMiCombate().setCasilla(!esUsuario, pos, Casilla.EscudoDanado);
    				}
    			}
			} else {
				Combate.getMiCombate().setCasilla(!esUsuario, pPos, Casilla.Agua);
			}
		} else {
			barco = ListaJugadores.getMiListaJ().getUnJugador(0).perteneceA(pPos);
			if (!barco.isEmpty()) {
				if (Combate.getMiCombate().esCasilla(esUsuario, pPos, Casilla.Barco))
    			{
					Combate.getMiCombate().setCasilla(esUsuario, pPos, Casilla.Tocado);
    				ListaJugadores.getMiListaJ().getUnJugador(0).tocar(pPos);
    				if (ListaJugadores.getMiListaJ().getUnJugador(0).estaHundido(pPos)) {
    					for (Integer pos : barco) {
    						Combate.getMiCombate().setCasilla(esUsuario, pos, Casilla.Hundido);
        				}
    				}
    			} else if (Combate.getMiCombate().esCasilla(esUsuario, pPos, Casilla.EscudoDanado)) {
    				for (Integer pos : barco) {
    					if (ListaJugadores.getMiListaJ().getUnJugador(0).estaTocado(pos)) {
    						Combate.getMiCombate().setCasilla(esUsuario, pos, Casilla.Tocado);
    					} else {
    						Combate.getMiCombate().setCasilla(esUsuario, pos, Casilla.Barco);
    					}
    				}
    			} else if (Combate.getMiCombate().esCasilla(esUsuario, pPos, Casilla.Escudo)) {
    				for (Integer pos : barco) {
    					Combate.getMiCombate().setCasilla(esUsuario, pos, Casilla.EscudoDanado);
    				}
    			}
			} else {
				Combate.getMiCombate().setCasilla(esUsuario, pPos, Casilla.Agua);
			}
		}
	}
}
