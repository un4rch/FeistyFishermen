package packModeloControlador;

public class Usuario extends Jugador
{
    public Usuario()
    {
        super();
    }

    //tienen distintos metodos

    public void anadirBarco(int pPos, Direccion pDir, int pTipo)
    {
        //TODO (sin terminar)
        this.getFlota().comprobarCantidad(pTipo);
    }
}
