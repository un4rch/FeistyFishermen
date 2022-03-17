package proyecto;

import java.util.ArrayList;
import java.util.Iterator;

public class Controlador {

    private static Controlador miControlador;
    private ArrayList<Barco> listaBarcosJugador;
    private ArrayList<Barco> listaBarcosEnemigo;

    private Controlador()
    {
        this.listaBarcosJugador = new ArrayList<Barco>();
        this.listaBarcosEnemigo = new ArrayList<Barco>();
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

    public boolean verSiHayGanador() //Recorre las 2 listas de barcos y devuelve un booleano que indica si la partida ha terminado, además de indicar por pantalla quién es el ganador
    {
        boolean ganador = true;
        Iterator<Barco> itr = this.getItrBarcosJugador();
        Barco unBarco = null;
        while (itr.hasNext() && ganador)
        {
            unBarco = itr.next();
            if(!unBarco.estaHundido()) //si un barco no está hundido
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
                if(!unBarco.estaHundido()) //si un barco no está hundido
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

}
