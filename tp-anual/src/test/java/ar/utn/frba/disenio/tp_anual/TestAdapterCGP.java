package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.Before;
import org.junit.Test;
import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

import com.sun.javafx.collections.MappingChange.Map;

public class TestAdapterCGP {
	
	AdapterCGP adapterCGP;
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
		servicioDTOBicis.nombre ="Alquiler Bicicletas";
		servicioDTOBicis.rango= new ArrayList<RangoServicioDTO>();
		//Su rango de atencion
		rangoServicioBicis=new RangoServicioDTO();
		rangoServicioBicis.dia=6;
		rangoServicioBicis.horarioDesde=9;
		rangoServicioBicis.minutoDesde=0;
		rangoServicioBicis.horarioHasta=19;
		rangoServicioBicis.minutoHasta=30;
		servicioDTOBicis.rango.add(rangoServicioBicis);
		
		//Ahora si, seteo atributos centro DTO
		centroDTO.comuna=polygon;
		centroDTO.domicilio="Arana 1551";
		centroDTO.nombreDirector="Tito Lasanta";
		centroDTO.telefono="45318008";
		centroDTO.zonasIncluidas="Balvanera, Villa Ortuza";
		centroDTO.listaServicios=new ArrayList<ServicioDTO>();
		centroDTO.listaServicios.add(servicioDTOBicis);
		
		//Creo el adapter
		adapterCGP= new AdapterCGP();
		
		//Creo una fecha para probar disponibilidad
		localDate = LocalDate.of(2016, 5, 29);
		fecha = localDate.atTime(14, 30);
		
	}
	@Test
	public void creaCGPconComunaCorrecta(){
		POITraducido=adapterCGP.traducir(centroDTO);
		assertEquals(centroDTO.comuna,POITraducido.comuna);
		//assertEquals(true,POITraducido.servicios.stream().findAny(tieneNombreBici));
	}
	@Test
	public void creaCGPconServicio(){
		POITraducido=adapterCGP.traducir(centroDTO);
		Predicate<Servicio> tieneNombreBici =  serv -> serv.nombre=="Alquiler Bicicletas";
		assertEquals(true,POITraducido.servicios.stream().anyMatch(tieneNombreBici));
	}
	/*@Test
	public void creaDisponibilidadHoraria(){
		POITraducido=adapterCGP.traducir(centroDTO);
		Predicate<Servicio> tieneNombreBici =  serv -> serv.nombre=="Alquiler Bicicletas";
		Servicio bicicletas =  POITraducido.servicios.stream().filter(tieneNombreBici).findFirst().get();
		assertEquals(1,bicicletas.diasDisponibles.size());
		
	}*/
	
}

