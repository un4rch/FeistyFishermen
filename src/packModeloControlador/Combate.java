package packModeloControlador;

public class Combate {
    private static Combate miCombate = null;
    private Casilla[][] tableroUsuario;
    private Casilla[][] tableroRival;

    private Combate()
    {
    	this.tableroUsuario = new Casilla[10][10];
    	this.tableroRival = new Casilla[10][10];
    	for (int i = 0; i<=9; i++) {
    		for (int j = 0; j<=9; j++) {
    			tableroUsuario[i][j] = Casilla.Agua;
    			tableroRival[i][j] = Casilla.Agua;
        	}
    	}
    }

    public static Combate getMiCombate()
    {
        if(Combate.miCombate == null)
        {
            Combate.miCombate = new Combate();
        }
        return Combate.miCombate;
    }
    
    //Este metodo esta sin terminar
    /*public boolean actuailzarPos(int posX, int posY, Arma tipoArma, boolean turno) {
    	boolean tocado = false;
    	if (turno) {
    		if (tipoArma == Arma.Bomba) {
    			this.tableroUsuario[posY][posX] = ;
    		} else if (tipoArma == Arma.Misil) {
    			
    		} etc...
    	} else {
    		if () {
    			
    		}
    	}
    	return tocado;
    }*/
    
    
}
