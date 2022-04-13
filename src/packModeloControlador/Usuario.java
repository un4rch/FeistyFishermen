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
        if (this.getFlota().comprobarCantidad(pTipo))
        {
            ArrayList<Integer> unBarco = super.ponerBarco(pPos,pDir,pTipo);
            if (Combate.getMiCombate().comprobarAdyacentes(unBarco,true))
            {
                Combate.getMiCombate().colocarBarco(unBarco,true));
                this.getFlota().anadirBarco(unBarco);
            }
        }
    }
}
