package packModeloControlador;

import java.util.ArrayList;

public class EscudoStrategy implements ActuarStrategy {
	
	public EscudoStrategy() {}
	
	public void actuar(Integer pPos, boolean esUsuario) {
		ArrayList<Integer> barco;
		if (esUsuario) {
			barco = ListaJugadores.getMiListaJ().getUnJugador(0).perteneceA(pPos);
		} else {
			barco = ListaJugadores.getMiListaJ().getUnJugador(1).perteneceA(pPos);
		}
		
		if (!barco.isEmpty()) {
			for (Integer pos : barco) {
				if (esUsuario) {
					if (!Combate.getMiCombate().esCasilla(esUsuario, pos, Casilla.Hundido)) 
					{
						Combate.getMiCombate().setCasilla(esUsuario, pos, Casilla.Escudo);
					} 
					else 
					{
						System.out.println("No puedes proteger un barco hundido");
					}
				} else {
					if (!Combate.getMiCombate().esCasilla(!esUsuario, pos, Casilla.Hundido))
					{
						Combate.getMiCombate().setCasilla(!esUsuario, pos, Casilla.Escudo);
					}
					else 
					{
						System.out.println("No puedes proteger un barco hundido");
					}
				}
			}
		} else {
			System.out.println("No puedes poner escudos en el agua");
		}
	}
}
