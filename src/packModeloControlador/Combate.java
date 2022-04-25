package packModeloControlador;

import java.util.ArrayList;

public class Combate {
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
    
    //Este metodo esta sin terminar
    /*public boolean actuailzarPos(int posX, int posY, Arma tipoArma, boolean turno) {
    	boolean tocado = false;
    	if (turno) {
    		if (tipoArma == Arma.Bomba) {
    			this.tableroUsuario[posY][posX] = ;
    		} else if (tipoArma == Arma.Misil) {
    			
    		} etc...
    	} else {
    		if () {
    			
    		}
    	}
    	return tocado;
    }*/
    
    /**
     * Mira a ver si un barco tiene algÃºn adyacente al querer colocarlo
     * Se puede mejorar el mÃ©todo ya que se repiten alguno cÃ¡lculos
     * @param pPosis Posiciones del barco a querer introducir
     * @param turno Turno del usuario (true) o del rival (false)
     * @return Si tiene adyacentes devuelve true, sino false
     */
    public boolean comprobarAdyacentes(ArrayList<Integer> pPosis, boolean turno) {
    	boolean esAdyacenye = false;
    	for (Integer pos : pPosis) {
    		int x = pos%10;
    		int y = pos/10;
    		if (x >= 0 && x <= 9 && y >= 0 && y <= 9) {
    			if (turno) {
        			if ((tableroUsuario[y-1][x-1] != Casilla.Agua) || 
        				(tableroUsuario[y-1][x] != Casilla.Agua) ||
        				(tableroUsuario[y-1][x+1] != Casilla.Agua) ||
        				(tableroUsuario[y][x-1] != Casilla.Agua) || 
        				(tableroUsuario[y][x] != Casilla.Agua) ||
        				(tableroUsuario[y][x+1] != Casilla.Agua) ||
        				(tableroUsuario[y+1][x-1] != Casilla.Agua) || 
        				(tableroUsuario[y+1][x] != Casilla.Agua) ||
        				(tableroUsuario[y+1][x+1] != Casilla.Agua)) {
        					esAdyacenye = true;
        			}
        		} else {
        			if ((tableroRival[y-1][x-1] != Casilla.Agua) || 
            			(tableroRival[y-1][x] != Casilla.Agua) ||
            			(tableroRival[y-1][x+1] != Casilla.Agua) ||
            			(tableroRival[y][x-1] != Casilla.Agua) || 
            			(tableroRival[y][x] != Casilla.Agua) ||
            			(tableroRival[y][x+1] != Casilla.Agua) ||
            			(tableroRival[y+1][x-1] != Casilla.Agua) || 
            			(tableroRival[y+1][x] != Casilla.Agua) ||
            			(tableroRival[y+1][x+1] != Casilla.Agua)) {
            				esAdyacenye = true;
            			}
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
			while (!enteroReparado) {
				if (pArma.equals(Arma.Escudo)) {
					if (this.tableroUsuario[x][y].equals(Casilla.Barco)) {
						this.tableroUsuario[x][y] = Casilla.Escudo;
					} else {
						System.out.println("No puedes poner escudos en el agua");
					}
				} else if (pArma.equals(Arma.Reparacion)) {
					//TODO
				}
				
				if (tableroUsuario[y-1][x-1] != Casilla.Agua) {
					x = x-1;
					y = y-1;
				} else if (tableroUsuario[y-1][x] != Casilla.Agua) {
					y = y-1;
				} else if (tableroUsuario[y-1][x+1] != Casilla.Agua) {
					x = x+1;
					y = y-1;
				} else if (tableroUsuario[y][x-1] != Casilla.Agua) {
					x = x-1;
				} else if (tableroUsuario[y][x+1] != Casilla.Agua) {
					x = x+1;
				} else if (tableroUsuario[y+1][x-1] != Casilla.Agua) {
					x = x-1;
					y = y+1;
				} else if (tableroUsuario[y+1][x] != Casilla.Agua) {
					y = y+1;
				} else if (tableroUsuario[y+1][x+1] != Casilla.Agua) {
					x = x+1;
					y = y+1;
				} else {
					enteroReparado = true;
				}
			}
		} else {
			while (!enteroReparado) {
				if (pArma.equals(Arma.Escudo)) {
					if (this.tableroRival[x][y].equals(Casilla.Barco)) {
						this.tableroRival[x][y] = Casilla.Escudo;
					} else {
						System.out.println("No puedes poner escudos en el agua");
					}
				} else if (pArma.equals(Arma.Reparacion)) {
					//TODO
				}
				
				if (tableroRival[y-1][x-1] != Casilla.Agua) {
					x = x-1;
					y = y-1;
				} else if (tableroRival[y-1][x] != Casilla.Agua) {
					y = y-1;
				} else if (tableroRival[y-1][x+1] != Casilla.Agua) {
					x = x+1;
					y = y-1;
				} else if (tableroRival[y][x-1] != Casilla.Agua) {
					x = x-1;
				} else if (tableroRival[y][x+1] != Casilla.Agua) {
					x = x+1;
				} else if (tableroRival[y+1][x-1] != Casilla.Agua) {
					x = x-1;
					y = y+1;
				} else if (tableroRival[y+1][x] != Casilla.Agua) {
					y = y+1;
				} else if (tableroRival[y+1][x+1] != Casilla.Agua) {
					x = x+1;
					y = y+1;
				} else {
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
    		
    		if (tableroRival[y-1][x-1] != Casilla.Agua) {
				x = x-1;
				y = y-1;
			} else if (tableroRival[y-1][x] != Casilla.Agua) {
				y = y-1;
			} else if (tableroRival[y-1][x+1] != Casilla.Agua) {
				x = x+1;
				y = y-1;
			} else if (tableroRival[y][x-1] != Casilla.Agua) {
				x = x-1;
			} else if (tableroRival[y][x+1] != Casilla.Agua) {
				x = x+1;
			} else if (tableroRival[y+1][x-1] != Casilla.Agua) {
				x = x-1;
				y = y+1;
			} else if (tableroRival[y+1][x] != Casilla.Agua) {
				y = y+1;
			} else if (tableroRival[y+1][x+1] != Casilla.Agua) {
				x = x+1;
				y = y+1;
			} else {
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
    		
    		if (tableroUsuario[y-1][x-1] != Casilla.Agua) {
				x = x-1;
				y = y-1;
			} else if (tableroUsuario[y-1][x] != Casilla.Agua) {
				y = y-1;
			} else if (tableroUsuario[y-1][x+1] != Casilla.Agua) {
				x = x+1;
				y = y-1;
			} else if (tableroUsuario[y][x-1] != Casilla.Agua) {
				x = x-1;
			} else if (tableroUsuario[y][x+1] != Casilla.Agua) {
				x = x+1;
			} else if (tableroUsuario[y+1][x-1] != Casilla.Agua) {
				x = x-1;
				y = y+1;
			} else if (tableroUsuario[y+1][x] != Casilla.Agua) {
				y = y+1;
			} else if (tableroUsuario[y+1][x+1] != Casilla.Agua) {
				x = x+1;
				y = y+1;
			} else {
				enteroAtacado = true;
			}
    	}
    }
}
