package packModeloControlador;

import java.util.ArrayList;
import java.util.Observable;

public class Combate extends Observable{
    private static Combate miCombate = null;
    private Casilla[][] tableroUsuario;
    private Casilla[][] tableroRival;

    private Combate()
    {
    	this.tableroUsuario = new Casilla[10][10];
    	this.tableroRival = new Casilla[10][10];
    	for (int i = 0; i<=9; i++) {
    		for (int j = 0; j<=9; j++) {
    			tableroUsuario[i][j] = Casilla.Agua;
    			tableroRival[i][j] = Casilla.Agua;
        	}
    	}
    }

    public static Combate getMiCombate()
    {
        if(Combate.miCombate == null)
        {
            Combate.miCombate = new Combate();
        }
        return Combate.miCombate;
    }
    
   /**
     * Mira a ver si un barco tiene algun adyacente al querer colocarlo
     * @param pPosis Posiciones del barco a querer introducir
     * @param turno Turno del usuario (true) o del rival (false)
     * @return Si tiene adyacentes devuelve true, sino false
     */
    public boolean comprobarAdyacentes(ArrayList<Integer> pPosis, boolean turno) {
    	boolean esAdyacenye = false;
    	int xInit = pPosis.get(0)%10;
    	int yInit = pPosis.get(0)/10;
    	int xFin = pPosis.get(pPosis.size()-1)%10;
    	int yFin = pPosis.get(pPosis.size()-1)/10;
    	if (yInit>=yFin && xInit>=xFin) { //Arriba o Izquierda (se les da la vuelta para que los vertices sean arriba-izq y abajo-der)
    		int aux = xInit;
    		xInit = xFin;
    		xFin = aux;
    	} //Sino Abajo o Derecha (no se cambia nada)
    	for (int i = xInit; i<=xFin; i++) {
			for (int j = xInit; j<=xFin; j++) {
    			if ((turno && !this.tableroUsuario[i][j].equals(Casilla.Agua)) || (!turno && !this.tableroRival[i][j].equals(Casilla.Agua))) {
					esAdyacenye = true;
    			}
    		}
		}
    	return esAdyacenye;
    }
    
    /**
     * Coloca barco en tablero
     * @param pPosis Posiciones del barco a poner
     * @param turno turno usuario (true), turno rival (false)
     * @return devuelve si se ha podido poner barco
     */
    public void colocarBarco(ArrayList<Integer> pPosis, boolean turno) {
    	for (Integer pos : pPosis) {
    		int x = pos%10;
        	int y = pos/10;
        	if (turno) {
        		this.tableroUsuario[y][x] = Casilla.Barco;
        	} else {
        		this.tableroRival[y][x] = Casilla.Barco;
        	}
    	}
    }

    public void defensa(int pPos, Arma pArma, boolean esUsuario) {
    	int x = pPos%10;
		int y = pPos/10;
		
		boolean enteroReparado = false;
		
		if (esUsuario) {
			while (!enteroReparado)
			{
				if (pArma.equals(Arma.Escudo)) 
				{
					if (this.tableroUsuario[x][y].equals(Casilla.Barco) || this.tableroUsuario[x][y].equals(Casilla.Tocado)) 
					{
						this.tableroUsuario[x][y] = Casilla.Escudo;
						setChanged();
						notifyObservers("0_"+(y*10+x)+"_E");
					} 
					else 
					{
						System.out.println("No puedes poner escudos en el agua");
					}
				} 
				else if (pArma.equals(Arma.Reparacion)) 
				{
					//TODO
				}
				
				if (y>0 && tableroUsuario[y-1][x] != Casilla.Agua) 
				{
					y = y-1;
				}
				else if (x>0 && tableroUsuario[y][x-1] != Casilla.Agua) 
				{
					x = x-1;
				} 
				else if (x<9 && tableroUsuario[y][x+1] != Casilla.Agua)
				{
					x = x+1;
				}
				else if (y<9 && tableroUsuario[y+1][x] != Casilla.Agua) 
				{
					y = y+1;
				}
				else 
				{
					enteroReparado = true;
				}
			}
		} 
		else 
		{
			while (!enteroReparado) 
			{
				if (pArma.equals(Arma.Escudo)) 
				{
					if (this.tableroRival[x][y].equals(Casilla.Barco) || this.tableroRival[x][y].equals(Casilla.Tocado))
					{
						this.tableroRival[x][y] = Casilla.Escudo;
					} 
					else 
					{
						System.out.println("No puedes poner escudos en el agua");
					}
				} 
				else if (pArma.equals(Arma.Reparacion)) 
				{
					//TODO
				}
				
				if (y>0 && tableroRival[y-1][x] != Casilla.Agua) 
				{
					y = y-1;
				} 
				else if ( x>0 && tableroRival[y][x-1] != Casilla.Agua) 
				{
					x = x-1;
				} 
				else if ( x<9 && tableroRival[y][x+1] != Casilla.Agua) 
				{
					x = x+1;
				} 
				else if ( y<9 && tableroRival[y+1][x] != Casilla.Agua) 
				{
					y = y+1;
				} 
				else 
				{
					enteroReparado = true;
				}
			}
		}
    }
    
