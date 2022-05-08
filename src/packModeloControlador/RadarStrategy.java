package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class RadarStrategy implements ActuarStrategy {
	
	public RadarStrategy() {}
	
	public void actuar(Integer pPos, boolean esUsuario) {
		int x = pPos%10;
		int y = pPos/10;
		
		int esUsuarioInt;
		if (esUsuario)
		{
			esUsuarioInt = 0;
		}
		else
		{
			esUsuarioInt = 1;
		}
		
		if (ListaJugadores.getMiListaJ().getUnJugador(esUsuarioInt).getArsenal().consumirRadar())
		{

			ArrayList<Integer> posis = new ArrayList<Integer>();
			posis.add(10*y+x);
			if (x-2>=0) 
			{
				posis.add(10*y+x-2);
			}
			if (x+2<=9) 
			{
				posis.add(10*y+x+2);
			}
			if (y-2>=0) 
			{
				posis.add(10*(y-2)+x);
			}
			if (y+2<=9) 
			{
				posis.add(10*(y+2)+x);
			}
			if (x-1>=0)
			{
				posis.add(10*y+x-1);
				if (y-1>=0) 
				{
					posis.add(10*(y-1)+x-1);
				}
				if (y+1<=9) 
				{
					posis.add(10*(y+1)+x-1);
				}
			}
			if (x+1<=9)
			{
				posis.add(10*y+x+1);
				if (y-1>=0) 
				{
					posis.add(10*(y-1)+x+1);
				}
				if (y+1<=9)
				{
					posis.add(10*(y+1)+x+1);
				}
			}
			if (y-1>=0) 
			{
				posis.add(10*(y-1)+x);
			}
			if (y+1<=9)
			{
				posis.add(10*(y+1)+x);
			}
			Combate.getMiCombate().mostrarCasillas(!esUsuario, posis);
		}
		
	}
}
