package packModeloControlador;

import java.util.ArrayList;

public class ReparacionStrategy implements ActuarStrategy {
	
	public ReparacionStrategy() {}
	
	public void actuar(Integer pPos, boolean esUsuario) {
		ArrayList<Integer> barco;
		int usu;
		if (esUsuario) {
			
			usu = 0;
		} else {
			usu = 1;
		}
		barco = ListaJugadores.getMiListaJ().getUnJugador(usu).perteneceA(pPos);
		
		if (!barco.isEmpty() && !Combate.getMiCombate().esCasilla(esUsuario, pPos, Casilla.Hundido)) {
			for (Integer pos : barco) 
			{
				if (ListaJugadores.getMiListaJ().getUnJugador(usu).estaTocado(pos))
				{
					ListaJugadores.getMiListaJ().getUnJugador(usu).reparar(pos);
					if (Combate.getMiCombate().esCasilla(esUsuario,pos,Casilla.Tocado))
					{
						Combate.getMiCombate().setCasilla(esUsuario,pos,Casilla.Barco);
					}
				}
			}
		} else {
			System.out.println("No puedes reparar el agua");
		}
	}
}
