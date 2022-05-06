package packModeloControlador;

import java.util.ArrayList;

public class ReparacionStrategy implements ActuarStrategy {
	
	public ReparacionStrategy() {}
	
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
					//TODO
				} else {
					//TODO
				}
			}
		} else {
			System.out.println("No puedes reparar el agua");
		}
	}
}
