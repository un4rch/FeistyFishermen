package packModeloControlador;

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
}
