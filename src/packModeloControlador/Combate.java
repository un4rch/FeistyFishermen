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
    
    //Hay que implementarlo: dado una lista de posiciones devuelve si se puede poner el barco o si hay algún otro barco adyacente
    /*public boolean comprobarAdyacentes(ArrayList<Integer> pPosis, boolean turno) {*/    
    
    //Hay que implementarlo: dado una lista de posiciones coloca un barco en esas posiciones. Este método también se encarga de hacer el notifyObservers.
    /*public void colocarBarco(ArrayList<Integer> pPosis, boolean turno) {*/
}
