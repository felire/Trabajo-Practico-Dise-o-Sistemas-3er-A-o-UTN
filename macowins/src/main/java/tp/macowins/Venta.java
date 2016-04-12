package tp.macowins;

public class Venta {
	private Prenda unaPrenda;
	private int cantidad;
	private Fecha fecha;
	public Venta (Prenda unaPrenda, int cantidad, Fecha fecha){
		this.unaPrenda = unaPrenda;
		this.cantidad = cantidad;
		this.fecha = fecha;
	}
	public boolean mismaFecha(Fecha unaFecha){
		return fecha.compararFecha(unaFecha);
	}
	public Fecha getFecha() {
		return fecha;
	}
	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}
	public double ganancia(){
		return cantidad*unaPrenda.precioFinal();
	}
}