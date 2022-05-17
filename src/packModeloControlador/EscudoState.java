package packModeloControlador;

import java.util.ArrayList;

public class EscudoState implements ActuarState{
	
	public EscudoState() {}
	
	public void actuar(Integer pPos, boolean esUsuario) {
		ArrayList<Integer> barco;
		int esUsuarioInt;
		if (esUsuario) {
			esUsuarioInt = 0;
		} else {
			
			esUsuarioInt = 1;
		}
		barco = ListaJugadores.getMiListaJ().getUnJugador(esUsuarioInt).perteneceA(pPos);
		
		if (!barco.isEmpty() && ListaJugadores.getMiListaJ().getUnJugador(esUsuarioInt).getArsenal().consumirEscudo()) {
			for (Integer pos : barco) {
				if (!Combate.getMiCombate().esCasilla(esUsuario, pos, Casilla.Hundido)) 
				{
					Combate.getMiCombate().setCasilla(esUsuario, pos, Casilla.Escudo);
				} 
				else 
				{
					System.out.println("No puedes proteger un barco hundido");
				}
			}
		} else {
			System.out.println("No puedes poner escudos en el agua");
		}
	}
}
