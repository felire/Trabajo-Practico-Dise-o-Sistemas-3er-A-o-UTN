package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;


import ar.utn.frba.disenio.tp_anual.gestion.BuscadorCGP;
import ar.utn.frba.disenio.tp_anual.poi.CGP;

public class TestBuscadorCGP extends Init {

	CreadorDeCGP creadorDeCGP;
	CentroDTO centroDTO;
	ServicioDTO servicioDTOBicis;
	RangoServicioDTO rangoServicioBicis;
	CGP POITraducido;
	LocalDate localDate;
	LocalDateTime fecha;
	BuscadorCGP buscadorCGP = new BuscadorCGP();
	List<CentroDTO> listaCentroDTO = new ArrayList<CentroDTO>();
	List<CGP> listaCGP;
	@Before
	public void setUp(){
		centroDTO=new CentroDTO();
		//Hago el poligono de la comuna
		Polygon polygon = new Polygon();
		Point punto1 = new Point(0,0);
		Point punto2 = new Point(0,100);
		Point punto3 = new Point(100,100);
		Point punto4 = new Point(100,0);
		polygon.add(punto1);
		polygon.add(punto2);
		polygon.add(punto3);
		polygon.add(punto4);
		//Seteo Primer servicio
		servicioDTOBicis= new ServicioDTO();
		servicioDTOBicis.setNombre("Alquiler Bicicletas"); 
		
		//Su rango de atencion
		rangoServicioBicis=new RangoServicioDTO();
		rangoServicioBicis.setDia(6);
		rangoServicioBicis.setHorarioDesde(9);
		rangoServicioBicis.setMinutoDesde(0);
		rangoServicioBicis.setHorarioHasta(17);
		rangoServicioBicis.setMinutoHasta(30);
		servicioDTOBicis.agregarRango(rangoServicioBicis);
		
		//Ahora si, seteo atributos centro DTO
		centroDTO.setComuna(polygon);
		centroDTO.setDomicilio("Arana 1551");
		centroDTO.setNombreDirector("Tito Lasanta");
		centroDTO.setTelefono("45318008");
		centroDTO.setZonasIncluidas("Balvanera, Villa Ortuza");
		centroDTO.agregarServicioDTO(servicioDTOBicis);
		
		//Creo el adapter
		creadorDeCGP= new CreadorDeCGP();
		
		//Creo una fecha para probar disponibilidad
		localDate = LocalDate.of(2016, 5, 28);
		fecha = localDate.atTime(14, 30);
		listaCentroDTO.add(centroDTO);
		buscadorCGP.setCreadorDeCGP(creadorDeCGP);
	}
	
	@Test
	public void TestArmarListaCGP(){
		listaCGP = creadorDeCGP.crearCGPs(listaCentroDTO);
		assertEquals("Nuevo CGP",listaCGP.get(0).getNombre());
	}

}
