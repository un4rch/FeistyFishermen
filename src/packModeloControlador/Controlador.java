package packModeloControlador;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;

public class Controlador extends Observable {

    private static Controlador miControlador;
    private ListaBarcos listaBarcosJugador;
    private ListaBarcos listaBarcosEnemigo;
    private int tipoBarco; //Longitud del barco
    private Direccion direccion;
    private int tipoArma;

    private Controlador()
    {
        this.listaBarcosJugador = new ListaBarcos();
        this.listaBarcosEnemigo = new ListaBarcos();
        this.tipoBarco = 3;
        this.direccion = Direccion.Abajo;
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

    /*public boolean verSiHayGanador() //Recorre las 2 listas de barcos y devuelve un booleano que indica si la partida ha terminado, ademÃƒÂ¡s de indicar por pantalla quiÃƒÂ©n es el ganador
    {
        boolean ganador = true;
        Iterator<Barco> itr = this.getItrBarcosJugador();
        Barco unBarco = null;
        while (itr.hasNext() && ganador)
        {
            unBarco = itr.next();
            if(!unBarco.estaHundido()) //si un barco no estÃƒÂ¡ hundido
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
                if(!unBarco.estaHundido()) //si un barco no estÃƒÂ¡ hundido
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
    
    public Color casillaRivalPulsada(int pPos) {       //Si en esta posiciÃ³n hay un barco, hay que cambiarlo a tocado (de momento porque solo hay bombas), si hay un barco devolver rojo y si hay agua devolver azul
        Iterator<Barco> itr = this.getItrBarcosEnemigo();
        boolean enc = false;
        Barco unBarco = null;
        while(itr.hasNext() && !enc)
        {
            unBarco = itr.next();
            if(unBarco.tieneEstaPos(pPos))
            {
                enc = true;
            }
        }

        Color unColor = null;

        if(enc)     //Ha encontrado el barco, hay que tocarlo y devolver el color rojo
        {
            unBarco.tocar(pPos);
            unColor = Color.red;
        }

        else        //No ha encontrado el barco, por lo tanto, es agua
        {
            unColor = Color.blue;
        }


        return unColor;
    }*/
    
    public void casillaUsuarioPulsada(int pos) {
    	ArrayList<Integer> posisBarco = new ArrayList<Integer>();
    	int x = pos%10;
    	int y = pos/10;
    	posisBarco.add(pos);
    	if (this.direccion == Direccion.Arriba && y-tipoBarco+1>=0) { //Arriba
    		for (int i=0; i<tipoBarco; i++) {
    			posisBarco.add(10*(y-i)+x);
    		}
    	} else if (this.direccion == Direccion.Derecha && x+tipoBarco-1<=9) { //Derecha
    		for (int i=0; i<tipoBarco; i++) {
    			posisBarco.add(10*y+x+i);
    		}
    	} else if (this.direccion == Direccion.Abajo && y+tipoBarco-1<=9) { //Abajo
    		for (int i=0; i<tipoBarco; i++) {
    			posisBarco.add(10*(y+i)+x);
    		}
    	} else if (this.direccion == Direccion.Izquierda && x-tipoBarco+1>=0) { //izquierda
    		for (int i=0; i<tipoBarco; i++) {
    			posisBarco.add(10*y+x-i);
    		}
    	}else {
			posisBarco = null;
		}
    	if (posisBarco != null) {
    		Barco barcoNuevo = new Barco(posisBarco);
        	if (!listaBarcosJugador.tieneAlgunAdyacente(barcoNuevo, this.direccion)) {
        		listaBarcosJugador.anadirBarco(barcoNuevo);
        		setChanged();
        		notifyObservers(posisBarco);
        	}
    	}
    }

}
