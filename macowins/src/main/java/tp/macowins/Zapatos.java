package tp.macowins;

public class Zapatos extends Prenda{
	private int talle;
	private int precioBase;
	public Zapatos(int talle, boolean importada)
	{
		this.talle=talle;
		this.precioBase=400;
		this.importada=importada;
	}
	public int precioBase()
	{
		return precioBase+5*this.talle;
	}
}
