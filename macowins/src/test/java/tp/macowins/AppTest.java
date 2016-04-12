package tp.macowins;

import org.junit.Before;
import org.junit.Test;

import junit.framework.Assert;


@SuppressWarnings("deprecation")
public class AppTest
{
	App aplicacion = new App();
	@Before
	public void init()
	{
		Prenda.setValorFijo(50);
		Sarkany sarkany = new Sarkany();
		Armani armani = new Armani();
		Prenda saco = new Sacos(10,true);
		Prenda zapato = new Zapatos(5,true);
		saco.setMarca(sarkany);
		zapato.setMarca(armani);		
		Venta venta1 = new Venta(saco,3,new Fecha(1,1,1));
		Venta venta2 = new Venta(zapato,2,new Fecha(1,1,1));
		aplicacion.getVentasTotales().add(venta1);
		aplicacion.getVentasTotales().add(venta2);
	}
    @Test
    public void test1()
    {
    	Assert.assertEquals(4407.0, aplicacion.calcularGanancia(new Fecha(1,1,1)));
    }
    
 
}