package packModeloControlador;

import java.util.ArrayList;
import java.util.Random;

public class Rival extends Jugador
{
    private Dificultad dificultad;
    
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
    
    public void setDificultad(Dificultad dif) {
    	this.dificultad = dif;
    }

    private void ponerBarcosAlAzar(int pCont, int pTipo)
    {
        /** @param pCont:   numero de barcos que tiene que poner de ese tipo **/
        while(pCont > 0)
        {
            int pos = new Random().nextInt(99);	//Coge un numero al azar del 0 al 99
            Direccion dir = this.cogerDireccionAlAzar();

            ArrayList<Integer> unBarco = super.sePuedePonerBarco(pos, dir, pTipo);	//Si devuelve null significa que no se puede poner el barco (porque no es posible ponerlo en esas posiciones)

            if(unBarco != null && !Combate.getMiCombate().comprobarAdyacentes(unBarco, false))
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
        /**  lo mismo que usuario, pero elige el arma y posiciÃƒÂ³n de forma random. Dependiendo del número obtenido, el rival accede a la tienda **/
    	int unNum = new Random().nextInt(9);
    	if (unNum == 5)
    	{
    		Tienda.getTienda().comprar("Escudo", false);
    	}
    	else if (unNum == 7)
    	{
    		Tienda.getTienda().comprar("Misil", false);
    	}
    	else if (unNum == 3)
    	{
    		Tienda.getTienda().comprar("Radar", false);
    	}
    	else if (unNum == 1)
    	{
    		Tienda.getTienda().comprar("Reparacion", false);
    	}
        Arma unArma = this.armaAleatoria();
        int unaPos = this.posAleatoria();
        Combate.getMiCombate().actuar(false, unaPos, unArma);
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

    public int comprobarYRestarDineroRival(int pPrecio)
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
    
}
