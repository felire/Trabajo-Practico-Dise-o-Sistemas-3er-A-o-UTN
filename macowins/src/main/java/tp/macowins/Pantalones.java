package tp.macowins;

public class Pantalones extends Prenda{
	private int tela;
	private int precioBase;
	
	public Pantalones(int tela,boolean importada){
		this.tela = tela;
		this.precioBase = 250;
		this.importada = importada;
	}
	public int precioBase()
	{
		return precioBase+this.tela;
	}
	
	
}
