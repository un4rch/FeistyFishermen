package packModeloControlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class Controlador extends Observable {

    private static Controlador miControlador;
    private ArrayList<Barco> listaBarcosJugador;
    private ArrayList<Barco> listaBarcosEnemigo;
    private int tipoBarco; //Longitud del barco
    private int direccion;
    private int tipoArma;

    private Controlador()
    {
        this.listaBarcosJugador = new ArrayList<Barco>();
        this.listaBarcosEnemigo = new ArrayList<Barco>();
        this.tipoBarco = 1;
        this.direccion = 1;
        this.tipoArma = 1;
    }

    public static Controlador getMiControlador()
    {
        if (Controlador.miControlador == null)
        {
            Controlador.miControlador = new Controlador();
        }
        return(Controlador.miControlador);
    }

    private Iterator<Barco> getItrBarcosJugador()
    {
        return this.listaBarcosJugador.iterator();
    }

    private Iterator<Barco> getItrBarcosEnemigo()
    {
        return this.listaBarcosEnemigo.iterator();
    }

    public boolean verSiHayGanador() //Recorre las 2 listas de barcos y devuelve un booleano que indica si la partida ha terminado, ademÃ¡s de indicar por pantalla quiÃ©n es el ganador
    {
        boolean ganador = true;
        Iterator<Barco> itr = this.getItrBarcosJugador();
        Barco unBarco = null;
        while (itr.hasNext() && ganador)
        {
            unBarco = itr.next();
            if(!unBarco.estaHundido()) //si un barco no estÃ¡ hundido
            {
                ganador = false;
            }
        }

        if(ganador)     //Ha ganado el Enemigo
        {
            //TODO (Pop-up para el Enemigo)
        }
        else            //No ha ganado el enemigo, comprobamos si ha ganado el jugador
        {
            ganador = true;     //porque tenemos que volver a comprobar para jugador
            itr = this.getItrBarcosEnemigo();
            while (itr.hasNext() && ganador)
            {
                unBarco = itr.next();
                if(!unBarco.estaHundido()) //si un barco no estÃ¡ hundido
                {
                    ganador = false;
                }
            }

            if(ganador)     //Ha ganado el Jugador
            {
                //TODO (Pop-up para el Jugador)
            }
        }

        return(ganador);    //Devuelve el booleano que indica si ha de terminar la partida
    }
    
    public boolean casillaRivalPulsada(int pos) {
    	System.out.print(pos);
    	return true;
    }
    
    public ArrayList<Integer> casillaUsuarioPulsada(int pos) {
    	ArrayList<Integer> posisBarco = new ArrayList<Integer>();
    	int x = pos%10;
    	int y = pos/10;
    	System.out.println(x+"-"+y);
    	posisBarco.add(pos);
    	if (this.direccion == 1) {
    		for (int i=1; i<=tipoBarco; i++) {
    			posisBarco.add(x+(10*y+i));
    		}
    	} else if (this.direccion == 2) {
    		for (int i=1; i<=tipoBarco; i++) {
    			posisBarco.add(x+i+10*y);
    		}
    	} else if (this.direccion == 3) {
    		for (int i=1; i<=tipoBarco; i++) {
    			posisBarco.add(x+(10*y+i));
    		}
    	} else {
    		for (int i=1; i<=tipoBarco; i++) {
    			posisBarco.add(x+(10*y+i));
    		}
    	}
    	return posisBarco;
    }

}
