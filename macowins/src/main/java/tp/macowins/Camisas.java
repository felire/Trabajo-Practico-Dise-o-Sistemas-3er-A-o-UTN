package tp.macowins;

public class Camisas extends Prenda{
	private int precioBase;
	
	public int precioBase()
	{
		return precioBase;
	}
	
	public Camisas(boolean importada){
		this.precioBase = 200;
		this.importada = importada;
	}
}
