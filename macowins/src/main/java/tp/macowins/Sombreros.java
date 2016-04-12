package tp.macowins;

public class Sombreros extends Prenda {
	private int coeficienteMetro;
	private int precioBase;
	public Sombreros(int coeficiente,boolean importada)
	{
		this.coeficienteMetro=coeficiente;
		this.importada=importada;
		this.precioBase=150;
	}
	public int precioBase()
	{
		return precioBase*(1+this.coeficienteMetro);
	}
}
