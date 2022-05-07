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
    private boolean partidaLista;
    private boolean partidaTerminada;

    private ModeloTablero()
    {
    	this.tipoArma = Arma.Bomba;
    	this.direccion = Direccion.Arriba;
    	this.tipoBarco = 1;
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

    public void empezarPartida() {
    	this.partidaLista = true;
    	System.out.println("La partida ha comenzado!");
    }
    public boolean partidaLista() {
    	return this.partidaLista;
    }
    
    public void casillaRivalPulsada(int pPos) //Se ataca/usa la herramienta asignada en la casilla dada, luego el rival juega su turno.
    {
		//Llama a ljugadores y recibe un booleano de vuelta
    	if(!this.partidaTerminada)
    	{
    		if(!this.partidaLista)
			{
    			System.out.println("Tienes que terminar de poner tus barcos!");
			}
			else //Se ha pulsado la casilla para atacar
			{
				if (this.tipoArma.equals(Arma.Bomba) || this.tipoArma.equals(Arma.Misil) || this.tipoArma.equals(Arma.Radar)) //si el arma seleccionada es correcta (de ataque) se juega la ronda
				{
					this.partidaTerminada = ListaJugadores.getMiListaJ().jugarRonda(pPos, this.tipoArma); //Al final de la ronda te dice si ha ganado alguien o no
				}
				else
				{
					System.out.println("No puedes poner una defensa al rival!");
				}
			}
    	}
    	else
    	{
    		System.out.println("La partida ha terminado, si desea volver a jugar reinicie la aplicaciÃƒÂ³n.");
    	}
    }
    
    public void casillaUsuarioPulsada(int pPos) { //TODO va a ir al usuario si partida no lista, si esta lista (poner escudo o reparar) a listajugadores
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
				System.out.println("Ã‚Â¡No puedes atacarte a ti mismo!");
			}
		}
    }
}
