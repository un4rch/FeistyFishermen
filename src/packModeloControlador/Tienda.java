package packModeloControlador;

import java.util.Observable;

public class Tienda  extends Observable{
	private int precioRadares;
	private int precioEscudos;
	private int precioMisiles;
	private int precioReparaciones;
	private int cantidadRadares;
	private int cantidadEscudos;
	private int cantidadMisiles;
	private int cantidadReparaciones;
	private static Tienda miTienda = null;
	
	private Tienda()
	{
		this.cantidadEscudos = 6;
		this.cantidadMisiles = 4;
		this.cantidadRadares = 2;
		this.cantidadReparaciones = 2;
		this.precioRadares = 500;
		this.precioEscudos = 500;
		this.precioMisiles = 200;
		this.precioReparaciones = 700;
	}
	
	public static Tienda getTienda()
	{
		if(Tienda.miTienda == null)
		{
			Tienda.miTienda = new Tienda();
		}
		return Tienda.miTienda;
	}
	
	public int getCantEscudos()
	{
		return this.cantidadEscudos;
	}
	
	public int getCantMisiles()
	{
		return this.cantidadMisiles;
	}
	
	public int getCantRadares()
	{
		return this.cantidadRadares;
	}
	
	public int getCantReparaciones()
	{
		return this.cantidadReparaciones;
	}
	
	public void comprar(String pNombreArma, boolean esUsuario)
	{
		if (this.sePuedeComprar(pNombreArma)) 
			{
			if (esUsuario)
			{
				if (pNombreArma=="Escudo") 
				{
					if (ListaJugadores.getMiListaJ().comprobarYRestarDineroUsuario(500)) 
					{
						this.comprarArma(Arma.Escudo, true);
						System.out.println("Escudo comprado");
						setChanged();
						notifyObservers(Arma.Escudo);	//Avisa a PopUpTienda de que se ha realizado una compra
					}
					else
					{
						System.out.println("Dinero insuficiente");
					}
				}
				else if (pNombreArma=="Misil")
				{
					if (ListaJugadores.getMiListaJ().comprobarYRestarDineroUsuario(200)) 
					{
						this.comprarArma(Arma.Misil, true);
						System.out.println("Misil comprado");
						setChanged();
						notifyObservers(Arma.Misil);	//Avisa a PopUpTienda de que se ha realizado una compra
					}
					else
					{
			    		System.out.println("Dinero insuficiente");
					}
				}
				else if (pNombreArma=="Radar")
				{
					if (ListaJugadores.getMiListaJ().comprobarYRestarDineroUsuario(500)) 
					{
						this.comprarArma(Arma.Radar, true);
						System.out.println("Radar comprado");
						setChanged();
						notifyObservers(Arma.Radar);	//Avisa a PopUpTienda de que se ha realizado una compra
					}
					else
					{
			    		System.out.println("Dinero insuficiente");
					}
				}
				else if (pNombreArma=="Reparacion")
				{
					if (ListaJugadores.getMiListaJ().comprobarYRestarDineroUsuario(700)) 
					{
						this.comprarArma(Arma.Reparacion, true);
						System.out.println("Reparacion comprada");
						setChanged();
						notifyObservers(Arma.Reparacion);	//Avisa a PopUpTienda de que se ha realizado una compra
					}
					else
					{
			    		System.out.println("Dinero insuficiente");
					}
				}
			}
			else //rival
			{
				if (pNombreArma=="Escudo") 
				{
					if (ListaJugadores.getMiListaJ().comprobarYRestarDineroRival(500)) 
					{
						this.comprarArma(Arma.Escudo, false);
						System.out.println("Escudo comprado por el rival");
						setChanged();
						notifyObservers(Arma.Escudo);	//Avisa a PopUpTienda de que se ha realizado una compra
					}
					else
					{
						//Dinero insufuciente
					}
				}
				else if (pNombreArma=="Misil")
				{
					if (ListaJugadores.getMiListaJ().comprobarYRestarDineroRival(200)) 
					{
						this.comprarArma(Arma.Misil, false);
						System.out.println("Misil comprado por el rival");
						setChanged();
						notifyObservers(Arma.Misil);	//Avisa a PopUpTienda de que se ha realizado una compra
					}
					else
					{
						//Dinero insufuciente
					}
				}
				else if (pNombreArma=="Radar")
				{
					if (ListaJugadores.getMiListaJ().comprobarYRestarDineroRival(500)) 
					{
						this.comprarArma(Arma.Radar, false);
						System.out.println("Radar comprado por el rival");
						setChanged();
						notifyObservers(Arma.Radar);	//Avisa a PopUpTienda de que se ha realizado una compra
					}
					else
					{
						//Dinero insufuciente
					}
				}
				else if (pNombreArma=="Reparacion")
				{
					if (ListaJugadores.getMiListaJ().comprobarYRestarDineroRival(700)) 
					{
						this.comprarArma(Arma.Reparacion, false);
						System.out.println("Reparacion comprada por el rival");
						setChanged();
						notifyObservers(Arma.Reparacion);	//Avisa a PopUpTienda de que se ha realizado una compra
					}
					else
					{
						//Dinero insufuciente
					}
				}
			}
		
		}
		else
		{
			//No hay stock en la tienda
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
		else if(pArma.equals(Arma.Reparacion))
		{
			this.cantidadReparaciones = this.cantidadReparaciones -1;
			if (pEsUsuario)
			{
				ListaJugadores.getMiListaJ().comprarArma(pArma, pEsUsuario);
				setChanged();
				notifyObservers('B');		//B de Reparar :)
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
		else if(pArma.equals("Reparacion") && this.cantidadReparaciones > 0)	{}
		else
		{
			sePuede = false;
		}
		
		return sePuede;
	}
	
}
