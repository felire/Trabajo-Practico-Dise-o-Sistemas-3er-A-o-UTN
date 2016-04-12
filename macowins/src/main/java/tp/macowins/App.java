package tp.macowins;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.swing.JFrame;

/**
 * Hello world!
 *
 */
public class App 
{
	private ArrayList<Venta> ventasTotales;
	private ArrayList<Venta> ventasTotalesAux;
	public App(){
		ventasTotales = new ArrayList<Venta>();
	}
    public ArrayList<Venta> getVentasTotales() {
		return ventasTotales;
	}
	public void setVentasTotales(ArrayList<Venta> ventasTotales) {
		this.ventasTotales = ventasTotales;
	}
	public double calcularGanancia(Fecha fecha){
		int i = 0;
		double acum = 0;
		ventasTotalesAux = (ArrayList<Venta>) this.ventasTotales.stream().filter(venta1 -> venta1.mismaFecha(fecha)).collect(Collectors.toList());
		for(;i<this.ventasTotalesAux.size();i++){
			acum += ventasTotalesAux.get(i).ganancia();
		}
		return acum;
	}
	public static void main( String[] args )
    {
		Prenda.setValorFijo(50);
    }
}