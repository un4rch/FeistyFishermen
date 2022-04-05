package packModeloControlador;

public class Arsenal {
    private int radares;  //El int es la cantidad que hay de ese arma
    private int escudos;

    public Arsenal()
    {
        this.radares = 0;
        this.escudos = 0;
    }

    public void anadirRadares(int pCant)
    {
        this.radares = this.radares + pCant;
    }

    public void anadirEscudos(int pCant)
    {
        this.escudos = this.escudos + pCant;
    }

    public void usarRadar()
    {
        this.radares--;
    }

    public void usarEscudo()
    {
        this.escudos--;
    }
}
