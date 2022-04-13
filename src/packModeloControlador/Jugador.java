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

    public ArrayList<Integer> ponerBarco(int pPos, Direccion pDir, int pTipoBarco)
    {
        //TODO pone el barco en la this.FLOTA (esta sin terminar)
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

        //TODO pone el barco en Combate (esta sin terminar)
    }
    
    public boolean actuar(int pPos, Arma pArma) 
    {
    	return false;
    }
    
    public boolean flotaHundida()
    {
    	return (this.flota.flotaHundida());
    }

}
