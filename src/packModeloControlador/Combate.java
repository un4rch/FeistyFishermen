package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;
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
    		aux = yInit;
    		yInit = yFin;
    		yFin = aux;
    	} //Sino Abajo o Derecha (no se cambia nada)
    		for (int i = xInit-1; i<=xFin+1; i++) {
    			for (int j = yInit-1; j<=yFin+1; j++) {
    				if (i>=0 && j>=0 && i<=9 && j<=9)
    				{
    					if ((turno && !this.tableroUsuario[j][i].equals(Casilla.Agua)) || (!turno && !this.tableroRival[j][i].equals(Casilla.Agua))) {
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
    	setChanged();
    	notifyObservers(pPosis);
    }
    
    public void defensa(int pPos, Arma pArma, boolean esUsuario) {
    	ArrayList<Integer> barco;
		
		if (esUsuario) {
			barco = ListaJugadores.getMiListaJ().getUnJugador(0).perteneceA(pPos);
		} else {
			barco = ListaJugadores.getMiListaJ().getUnJugador(1).perteneceA(pPos);
		}
		
		if (!barco.isEmpty()) {
			for (Integer pos : barco) {
				int x = pos%10;
				int y = pos/10;
				if (esUsuario) {
					if (pArma.equals(Arma.Escudo)) 
					{
						if (!this.tableroUsuario[y][x].equals(Casilla.Hundido)) 
						{
							this.tableroUsuario[y][x] = Casilla.Escudo;
							setChanged();
							notifyObservers("0_"+(y*10+x)+"_E");
						} 
						else 
						{
							System.out.println("No puedes proteger un barco hundido");
						}
					} 
					else if (pArma.equals(Arma.Reparacion)) 
					{
						//TODO
					}
				} else {
					if (pArma.equals(Arma.Escudo)) 
					{
						if (!this.tableroRival[y][x].equals(Casilla.Hundido))
						{
							this.tableroRival[y][x] = Casilla.Escudo;
						}
						else 
						{
							System.out.println("No puedes proteger un barco hundido");
						}
					} 
					else if (pArma.equals(Arma.Reparacion)) 
					{
						//TODO
					}
				}
			}
		} else {
			System.out.println("No puedes poner escudos en el agua");
		}
    }

    public void atacar(int pPos, Arma pArma, boolean esUsuario) {
    	int x = pPos%10;
		int y = pPos/10;
    	ArrayList<Integer> barco;
		
		if (esUsuario) {
			if (pArma.equals(Arma.Radar))
			{
				ArrayList<Casilla> listaVistos = new ArrayList<Casilla>();
				ArrayList<Integer> posis = new ArrayList<Integer>();
				listaVistos.add(this.tableroRival[y][x]);
				posis.add(10*y+x);
				if (x-2>=0) 
				{
					listaVistos.add(this.tableroRival[y][x-2]);
					posis.add(10*y+x-2);
				}
				if (x+2<=9) 
				{
					listaVistos.add(this.tableroRival[y][x+2]);
					posis.add(10*y+x+2);
				}
				if (y-2>=0) 
				{
					listaVistos.add(this.tableroRival[y-2][x]);
					posis.add(10*(y-2)+x);
				}
				if (y+2<=9) 
				{
					listaVistos.add(this.tableroRival[y+2][x]);
					posis.add(10*(y+2)+x);
				}
				if (x-1>=0)
				{
					listaVistos.add(this.tableroRival[y][x-1]);
					posis.add(10*y+x-1);
					if (y-1>=0) 
					{
						listaVistos.add(this.tableroRival[y-1][x-1]);
						posis.add(10*(y-1)+x-1);
					}
					if (y+1<=9) 
					{
						listaVistos.add(this.tableroRival[y+1][x-1]);
						posis.add(10*(y+1)+x-1);
					}
				}
				if (x+1<=9)
				{
					listaVistos.add(this.tableroRival[y][x+1]);
					posis.add(10*y+x+1);
					if (y-1>=0) 
					{
						listaVistos.add(this.tableroRival[y-1][x+1]);
						posis.add(10*(y-1)+x+1);
					}
					if (y+1<=9)
					{
						listaVistos.add(this.tableroRival[y+1][x+1]);
						posis.add(10*(y+1)+x+1);
					}
				}
				if (y-1>=0) 
				{
					listaVistos.add(this.tableroRival[y-1][x]);
					posis.add(10*(y-1)+x);
				}
				if (y+1<=9)
				{
					listaVistos.add(this.tableroRival[y+1][x]);
					posis.add(10*(y+1)+x);
				}
				Iterator<Casilla> itr = listaVistos.iterator();
				int cont = 0;
				while (itr.hasNext())
				{
					Casilla act = itr.next();
					y = (posis.get(cont))/10;
					x = (posis.get(cont))%10;
					cont++;
					if (act.equals(Casilla.Agua))
					{
						setChanged();
						notifyObservers("1_"+(10*y+x)+"_A");
					}
					else if(act.equals(Casilla.Barco))
					{
						setChanged();
						notifyObservers("1_"+(10*y+x)+"_B");
					}
					else if(act.equals(Casilla.Tocado))
					{
						setChanged();
						notifyObservers("1_"+(10*y+x)+"_T");
					}
					else if(act.equals(Casilla.Escudo))
					{
						setChanged();
						notifyObservers("1_"+(10*y+x)+"_E");
					}
					else if(act.equals(Casilla.EscudoDanado))
					{
						setChanged();
						notifyObservers("1_"+(10*y+x)+"_ED");
					}
					else if(act.equals(Casilla.Hundido))
					{
						setChanged();
						notifyObservers("1_"+(10*y+x)+"_H");
					}
				}
			}
			else
			{
				barco = ListaJugadores.getMiListaJ().getUnJugador(1).perteneceA(pPos);
				if (!barco.isEmpty()) {
					if (pArma.equals(Arma.Bomba)) {
						if (this.tableroRival[y][x].equals(Casilla.Barco))
	        			{
	        				this.tableroRival[y][x] = Casilla.Tocado;
	        				ListaJugadores.getMiListaJ().getUnJugador(1).tocar(pPos);
	        				setChanged();
	        				notifyObservers("1_"+(y*10+x)+"_T");
	        				if (ListaJugadores.getMiListaJ().getUnJugador(1).estaHundido(pPos)) {
	        					for (Integer pos : barco) {
	            					x = pos%10;
	            					y = pos/10;
	            					this.tableroRival[y][x] = Casilla.Hundido;
	            					setChanged();
	            					notifyObservers("1_"+(y*10+x)+"_H");
	            				}
	        				}
	        			} else if (this.tableroRival[y][x].equals(Casilla.EscudoDanado)) {
	        				for (Integer pos : barco) {
	        					x = pos%10;
	        					y = pos/10;
	        					if (ListaJugadores.getMiListaJ().getUnJugador(1).estaTocado(pos)) {
	        						this.tableroRival[y][x] = Casilla.Tocado;
	        						setChanged();
	            					notifyObservers("1_"+(y*10+x)+"_T");
	        					} else {
	        						this.tableroRival[y][x] = Casilla.Barco;
	        						setChanged();
	            					notifyObservers("1_"+(y*10+x)+"_B");
	        					}
	        				}
	        			} else if (this.tableroRival[y][x].equals(Casilla.Escudo)) {
	        				for (Integer pos : barco) {
	        					x = pos%10;
	        					y = pos/10;
	        					this.tableroRival[y][x] = Casilla.EscudoDanado;
	        					setChanged();
	        					notifyObservers("1_"+(y*10+x)+"_ED");
	        				}
	        			}
					} else if (pArma.equals(Arma.Misil)) {
						if (this.tableroRival[y][x].equals(Casilla.Barco))
	        			{
							for (Integer pos : barco) {
								x = pos%10;
	        					y = pos/10;
								this.tableroRival[y][x] = Casilla.Hundido;
								ListaJugadores.getMiListaJ().getUnJugador(1).tocar(pos);
		        				setChanged();
		    					notifyObservers("1_"+(y*10+x)+"_H");
							}
	        			}
	        			else if ( (this.tableroRival[y][x].equals(Casilla.Escudo)) || (this.tableroRival[y][x].equals(Casilla.EscudoDanado)) )
	        			{
	        				for (Integer pos : barco) {
	        					x = pos%10;
	        					y = pos/10;
	        					if (ListaJugadores.getMiListaJ().getUnJugador(1).estaTocado(pos)) {
	        						this.tableroRival[y][x] = Casilla.Tocado;
	        						setChanged();
	            					notifyObservers("1_"+(y*10+x)+"_T");
	        					} else {
	        						this.tableroRival[y][x] = Casilla.Barco;
	        						setChanged();
	            					notifyObservers("1_"+(y*10+x)+"_B");
	        					}
	        				}
	        			}
					} 
				} else {
					setChanged();
					notifyObservers("1_"+(y*10+x)+"_A");
				}
			}
		} else {
			if (pArma.equals(Arma.Radar))
			{
				ArrayList<Casilla> listaVistos = new ArrayList<Casilla>();
				ArrayList<Integer> posis = new ArrayList<Integer>();
				listaVistos.add(this.tableroUsuario[y][x]);
				posis.add(10*y+x);
				if (x-2>=0) 
				{
					listaVistos.add(this.tableroUsuario[y][x-2]);
					posis.add(10*y+x-2);
				}
				if (x+2<=9) 
				{
					listaVistos.add(this.tableroUsuario[y][x+2]);
					posis.add(10*y+x+2);
				}
				if (y-2>=0) 
				{
					listaVistos.add(this.tableroUsuario[y-2][x]);
					posis.add(10*(y-2)+x);
				}
				if (y+2<=9) 
				{
					listaVistos.add(this.tableroUsuario[y+2][x]);
					posis.add(10*(y+2)+x);
				}
				if (x-1>=0)
				{
					listaVistos.add(this.tableroUsuario[y][x-1]);
					posis.add(10*y+x-1);
					if (y-1>=0) 
					{
						listaVistos.add(this.tableroUsuario[y-1][x-1]);
						posis.add(10*(y-1)+x-1);
					}
					if (y+1<=9) 
					{
						listaVistos.add(this.tableroUsuario[y+1][x-1]);
						posis.add(10*(y+1)+x-1);
					}
				}
				if (x+1<=9)
				{
					listaVistos.add(this.tableroUsuario[y][x+1]);
					posis.add(10*y+x+1);
					if (y-1>=0) 
					{
						listaVistos.add(this.tableroUsuario[y-1][x+1]);
						posis.add(10*(y-1)+x+1);
					}
					if (y+1<=9)
					{
						listaVistos.add(this.tableroUsuario[y+1][x+1]);
						posis.add(10*(y+1)+x+1);
					}
				}
				if (y-1>=0) 
				{
					listaVistos.add(this.tableroUsuario[y-1][x]);
					posis.add(10*(y-1)+x);
				}
				if (y+1<=9)
				{
					listaVistos.add(this.tableroUsuario[y+1][x]);
					posis.add(10*(y+1)+x);
				}
				Iterator<Casilla> itr = listaVistos.iterator();
				int cont = 0;
				while (itr.hasNext())
				{
					Casilla act = itr.next();
					y = (posis.get(cont))/10;
					x = (posis.get(cont))%10;
					cont++;
					if (act.equals(Casilla.Agua))
					{
						setChanged();
						notifyObservers("0_"+(10*y+x)+"_A");
					}
					else if(act.equals(Casilla.Barco))
					{
						setChanged();
						notifyObservers("0_"+(10*y+x)+"_B");
					}
					else if(act.equals(Casilla.Tocado))
					{
						setChanged();
						notifyObservers("0_"+(10*y+x)+"_T");
					}
					else if(act.equals(Casilla.Escudo))
					{
						setChanged();
						notifyObservers("0_"+(10*y+x)+"_E");
					}
					else if(act.equals(Casilla.EscudoDanado))
					{
						setChanged();
						notifyObservers("0_"+(10*y+x)+"_ED");
					}
					else if(act.equals(Casilla.Hundido))
					{
						setChanged();
						notifyObservers("0_"+(10*y+x)+"_H");
					}
				}
			}
			else
			{
				barco = ListaJugadores.getMiListaJ().getUnJugador(0).perteneceA(pPos);
				if (!barco.isEmpty()) {
					if (pArma.equals(Arma.Bomba)) {
						if (this.tableroUsuario[y][x].equals(Casilla.Barco))
	        			{
	        				this.tableroUsuario[y][x] = Casilla.Tocado;
	        				ListaJugadores.getMiListaJ().getUnJugador(0).tocar(pPos);
	        				setChanged();
	        				notifyObservers("0_"+(y*10+x)+"_T");
	        				if (ListaJugadores.getMiListaJ().getUnJugador(0).estaHundido(pPos)) {
	        					for (Integer pos : barco) {
	            					x = pos%10;
	            					y = pos/10;
	            					this.tableroUsuario[y][x] = Casilla.Hundido;
	            					setChanged();
	            					notifyObservers("0_"+(y*10+x)+"_H");
	            				}
	        				}
	        			} else if (this.tableroUsuario[y][x].equals(Casilla.EscudoDanado)) {
	        				for (Integer pos : barco) {
	        					x = pos%10;
	        					y = pos/10;
	        					if (ListaJugadores.getMiListaJ().getUnJugador(0).estaTocado(pos)) {
	        						this.tableroUsuario[y][x] = Casilla.Tocado;
	        						setChanged();
	            					notifyObservers("0_"+(y*10+x)+"_T");
	        					} else {
	        						this.tableroUsuario[y][x] = Casilla.Barco;
	        						setChanged();
	            					notifyObservers("0_"+(y*10+x)+"_B");
	        					}
	        				}
	        			} else if (this.tableroUsuario[y][x].equals(Casilla.Escudo)) {
	        				for (Integer pos : barco) {
	        					x = pos%10;
	        					y = pos/10;
	        					this.tableroUsuario[y][x] = Casilla.EscudoDanado;
	        					setChanged();
	        					notifyObservers("0_"+(y*10+x)+"_ED");
	        				}
	        			}
					} else if (pArma.equals(Arma.Misil)) {
						if (this.tableroUsuario[y][x].equals(Casilla.Barco))
	        			{
							for (Integer pos : barco) {
								x = pos%10;
	        					y = pos/10;
								this.tableroUsuario[y][x] = Casilla.Hundido;
								ListaJugadores.getMiListaJ().getUnJugador(0).tocar(pos);
		        				setChanged();
		    					notifyObservers("0_"+(y*10+x)+"_H");
							}
	        			}
	        			else if ( (this.tableroUsuario[y][x].equals(Casilla.Escudo)) || (this.tableroUsuario[y][x].equals(Casilla.EscudoDanado)) )
	        			{
	        				for (Integer pos : barco) {
	        					x = pos%10;
	        					y = pos/10;
	        					if (ListaJugadores.getMiListaJ().getUnJugador(0).estaTocado(pos)) {
	        						this.tableroUsuario[y][x] = Casilla.Tocado;
	        						setChanged();
	            					notifyObservers("0_"+(y*10+x)+"_T");
	        					} else {
	        						this.tableroUsuario[y][x] = Casilla.Barco;
	        						setChanged();
	            					notifyObservers("0_"+(y*10+x)+"_B");
	        					}
	        				}
	        			}
					}
				} else {
					setChanged();
					notifyObservers("0_"+(y*10+x)+"_A");
				}
			}
		}
    }
}
