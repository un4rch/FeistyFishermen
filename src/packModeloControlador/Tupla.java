package packModeloControlador;

public class Tupla 
{
	
	private int pos;
	private boolean tocado;
	
	public Tupla(int pPos, boolean pTocado)
	{
		this.pos = pPos;
		this.tocado = pTocado;
	}
	
	public int getPos()
	{
		return this.pos;
	}
	
	public boolean estaTocado()
	{
		return this.tocado;
	}
	
	public void tocar()
	{
		this.tocado = true;
	}
	
	public boolean tieneEstaPos(int pPos)
	{
		return this.pos == pPos;
	}
}
