package tp.macowins;

public abstract class Prenda {
	static private int valorFijo;
	protected boolean importada;
	protected Marca marca;
	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public abstract int precioBase();
	
	public static int getValorFijo() {
		return valorFijo;
	}

	public static void setValorFijo(int valorFijo) {
		Prenda.valorFijo = valorFijo;
	}

	public boolean isImportada() {
		return importada;
	}

	public void setImportada(boolean importada) {
		this.importada = importada;
	}
	
	public double precioOriginal(){
		return (this.valorFijo + this.precioBase())*this.importada();
	}
	
	public double precioFinal()
	{
		return marca.coeficiente(this.precioOriginal())*this.precioOriginal();
	}
	
	public double importada(){
		if(this.importada){
			return 1.3;
		}
		else{
			return 1;
		}
	}
	
}