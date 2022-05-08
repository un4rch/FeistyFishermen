package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;

public class MisilStrategy implements ActuarStrategy extends Observable{
	
	public MisilStrategy() {}
	
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
				for (Integer pos : barco) {
					Combate.getMiCombate().setCasilla(!esUsuario, pos, Casilla.Hundido);
					ListaJugadores.getMiListaJ().getUnJugador(esUsuarioInt).tocar(pos);
				}
			}
			else if ( (Combate.getMiCombate().esCasilla(!esUsuario, pPos, Casilla.Escudo)) || (Combate.getMiCombate().esCasilla(!esUsuario, pPos, Casilla.EscudoDanado)) )
			{
				for (Integer pos : barco) {
					if (ListaJugadores.getMiListaJ().getUnJugador(esUsuarioInt).estaTocado(pos)) {
						Combate.getMiCombate().setCasilla(!esUsuario, pos, Casilla.Tocado);
					} else {
						Combate.getMiCombate().setCasilla(!esUsuario, pos, Casilla.Barco);
					}
				}
			}
		} else {
			Combate.getMiCombate().setCasilla(!esUsuario, pPos, Casilla.Agua);
		}
		setChanged();
		notifyObservers('M');
	}
}
