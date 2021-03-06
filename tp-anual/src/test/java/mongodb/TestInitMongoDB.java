package mongodb;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import util.BigDecimalConverter;
import util.LocalDateConverter;
import util.LocalDateTimeConverter;
import util.LocalTimeConverterMorphia;
import util.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import com.mongodb.MongoClient;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.ParadaDeColectivo;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoBusquedas;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import junit.framework.Assert;

public class TestInitMongoDB {
	
//	Morphia morphia;
//	MongoClient cliente;
//	Datastore datastore;
//	Busqueda busquedaPersistida;
	
	@Before
	public void setUp(){
		/*morphia = new Morphia();
		morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);
		morphia.getMapper().getConverters().addConverter(LocalDateConverter.class);
		morphia.getMapper().getConverters().addConverter(LocalDateTimeConverter.class);
		morphia.getMapper().getConverters().addConverter(LocalTimeConverterMorphia.class);
		//morphia.mapPackage("ar.utn.frba.disenio.tp_anual.model");
		cliente = new MongoClient();
		datastore = morphia.createDatastore(cliente, "tp_anual_diseno_test");*/
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testPersistirBusqueda(){
		ParadaDeColectivo parada = new ParadaDeColectivo("55", new Point(5,5));
		List<POI> pois = new ArrayList<>();
		
		pois.add(parada);
		
		Terminal terminal = new Terminal("Retiro", new Double(55.5));
		terminal.setID(66);
		
		Busqueda busqueda = new Busqueda(pois, "berreta", "hoola morhpia", terminal.getNombre());
		Busqueda busqueda2 = new Busqueda(pois, "berreta", "hoola morhpia", terminal.getNombre());
		/*RepoBusquedas.getInstance().persistirBusqueda(busqueda);
		RepoBusquedas.getInstance().persistirBusqueda(busqueda2);*/
		List<Busqueda> busquedas;
		List<Busqueda> busquedas2;
		busquedas2 = RepoBusquedas.getInstance().todasLasBusquedas();
		busquedas = RepoBusquedas.getInstance().filtrar(LocalDate.of(2000,9,12), LocalDate.of(2056,9,12), 10000, "fhfaohasdohdfsa");
		busquedas2.stream().forEach(bus -> System.out.println(RepoBusquedas.getInstance().buscarPorID(bus.getID()).getID().toString()));
		
		//assertEquals(2, busquedas2.size());
		/*Assert.assertTrue(busquedas.contains(busqueda));
		assertEquals("55",busquedaPersistida.getResultados().get(0).getNombre());
		assertEquals((int)5,(int) busquedaPersistida.getResultados().get(0).getCoordenada().getLatitud());
		assertEquals((int)5, (int)busquedaPersistida.getResultados().get(0).getCoordenada().getLongitud());
		assertEquals(LocalDate.now(),busquedaPersistida.getFecha());*/
	}
	
	/*@After
	public void clean(){

		cliente.dropDatabase("tp_anual_diseno_test");
	}*/
}