    public void atacar(int pPos, Arma pArma, boolean esUsuario) {
    	int x = pPos%10;
		int y = pPos/10;
		
		boolean enteroAtacado = false;
		
    	if (esUsuario)  
    	{
    		if (pArma.equals(Arma.Bomba))
    		{
    			if (this.tableroRival[x][y].equals(Casilla.Barco))
    			{
    				this.tableroRival[x][y] = Casilla.Tocado;
    			}
    			else if (this.tableroRival[x][y].equals(Casilla.EscudoDanado))
    			{
    				this.tableroRival[x][y] = Casilla.Barco;
    			}
    			else if (this.tableroRival[x][y].equals(Casilla.Escudo))
    			{
    				this.tableroRival[x][y] = Casilla.EscudoDanado; 
    			}
    		}
    		else if (pArma.equals(Arma.Misil))
    		{
    			if (this.tableroRival[x][y].equals(Casilla.Barco))
    			{
    				this.tableroRival[x][y] = Casilla.Hundido;
    			}
    			else if ( (this.tableroRival[x][y].equals(Casilla.Escudo)) || (this.tableroRival[x][y].equals(Casilla.EscudoDanado)) )
    			{
    				this.tableroRival[x][y] = Casilla.Barco;
    			}
    		}
    		else if (pArma.equals(Arma.Radar))
    		{
    			//TODO
    		}
    		
    		if (y>0 && tableroRival[y-1][x] != Casilla.Agua) 
			{
				y = y-1;
			} 
			else if ( x>0 && tableroRival[y][x-1] != Casilla.Agua) 
			{
				x = x-1;
			} 
			else if ( x<9 && tableroRival[y][x+1] != Casilla.Agua) 
			{
				x = x+1;
			} 
			else if ( y<9 && tableroRival[y+1][x] != Casilla.Agua) 
			{
				y = y+1;
			} 
			else 
			{
				enteroAtacado = true;
			}
    	}
    	else
    	{
    		if (pArma.equals(Arma.Bomba))
    		{
    			if (this.tableroUsuario[x][y].equals(Casilla.Barco))
    			{
    				this.tableroUsuario[x][y] = Casilla.Tocado;
    			}
    			else if (this.tableroUsuario[x][y].equals(Casilla.EscudoDanado))
    			{
    				this.tableroUsuario[x][y] = Casilla.Barco;
    			}
    			else if (this.tableroUsuario[x][y].equals(Casilla.Escudo))
    			{
    				this.tableroUsuario[x][y] = Casilla.EscudoDanado; 
    			}
    		}
    		else if (pArma.equals(Arma.Misil))
    		{
    			if (this.tableroUsuario[x][y].equals(Casilla.Barco))
    			{
    				this.tableroUsuario[x][y] = Casilla.Hundido;
    			}
    			else if ( (this.tableroUsuario[x][y].equals(Casilla.Escudo)) || (this.tableroUsuario[x][y].equals(Casilla.EscudoDanado)) )
    			{
    				this.tableroUsuario[x][y] = Casilla.Barco;
    			}
    		}
    		else if (pArma.equals(Arma.Radar))
    		{
    			//TODO
    		}
    		
    		if (y>0 && tableroUsuario[y-1][x] != Casilla.Agua) 
			{
				y = y-1;
			}
			else if (x>0 && tableroUsuario[y][x-1] != Casilla.Agua) 
			{
				x = x-1;
			} 
			else if (x<9 && tableroUsuario[y][x+1] != Casilla.Agua)
			{
				x = x+1;
			}
			else if (y<9 && tableroUsuario[y+1][x] != Casilla.Agua) 
			{
				y = y+1;
			}
			else 
			{
				enteroAtacado = true;
			}
    	}
    }
}
