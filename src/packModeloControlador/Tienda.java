package packModeloControlador;

import java.util.Observable;

public class Tienda  extends Observable{
	private int precioRadares;
	private int precioEscudos;
	private int precioMisiles;
	private int cantidadRadares;
	private int cantidadEscudos;
	private int cantidadMisiles;
	private static Tienda miTienda = null;
	
	private Tienda()
	{
		this.cantidadRadares = 2;
		this.cantidadEscudos = 6;
		this.cantidadMisiles = 4;
		this.precioRadares = 300;
		this.precioEscudos = 40;
		this.precioMisiles = 100;
	}
	
	public static Tienda getTienda()
	{
		if(Tienda.miTienda == null)
		{
			Tienda.miTienda = new Tienda();
		}
		return Tienda.miTienda;
	}
	
	public void comprar(String pNombreArma)
	{
		if (this.sePuedeComprar(pNombreArma)) 
			{
				if (pNombreArma=="Escudo") 
				{
					if (ListaJugadores.getMiListaJ().comprobarYRestarDineroUsuario(40)) 
					{
						this.comprarArma(Arma.Escudo, true);
						System.out.println("Escudo comprado");
					}
					else
					{
						System.out.println("Dinero insuficiente");
					}
				}
				else if (pNombreArma=="Misil")
				{
					if (ListaJugadores.getMiListaJ().comprobarYRestarDineroUsuario(100)) 
					{
						this.comprarArma(Arma.Misil, true);
						System.out.println("Misil comprado");
					}
					else
					{
			    		System.out.println("Dinero insuficiente");
					}
				}
				else if (pNombreArma=="Radar")
				{
					if (ListaJugadores.getMiListaJ().comprobarYRestarDineroUsuario(300)) 
					{
						this.comprarArma(Arma.Radar, true);
						System.out.println("Radar comprado");
					}
					else
					{
			    		System.out.println("Dinero insuficiente");
					}
				}
			}
		else
		{
			System.out.println("No queda stock en la tienda D,:");
		}
	}
	
	public boolean comprarArma(Arma pArma, boolean pEsUsuario) // se ha comprobado antes si se puede comprar
	{
		boolean sePuede = true;
		if(pArma.equals(Arma.Radar))
		{
			this.cantidadRadares = this.cantidadRadares -1;	
			if (pEsUsuario)
			{
				ListaJugadores.getMiListaJ().comprarArma(pArma, pEsUsuario);
				setChanged();
				notifyObservers('R');
			}
			
		}
		else if(pArma.equals(Arma.Escudo))
		{
			this.cantidadEscudos = this.cantidadEscudos -1;	
			if (pEsUsuario)
			{
				ListaJugadores.getMiListaJ().comprarArma(pArma, pEsUsuario);
				System.out.println("SSSSSSSS");
				setChanged();
				notifyObservers('E');
			}
		}
		else if(pArma.equals(Arma.Misil))
		{
			this.cantidadMisiles = this.cantidadMisiles -1;
			if (pEsUsuario)
			{
				ListaJugadores.getMiListaJ().comprarArma(pArma, pEsUsuario);
				setChanged();
				notifyObservers('M');
			}
		}
		else
		{
			sePuede = false;
		}
		
		return sePuede;
	}
	
	public boolean sePuedeComprar(String pArma)
	{
		boolean sePuede = true;
		if(pArma.equals("Radar") && this.cantidadRadares > 0){}
		else if(pArma.equals("Escudo") && this.cantidadEscudos > 0)	{}
		else if(pArma.equals("Misil") && this.cantidadMisiles > 0)	{}
		else
		{
			sePuede = false;
		}
		
		return sePuede;
	}
	
}
