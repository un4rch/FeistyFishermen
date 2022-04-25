package packModeloControlador;

import java.util.ArrayList;
import java.util.Random;

public class Rival extends Jugador
{
    public Rival()
    {
        super();
    }

    //tienen distintos metodos

    public void anadirBarco(int p1, Direccion p2, int p3) //Los parametros no se utilizan, se ponen para que la herencia funcione
    {
        int tipoBarco;

        //1 barco de longitud 4
        tipoBarco = 4;
        this.ponerBarcosAlAzar(1, tipoBarco); //Cont es el numero de barcos de ese tipo que tiene que poner

        //2 barcos de longitud 3
        tipoBarco = 3;
        this.ponerBarcosAlAzar(2, tipoBarco);

        //3 barcos de longitud 2
        tipoBarco = 2;
        this.ponerBarcosAlAzar(3, tipoBarco);

        //4 barcos de longitud 1
        tipoBarco = 1;
        this.ponerBarcosAlAzar(4, tipoBarco);
    }

    private void ponerBarcosAlAzar(int pCont, int pTipo)
    {
        /** @param pCont:   numero de barcos que tiene que poner de ese tipo **/
        while(pCont > 0)
        {
            int pos = new Random().nextInt(99);	//Coge un numero al azar del 0 al 99
            Direccion dir = this.cogerDireccionAlAzar();

            ArrayList<Integer> unBarco = super.sePuedePonerBarco(pos, dir, pTipo);	//Si devuelve null significa que no se puede poner el barco (porque no es posible ponerlo en esas posiciones)

            if(unBarco != null && Combate.getMiCombate().comprobarAdyacentes(unBarco, false))
            {
                Combate.getMiCombate().colocarBarco(unBarco, false); //false porque es el turno del rival
                this.getFlota().anadirBarco(unBarco);
                pCont--;
            }
        }
    }

    private Direccion cogerDireccionAlAzar()
    {
        int num = new Random().nextInt(Direccion.values().length);
        return Direccion.values()[num];
    }

    public void actuar(int p1, Arma p2) //No se van a usar los parametros, es para que funcione la herencia
    {
        /**  lo mismo que usuario, pero elige el arma y posición de forma random **/
        Arma unArma = this.armaAleatoria();
        int unaPos = this.posAleatoria();

        if (unArma.equals(Arma.Bomba) || unArma.equals(Arma.Misil) || unArma.equals(Arma.Radar))   //ATAQUE
        {
            Combate.getMiCombate().atacar(unaPos, unArma, false);
        }
        else if (unArma.equals(Arma.Escudo) || unArma.equals(Arma.Reparacion))    //DEFENSA
        {
            Combate.getMiCombate().defensa(unaPos, unArma, false);
        }
    }

    private Arma armaAleatoria() //TODO genera un arma aleatoria (estaria bien hacer lista a la IA para que si no tiene ningun barco tocado no elija reparar por ejemplo)
    {
    	int num = new Random().nextInt(Arma.values().length -1);
    	return Arma.values()[num];
    }

    private int posAleatoria()
    {
    	int num = new Random().nextInt(99);   //TODO hacerlo menos random
    	return num;
    }

}

