package packModeloControlador;

public class Combate {
    private static Combate miCombate = null;

    private Combate()
    {

    }

    public static Combate getMiCombate()
    {
        if(Combate.miCombate == null)
        {
            Combate.miCombate = new Combate();
        }
        return Combate.miCombate;
    }
}
