package packModeloControlador;

public class Arsenal{
    private int radares;  //El int es la cantidad que hay de ese arma
    private int escudos;
    private int misiles;
    private int reparaciones;

    public Arsenal()
    {
        this.radares = 1;
        this.escudos = 1;
        this.misiles = 2;
        this.reparaciones = 2;
    }

    public int getRadares()
    {
    	return this.radares;
    }

    public int getEscudos()
    {
    	return this.escudos;
    }

    public int getMisiles()
    {
    	return this.misiles;
    }
    
    public int getReparaciones()
    {
    	return this.reparaciones;
    }    
    
    public void anadirRadares(int pCant)
    {
        this.radares = this.radares + pCant;
    }

    public void anadirEscudos(int pCant)
    {
        this.escudos = this.escudos + pCant;
    }

    public void anadirMisiles(int pCant)
    {
        this.misiles = this.misiles + pCant;
    }
    
    public void anadirReparaciones(int pCant)
    {
        this.reparaciones = this.reparaciones + pCant;
    }

    public boolean consumirMisil()
    {
    	boolean consumido = false;
    	if (this.tieneSuficientesMisiles())
    	{
            this.misiles--;
            consumido = true;
    	}
    	return consumido;
    }
    
    public boolean consumirRadar()
    {
    	boolean consumido = false;
    	if (this.tieneSuficientesRadares())
    	{
    		this.radares--;
    		consumido = true;
    	}
    	return consumido;
    }

    public boolean consumirEscudo()
    {
    	boolean consumido = false;
    	if (this.tieneSuficientesEscudos())
    	{
    		this.escudos--;
    		consumido = true;
    	}
    	return consumido;
    }
    
    public boolean consumirReparacion()
    {
    	boolean consumido = false;
    	if (this.tieneSuficientesReparaciones())
    	{
    		this.reparaciones--;
    		consumido = true;
    	}
    	return consumido;
    }

    public boolean tieneSuficientesRadares()
    {
        boolean tieneRadares = false;
        if(this.radares >= 1)
        {
            tieneRadares = true;
        }
        return tieneRadares;
    }

    public boolean tieneSuficientesEscudos()
    {
        boolean tieneEscudos = false;
        if(this.escudos >= 1)
        {
            tieneEscudos = true;
        }
        return tieneEscudos;
    }
    
    public boolean tieneSuficientesMisiles()
    {
        boolean tieneMisiles = false;
        if(this.misiles >= 1)
        {
        	tieneMisiles = true;
        }
        return tieneMisiles;
    }
    
    public boolean tieneSuficientesReparaciones()
    {
        boolean tieneReparaciones = false;
        if(this.reparaciones >= 1)
        {
        	tieneReparaciones = true;
        }
        return tieneReparaciones;
    }
}
