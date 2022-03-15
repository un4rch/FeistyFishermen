package proyecto;

public class Tupla 
{
	
	private int x;
	private int y;
	private boolean tocado;
	
	public Tupla(int pX, int pY, boolean pTocado)
	{
		this.x = pX;
		this.y = pY;
		this.tocado = pTocado;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public boolean estaTocado()
	{
		return this.tocado;
	}
	
}