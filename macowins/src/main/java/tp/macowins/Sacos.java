package tp.macowins;

public class Sacos extends Prenda{
	private int botones;
	private int precioBase;
	
	public Sacos(int botones, boolean importada){
		this.botones=botones;
		this.precioBase = 300;
		this.importada = importada;		
	}
	
	public int precioBase()
	{
		return precioBase+10*this.botones;
	}
}
