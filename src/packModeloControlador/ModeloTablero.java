package packModeloControlador;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Observable;
import java.util.Random;

public class ModeloTablero extends Observable {

    private static ModeloTablero miModeloTablero;
    private ListaBarcos listaBarcosJugador;
    private ListaBarcos listaBarcosEnemigo;
    private int tipoBarco; //Longitud del barco
    private Direccion direccion;
    private int tipoArma;

    private ModeloTablero()
    {
        this.listaBarcosJugador = new ListaBarcos();
        this.listaBarcosEnemigo = new ListaBarcos();
		this.ponerBarcosEnemigo();
        //this.tipoBarco = 1;
        //this.direccion = Direccion.Arriba;
        //this.tipoArma = 1;
    }

    public static ModeloTablero getMiModeloTablero()
    {
        if (ModeloTablero.miModeloTablero == null)
        {
            ModeloTablero.miModeloTablero = new ModeloTablero();
        }
        return(ModeloTablero.miModeloTablero);
    }
    
    public void setTipoBarco(int tipoBarco) {
    	this.tipoBarco = tipoBarco;
    }
    
    public void setDireccion(Direccion dir) {
    	this.direccion = dir;
    }

    /*public boolean verSiHayGanador() //Recorre las 2 listas de barcos y devuelve un booleano que indica si la partida ha terminado, ademÃƒÆ’Ã‚Â¡s de indicar por pantalla quiÃƒÆ’Ã‚Â©n es el ganador
    {
        boolean ganador = true;
        Iterator<Barco> itr = this.getItrBarcosJugador();
        Barco unBarco = null;
        while (itr.hasNext() && ganador)
        {
            unBarco = itr.next();
            if(!unBarco.estaHundido()) //si un barco no esta hundido
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
                if(!unBarco.estaHundido()) //si un barco no estÃƒÆ’Ã‚Â¡ hundido
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
    } */
    
    public void casillaRivalPulsada(int pPos) {       //Si en esta posicion hay un barco, hay que cambiarlo a tocado (de momento porque solo hay bombas), si hay un barco devolver rojo y si hay agua devolver azul
		boolean hayBarco = this.listaBarcosEnemigo.tocarBarco(pPos);	//toca el barco que esta en la posicion dada, y devuelve un booleano que dice si hay un barco en esa pos o no
        //TODO el rival te tiene que devolver el ataque
		setChanged();
		notifyObservers(new Tupla(pPos, hayBarco)); //El color se elige dentro
    }
    
    public void casillaUsuarioPulsada(int pos) {
    	if (listaBarcosJugador.comprobarCantidad(this.tipoBarco)) //Comprueba que se pueda anadir un barco del tipo seleccionado
    	{
    		ArrayList<Integer> posisBarco = this.ponerBarco(pos);
    		if (posisBarco != null) {
    			if (!listaBarcosJugador.tieneAlgunAdyacente(posisBarco, this.direccion)) {
    				listaBarcosJugador.anadirBarco(posisBarco);
    				setChanged();
    				notifyObservers(posisBarco);
    			}
    		}
    	}
    }

	private void ponerBarcosEnemigo()
	{
		//1 barco de longitud 4
		this.tipoBarco = 4;
		this.ponerBarcosAlAzar(1); //Cont es el número de barcos de ese tipo que tiene que poner

		//2 barcos de longitud 3
		this.tipoBarco = 3;
		this.ponerBarcosAlAzar(2);

		//3 barcos de longitud 2
		this.tipoBarco = 2;
		this.ponerBarcosAlAzar(3);

		//4 barcos de longitud 1
		this.tipoBarco = 1;
		this.ponerBarcosAlAzar(4);
	}

	private void ponerBarcosAlAzar(int cont)
	{
		while(cont > 0)
		{
			int pos = new Random().nextInt(99);	//Coge un número al azar del 0 al 99
			this.direccion = this.cogerDireccionAlAzar();
			ArrayList<Integer> lista = this.ponerBarco(pos);	//Si devuelve null significa que no se puede poner el barco (porque no es posible ponerlo en esas posiciones)
			if(lista != null && !this.listaBarcosEnemigo.tieneAlgunAdyacente(lista, this.direccion))
			{
				this.listaBarcosEnemigo.anadirBarco(lista);
				cont--;
			}
		}
	}

	private ArrayList<Integer> ponerBarco(int pos)
	{
		ArrayList<Integer> posisBarco = new ArrayList<Integer>();
		int x = pos%10;
		int y = pos/10;
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
		return posisBarco;
	}

	private Direccion cogerDireccionAlAzar()
	{
		int num = new Random().nextInt(Direccion.values().length);
		return Direccion.values()[num];
	}

}
