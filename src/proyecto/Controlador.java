package proyecto;

public class Controlador {

    private static Controlador miControlador;
    private ArrayList<Barco> listaBarcosJugador;
    private ArrayList<Barco> listaBarcosEnemigo;

    private Controlador()
    {

    }

    public static Controlador getMiControlador()
    {
        if (Controlador.miControlador == null)
        {
            Controlador.miControlador = new Controlador();
        }
        return(Controlador.miControlador);
    }

}
