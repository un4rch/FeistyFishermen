package packModeloControlador;

import java.util.ArrayList;

public abstract class Jugador
{
    private ListaBarcos flota;
    private Arsenal arsenal;    //Armas
    private int tesoreria;      //Dinero
    public Jugador()
    {
        this.flota = new ListaBarcos();
        this.arsenal = new Arsenal();
        this.tesoreria = 0;
    }

    protected ListaBarcos getFlota()
    {
        return this.flota;
    }

    public void sumarCasillas(int pCant)
    {
        this.flota.sumarCasillas(pCant);
    }

    public void restarCasillas(int pCant)
    {
        this.flota.restarCasillas(pCant);
    }
    
    public boolean estaHundido(int pPos)
    {
    	return this.flota.estaHundido(pPos);
    }
    
    public ArrayList<Integer> perteneceA(int pPos)
    {
    	return (this.getFlota().perteneceA(pPos));
    }

    public ArrayList<Integer> sePuedePonerBarco(int pPos, Direccion pDir, int pTipoBarco)
    {
        /** Si es posible colocar el barco con esos parametros, devuelve un ArrayL<int> con las posiciones, si no devuelve null **/
        //TODO pone el barco en la this.FLOTA (no se si esta sin terminar)
        ArrayList<Integer> posisBarco = new ArrayList<Integer>();
        int x = pPos%10;
        int y = pPos/10;

        if (pDir == Direccion.Arriba && y-pTipoBarco+1>=0) { //Arriba
            for (int i=0; i<pTipoBarco; i++) {
                posisBarco.add(10*(y-i)+x);
            }
        } else if (pDir == Direccion.Derecha && x+pTipoBarco-1<=9) { //Derecha
            for (int i=0; i<pTipoBarco; i++) {
                posisBarco.add(10*y+x+i);
            }
        } else if (pDir == Direccion.Abajo && y+pTipoBarco-1<=9) { //Abajo
            for (int i=0; i<pTipoBarco; i++) {
                posisBarco.add(10*(y+i)+x);
            }
        } else if (pDir == Direccion.Izquierda && x-pTipoBarco+1>=0) { //izquierda
            for (int i=0; i<pTipoBarco; i++) {
                posisBarco.add(10*y+x-i);
            }
        }else {
            posisBarco = null;
        }

        return posisBarco;
    }
    
    public void actuar(int pPos, Arma pArma){}

    public void anadirBarco(int pPos, Direccion pDir, int pTipoBarco){}

    public boolean tieneLaFlotaHundida()
    {
    	return (this.flota.flotaHundida());
    }
    
    public void tocar(Integer pPos) {
    	this.flota.tocarBarco(pPos);
    }
    
    public boolean estaTocado(Integer pPos) {
    	return this.flota.estaTocado(pPos);
    }
}
