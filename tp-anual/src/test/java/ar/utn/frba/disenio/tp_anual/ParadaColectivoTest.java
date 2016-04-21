package ar.utn.frba.disenio.tp_anual;

/* No pude incluir los tests de ParadaColectivo con los otros porque estos usan java.awt.Point 
 * y la CGP usa org.uqbar.geodds.Point y cuando trato de importar ambas librerias en la misma clase
 * tratan de pisarse una a la otra. Si alguno puede solucionarlo, ya sea logrando que coexistan
 * o cambiando el codigo*/

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ParadaColectivoTest 
{
	BuscadorPOIS buscadorPOIS;
	POI paradaDe55;
	POI paradaDe144;
	POI otraParadaDe55;
	Point posicionDel55;
	Point posicionDel144;
	Point posicionDelOtro55;
	LocalDate localDate;
	LocalDateTime fecha;
	List<POI> resultadosDeBusqueda;
	

	@Before
	public void init()
	{
		posicionDel55 = new Point(0,0);
		paradaDe55 = new ParadaDeColectivo("55", posicionDel55);
		posicionDel144 = new Point (15,0);
		paradaDe144 = new ParadaDeColectivo("144", posicionDel144);
		localDate = LocalDate.of(1900, 1, 01);
		fecha = localDate.atTime(1, 00);
		posicionDelOtro55 = new Point(0,4);
		otraParadaDe55 = new ParadaDeColectivo("55", posicionDelOtro55);
		buscadorPOIS = new BuscadorPOIS();
		buscadorPOIS.addPOI(paradaDe55);
		buscadorPOIS.addPOI(paradaDe144);
		buscadorPOIS.addPOI(otraParadaDe55);
	}
	
	@Test
	public void ambasParadasEstanDisponiblesTest()
	{
		assertEquals(true, paradaDe55.estaDisponible(fecha,""));
		assertEquals(true, paradaDe144.estaDisponible(fecha, ""));
	}
	
	@Test
	public void lasParadasEstanAMenosDe20MetrosTest()
	{
		assertEquals(true, paradaDe144.seEncuentraAXDe(2000, paradaDe55));
	}
	
	@Test
	public void siBusco55MeSalenSoloLasParadaDel55Test()
	{
		resultadosDeBusqueda = buscadorPOIS.filtrarPOIs("55");
		assertEquals(true, resultadosDeBusqueda.contains(paradaDe55));
		assertEquals(true, resultadosDeBusqueda.contains(otraParadaDe55));
		assertEquals(false, resultadosDeBusqueda.contains(paradaDe144));
	}
	
	
}
