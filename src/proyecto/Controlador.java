package proyecto;

import java.util.ArrayList;

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

    public boolean verSiHayGanador() //Recorre las 2 listas de barcos y devuelve un booleano que indica si la partida ha terminado, además de indicar por pantalla quién es el ganador
    {
        //TODO
        return(false);
    }

}
