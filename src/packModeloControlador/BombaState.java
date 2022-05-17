package packModeloControlador;

import java.util.ArrayList;

public class BombaState implements ActuarState {
	
	public BombaState() {}
	
	public void actuar(Integer pPos, boolean esUsuario) {
		Integer esUsuarioInt;
		if (esUsuario) {
			esUsuarioInt = 1;
		} else {
			esUsuarioInt = 0;
		}
		ArrayList<Integer> barco;
		barco = ListaJugadores.getMiListaJ().getUnJugador(esUsuarioInt).perteneceA(pPos);
		if (!barco.isEmpty()) {
			if (Combate.getMiCombate().esCasilla(!esUsuario, pPos, Casilla.Barco))
			{
				Combate.getMiCombate().setCasilla(!esUsuario, pPos, Casilla.Tocado);
				ListaJugadores.getMiListaJ().getUnJugador(esUsuarioInt).tocar(pPos);
				if (ListaJugadores.getMiListaJ().getUnJugador(esUsuarioInt).estaHundido(pPos)) {
					for (Integer pos : barco) {
    					Combate.getMiCombate().setCasilla(!esUsuario, pos, Casilla.Hundido);
    				}
				}
			} else if (Combate.getMiCombate().esCasilla(!esUsuario, pPos, Casilla.EscudoDanado)) {
				for (Integer pos : barco) {
					if (ListaJugadores.getMiListaJ().getUnJugador(esUsuarioInt).estaTocado(pos)) {
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
	}
}
