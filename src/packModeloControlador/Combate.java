package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class Combate extends Observable{
    private static Combate miCombate = null;
    private ActuarStrategy herramienta;
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
    			if (i>=0 && j>=0 && i<=9 && j<=9) {
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
    
    
    public void actuar(boolean esUsuario, int pPos, Arma pArma) {
    	if (pArma.equals(Arma.Bomba)) {
    		this.herramienta = new BombaStrategy();
    	} else if (pArma.equals(Arma.Misil)) {
    		this.herramienta = new MisilStrategy();
    	} else if (pArma.equals(Arma.Radar)) {
    		this.herramienta = new RadarStrategy();
    	} else if (pArma.equals(Arma.Escudo)) {
    		this.herramienta = new EscudoStrategy();
    	} else if (pArma.equals(Arma.Reparacion)) {
    		this.herramienta = new ReparacionStrategy();
    	}
    	herramienta.actuar(pPos, esUsuario);
    }
    
    public boolean esCasilla(boolean esUsuario, Integer pPos, Casilla pCasilla) {
    	if (esUsuario) {
    		return this.tableroUsuario[pPos/10][pPos%10].equals(pCasilla);
    	} else {
    		return this.tableroRival[pPos/10][pPos%10].equals(pCasilla);
    	}
    }
    
    public void setCasilla(boolean esUsuario, Integer pPos, Casilla pCasilla) {
    	if (esUsuario) {
    		this.tableroUsuario[pPos/10][pPos%10] = pCasilla;
    	} else {
    		this.tableroRival[pPos/10][pPos%10] = pCasilla;
    	}
    	String tipoCasilla;
    	if (pCasilla.equals(Casilla.EscudoDanado)) {
    		tipoCasilla = "ED";
    	} else {
    		tipoCasilla = pCasilla.toString().charAt(0)+"";
    	}
    	Integer esUsuarioInt;
    	if (esUsuario) {
    		esUsuarioInt = 0;
    	} else {
    		esUsuarioInt = 1;
    	}
    	setChanged();
		notifyObservers(esUsuarioInt+"_"+(pPos)+"_"+tipoCasilla);
    }
    
    public void mostrarCasillas(boolean esUsuario, ArrayList<Integer> pPosis) {
    	
    	Iterator<Integer> itr = pPosis.iterator();
		while (itr.hasNext())
		{
			Integer pos = itr.next();
			Casilla casilla;
			if (esUsuario) {
	    		casilla = this.tableroUsuario[pos/10][pos%10];
	    	} else {
	    		casilla = this.tableroRival[pos/10][pos%10];
	    	}
			String tipoCasilla;
	    	if (casilla.equals(Casilla.EscudoDanado)) {
	    		tipoCasilla = "ED";
	    	} else {
	    		tipoCasilla = casilla.toString().charAt(0)+"";
	    	}
	    	Integer esUsuarioInt;
	    	if (esUsuario) {
	    		esUsuarioInt = 0;
	    	} else {
	    		esUsuarioInt = 1;
	    	}
	    	setChanged();
			notifyObservers(esUsuarioInt+"_"+(pos)+"_"+tipoCasilla);
		setChanged();
		notifyObservers('R');
		}
    }
}
