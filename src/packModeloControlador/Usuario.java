package packModeloControlador;

import java.util.ArrayList;

public class Usuario extends Jugador
{
    public Usuario()
    {
        super();
    }

    //tienen distintos metodos

    public void anadirBarco(int pPos, Direccion pDir, int pTipo)
    {
        //TODO (no se si has terminado este Ruben)
        if (this.getFlota().comprobarCantidad(pTipo))
        {
            ArrayList<Integer> unBarco = super.sePuedePonerBarco(pPos,pDir,pTipo);
            if (unBarco != null && !Combate.getMiCombate().comprobarAdyacentes(unBarco, true)) //Si se puede poner el barco teniendo en cuenta los adyacentes
            {
                Combate.getMiCombate().colocarBarco(unBarco, true); //true porque es el turno del Usuario
                this.getFlota().anadirBarco(unBarco);
            }
        }
        if (this.getFlota().partidaLista())
        {
        	ModeloTablero.getMiModeloTablero().empezarPartida();
        }
    }

    public void actuar(int pPos, Arma pArma)
    {
        /** Dada la pPos del tablero que ha tocado el jugador (suya o del enemigo) y el pArma que tiene seleccionada, actua de una manera u otra **/
        Combate.getMiCombate().actuar(true, pPos, pArma);
    }



    public int comprobarYRestarDineroUsuario(int pPrecio)
	{
    	int comprado = -99;
    	int dineros = super.getTesoreria();
    	if (dineros >= pPrecio)
    	{
    		super.restarTesoreria(pPrecio);
    		comprado = super.getTesoreria();
    	}
		return comprado;
	}
    
    public int getDineroUsuario()
    {
    	return super.getTesoreria();
    }
    
    public Arsenal getArsenal()
    {
    	return super.getArsenal();
    }

}
