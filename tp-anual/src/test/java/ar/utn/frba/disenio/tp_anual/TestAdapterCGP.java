package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;
import static org.junit.Assert.assertEquals;

public class TestAdapterCGP {
	private CentroDTO centroDTO = new CentroDTO();
	private ServicioDTO servicioDTO = new ServicioDTO();
	private RangoServicioDTO rango = new RangoServicioDTO();
	private AdapterCGP adapterCGP = new AdapterCGP();
	private CGP cgp;
	LocalDate localDateCGP;
	LocalDateTime fechaCGP;
	@Before
	public void setUp(){
		Polygon polygon = new Polygon();
		Point punto1 = new Point(0,0);
		Point punto2 = new Point(0,100);
		Point punto3 = new Point(100,100);
		Point punto4 = new Point(100,0);
		polygon.add(punto1);
		polygon.add(punto2);
		polygon.add(punto3);
		polygon.add(punto4);
		centroDTO.setComuna(polygon);
		rango.setDia(1);
		rango.setHorarioDesde(9);
		rango.setMinutoDesde(0);
		rango.setHorarioHasta(17);
		rango.setMinutoHasta(0);
		List<RangoServicioDTO> lista = new ArrayList<RangoServicioDTO>();
		lista.add(rango);
		servicioDTO.setRango(lista);
		List<ServicioDTO> listaServicios = new ArrayList<ServicioDTO>();
		centroDTO.setListaServicios(listaServicios);
		cgp = adapterCGP.traducir(centroDTO);
		localDateCGP = LocalDate.of(2016, 4, 20);
		fechaCGP = localDateCGP.atTime(14, 00);
	}
	@Test
	public void testAdapter() {
		assertEquals(cgp.nombre, "Nuevo CGP");
		assertEquals(true, cgp.estaDisponible(fechaCGP));
	}

}
