package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.awt.Point;
import java.util.List;

public class ParadaColectivoTest 
{
	ProgramaPrincipal programaPrincipal;
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
		paradaDe55 = new ParadaDeColectivo("55");
		posicionDel55 = new Point(0,0);
		paradaDe55.setCoordenada(posicionDel55);
		paradaDe144 = new ParadaDeColectivo("144");
		posicionDel144 = new Point (15,0);
		paradaDe144.setCoordenada(posicionDel144);
		localDate = LocalDate.of(2016, 4, 18);
		fecha = localDate.atTime(12, 00);
		otraParadaDe55 = new ParadaDeColectivo("55");
		posicionDelOtro55 = new Point(0,4);
		otraParadaDe55.setCoordenada(posicionDelOtro55);
		programaPrincipal = new ProgramaPrincipal();
		programaPrincipal.addPOI(paradaDe55);
		programaPrincipal.addPOI(paradaDe144);
		programaPrincipal.addPOI(otraParadaDe55);
	}
	
	@Test
	public void ambasParadasEstanDisponibles()
	{
		assertEquals(true, paradaDe55.estaDisponible(fecha,""));
		assertEquals(true, paradaDe144.estaDisponible(fecha, ""));
	}
	
	@Test
	public void lasParadasEstanAMenosDe20Metros()
	{
		assertEquals(true, paradaDe144.seEncuentraAXDe(20, paradaDe55));
	}
	
	@Test
	public void siBusco55MeSalenSoloLasParadaDel55()
	{
		resultadosDeBusqueda = programaPrincipal.filtrarPOIs("55");
		assertEquals(true, resultadosDeBusqueda.contains(paradaDe55));
		assertEquals(true, resultadosDeBusqueda.contains(otraParadaDe55));
		assertEquals(false, resultadosDeBusqueda.contains(paradaDe144));
	}
	
	
}
