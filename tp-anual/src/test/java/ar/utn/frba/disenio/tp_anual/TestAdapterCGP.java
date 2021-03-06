package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;
import util.Point;
import util.Polygon;

import ar.utn.frba.disenio.tp_anual.adapter.CreadorDeCGP;
import ar.utn.frba.disenio.tp_anual.dto.CentroDTO;
import ar.utn.frba.disenio.tp_anual.dto.RangoServicioDTO;
import ar.utn.frba.disenio.tp_anual.dto.ServicioDTO;
import ar.utn.frba.disenio.tp_anual.model.CGP;
import ar.utn.frba.disenio.tp_anual.model.Servicio;

public class TestAdapterCGP extends Init {
	
	CreadorDeCGP creadorDeCGP;
	CentroDTO centroDTO;
	ServicioDTO servicioDTOBicis;
	RangoServicioDTO rangoServicioBicis;
	CGP POITraducido;
	LocalDate localDate;
	LocalDateTime fecha;
	
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
		
	}
	@Test
	public void creaCGPconComunaCorrecta(){
		POITraducido=creadorDeCGP.traducir(centroDTO);
		assertEquals(centroDTO.getComuna(),POITraducido.getComuna());
	}
	@Test
	public void creaCGPconServicio(){
		POITraducido=creadorDeCGP.traducir(centroDTO);
		Predicate<Servicio> tieneNombreBici =  serv -> serv.getNombre()=="Alquiler Bicicletas";
		assertTrue(POITraducido.servicios.stream().anyMatch(tieneNombreBici));
		
	}
	@Test
	public void creaDisponibilidadHoraria(){
		POITraducido=creadorDeCGP.traducir(centroDTO);
		Predicate<Servicio> tieneNombreBici =  serv -> serv.getNombre()=="Alquiler Bicicletas";
		Servicio bicicletas =  POITraducido.servicios.stream().filter(tieneNombreBici).findFirst().get();
		assertTrue(bicicletas.estaDisponible(fecha));
		
	}
		
}

