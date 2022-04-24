package packModeloControlador;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import packVista.PopupGanador;

public class ModeloTablero {

    private static ModeloTablero miModeloTablero;
    private int tipoBarco; //Longitud del barco
    private Direccion direccion;
    private Arma tipoArma;
    private Dificultad dificultad;
    private boolean partidaLista;
    private boolean partidaTerminada;

    private ModeloTablero()
    {
        this.partidaLista = false;
		this.partidaTerminada = false;
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
    
    public void setTipoArma(Arma tipoArma) {
    	this.tipoArma = tipoArma;
    }
    
    public void setDireccion(Direccion dir) {
    	this.direccion = dir;
    }
    
    public void setDificultad(Dificultad dif) {
    	this.dificultad = dif;
    }

    public boolean partidaLista() {
    	return this.partidaLista;
    }
    /*public void rivalAtaca() {	//TODO hay que pasar esto a los metodos actuar(), armaAleatoria() y posAleatoria() de la clase Rival
    	int pos = 0;
    	Tupla posAleatoria;
    	boolean posRandom = true;
    	int prob = new Random().nextInt(19); //Numero aleatorio que si se encuentra en el rango de la dificultad la IA acierta si o si, sino escoje posicion random
    	
    	if (this.dificultad == Dificultad.Facil && prob>=0 && prob<=1) {
    		posRandom = false;
    	} else if (this.dificultad == Dificultad.Medio && prob>=0 && prob<=3) {
    		posRandom = false;
    	} else if (this.dificultad == Dificultad.Dificil && prob>=0 && prob<=5) {
    		posRandom = false;
    	} else if (this.dificultad == Dificultad.Demente && prob>=0 && prob<=7) {
    		posRandom = false;
    	}
    	
    	try {
    		System.out.println("La IA esta pensando"); //Ponerlo en la pantalla no consola
    		Thread.sleep(500);
    	} catch (Exception e) {
    		
    	}
    	
    	if (posRandom) {
    		pos = new Random().nextInt(99);
    	} else {
    		posAleatoria = listaBarcosJugador.getBarcoAleatorio().getPosAleatoria();
    		while (posAleatoria.estaTocado()) {
    			posAleatoria = listaBarcosJugador.getBarcoAleatorio().getPosAleatoria();
    		}
    		pos = posAleatoria.getPos();
    	}
    	
    	if (listaBarcosJugador.tocarBarco(pos)) {
    		System.out.println("La IA ha tocado, turno del usuario");
    		setChanged();
    		notifyObservers(pos+"_t");
    	} else {
    		System.out.println("La IA no ha tocado, turno del usuario");
    		setChanged();
    		notifyObservers(pos+"_f");
    	}
    }*/
    
    public void casillaRivalPulsada(int pPos) {       //Si en esta posicion hay un barco, hay que cambiarlo a tocado (de momento porque solo hay bombas), si hay un barco devolver rojo y si hay agua devolver azul
		//if (!partidaTerminada)
		//{
		//	boolean hayBarco = this.listaBarcosEnemigo.tocarBarco(pPos);	//toca el barco que esta en la posicion dada, y devuelve un booleano que dice si hay un barco en esa pos o no
		//	setChanged();
		//	notifyObservers(new Tupla(pPos, hayBarco)); //El color se elige dentro
		//	this.rivalAtaca(); //El rival nos devuelve el ataque
		//	this.partidaTerminada=this.verSiHayGanador();
		//}


		//Llama a ljugadores y recibe un booleano de vuelta

		if(!this.partidaLista)
		{
			System.out.println("¡Tienes que terminar de poner tus barcos!");
		}
		else //Se ha pulsado la casilla para atacar
		{
			if (this.tipoArma.equals(Arma.Bomba) || this.tipoArma.equals(Arma.Misil) || this.tipoArma.equals(Arma.Radar)) //si el arma seleccionada es correcta (de ataque) se juega la ronda
			{
				this.partidaTerminada = ListaJugadores.getMiListaJ().jugarRonda(pPos, this.tipoArma); //Al final de la ronda te dice si ha ganado alguien o no
			}
			else
			{
				System.out.println("¡No puedes poner una defensa al rival!");
			}
		}
    }
    
    public void casillaUsuarioPulsada(int pPos) { //TODO va a ir al usuario si partida no lista, si esta lista (poner escudo o reparar) a listajugadores
    	//if (listaBarcosJugador.comprobarCantidad(this.tipoBarco)) //Comprueba que se pueda anadir un barco del tipo seleccionado
    	//{
    	//	ArrayList<Integer> posisBarco = this.ponerBarco(pPos);
    	//	if (posisBarco != null) {
    	//		if (!listaBarcosJugador.tieneAlgunAdyacente(posisBarco, this.direccion)) {
    	//			listaBarcosJugador.anadirBarco(posisBarco);
    	//			setChanged();
    	//			notifyObservers(posisBarco);
    	//		}
    	//	}
    	//}
    	//if (!(listaBarcosJugador.comprobarCantidad(4) || listaBarcosJugador.comprobarCantidad(3) || listaBarcosJugador.comprobarCantidad(2) || listaBarcosJugador.comprobarCantidad(1))) {
    	//	this.partidaLista = true;
    	//	System.out.println("Partida lista");
    	//}


		///
		if(!this.partidaLista) //Se ha pulsado la casilla para poner barcos
		{
			ListaJugadores.getMiListaJ().getUnJugador(0).anadirBarco(pPos, this.direccion, this.tipoBarco); //Le pasas al USUARIO la posicion y la direccion
		}
		else //Se ha pulsado la casilla para poner ESCUDO o REPARAR barco (DEFENSA)
		{
			if (this.tipoArma.equals(Arma.Escudo) || this.tipoArma.equals(Arma.Reparacion)) //si el arma seleccionada es correcta (de defensa) se juega la ronda
			{
				this.partidaTerminada = ListaJugadores.getMiListaJ().jugarRonda(pPos, this.tipoArma); //Al final de la ronda te dice si ha ganado alguien o no
			}
			else
			{
				System.out.println("¡No puedes atacarte a ti mismo!");
			}
		}
    }

	//private ArrayList<Integer> ponerBarco(int pos)
	//{
	//	ArrayList<Integer> posisBarco = new ArrayList<Integer>();
	//	int x = pos%10;
	//	int y = pos/10;
	//	if (this.direccion == Direccion.Arriba && y-tipoBarco+1>=0) { //Arriba
	//		for (int i=0; i<tipoBarco; i++) {
	//			posisBarco.add(10*(y-i)+x);
	//		}
	//	} else if (this.direccion == Direccion.Derecha && x+tipoBarco-1<=9) { //Derecha
	//		for (int i=0; i<tipoBarco; i++) {
	//			posisBarco.add(10*y+x+i);
	//		}
	//	} else if (this.direccion == Direccion.Abajo && y+tipoBarco-1<=9) { //Abajo
	//		for (int i=0; i<tipoBarco; i++) {
	//			posisBarco.add(10*(y+i)+x);
	//		}
	//	} else if (this.direccion == Direccion.Izquierda && x-tipoBarco+1>=0) { //izquierda
	//		for (int i=0; i<tipoBarco; i++) {
	//			posisBarco.add(10*y+x-i);
	//		}
	//	}else {
	//		posisBarco = null;
	//	}
	//	return posisBarco;
	//}

	//private void ponerBarcosEnemigo()
	//{
	//	//1 barco de longitud 4
	//	this.tipoBarco = 4;
	//	this.ponerBarcosAlAzar(1); //Cont es el numero de barcos de ese tipo que tiene que poner
//
	//	//2 barcos de longitud 3
	//	this.tipoBarco = 3;
	//	this.ponerBarcosAlAzar(2);
//
	//	//3 barcos de longitud 2
	//	this.tipoBarco = 2;
	//	this.ponerBarcosAlAzar(3);
//
	//	//4 barcos de longitud 1
	//	this.tipoBarco = 1;
	//	this.ponerBarcosAlAzar(4);
	//}

	//private void ponerBarcosAlAzar(int cont)
	//{
	//	while(cont > 0)
	//	{
	//		int pos = new Random().nextInt(99);	//Coge un numero al azar del 0 al 99
	//		this.direccion = this.cogerDireccionAlAzar();
	//		ArrayList<Integer> lista = this.ponerBarco(pos);	//Si devuelve null significa que no se puede poner el barco (porque no es posible ponerlo en esas posiciones)
	//		if(lista != null && !this.listaBarcosEnemigo.tieneAlgunAdyacente(lista, this.direccion))
	//		{
	//			this.listaBarcosEnemigo.anadirBarco(lista);
	//			cont--;
	//		}
	//	}
	//}

	//private Direccion cogerDireccionAlAzar()
	//{
	//	int num = new Random().nextInt(Direccion.values().length);
	//	return Direccion.values()[num];
	//}

}
