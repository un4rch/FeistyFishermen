package packModeloControlador;

import java.util.ArrayList;

public class BarcoFactory {

    private static BarcoFactory miBarcoFactory;

    private BarcoFactory()
    {

    }

    public static BarcoFactory getMiBarcoFactory()
    {
        if (BarcoFactory.miBarcoFactory == null)
        {
            BarcoFactory.miBarcoFactory = new BarcoFactory();
        }
        return BarcoFactory.miBarcoFactory;
    }

    public Barco crearBarco(ArrayList<Integer> pPosiciones)
    {
        Barco unBarco = null;

        if(pPosiciones.size() == 1)     //Si el tamano del barco es 1
        {
            unBarco = new Fragata(pPosiciones);
        }
        else if(pPosiciones.size() == 2)
        {
            unBarco = new Destructor(pPosiciones);
        }
        else if(pPosiciones.size() == 3)
        {
            unBarco = new Submarino(pPosiciones);
        }
        else if(pPosiciones.size() == 4)
        {
            unBarco = new Portaaviones(pPosiciones);
        }

        return unBarco;
    }
}
