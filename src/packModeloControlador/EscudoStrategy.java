package packModeloControlador;

import java.util.ArrayList;
import java.util.Observable;

public class EscudoStrategy implements ActuarStrategy{
	
	public EscudoStrategy() {}
	
	public void actuar(Integer pPos, boolean esUsuario) {
		ArrayList<Integer> barco;
		int esUsuarioInt;
		if (esUsuario) {
			barco = ListaJugadores.getMiListaJ().getUnJugador(0).perteneceA(pPos);
			esUsuarioInt = 0;
		} else {
			barco = ListaJugadores.getMiListaJ().getUnJugador(1).perteneceA(pPos);
			esUsuarioInt = 1;
		}
		
		if (!barco.isEmpty() && ListaJugadores.getMiListaJ().getUnJugador(esUsuarioInt).getArsenal().consumirEscudo()) {
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
