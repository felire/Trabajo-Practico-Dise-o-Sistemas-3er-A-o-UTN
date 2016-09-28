package mongodb;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import util.BigDecimalConverter;
import util.LocalDateConverter;
import util.LocalDateTimeConverter;
import util.LocalTimeConverter;
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
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;

public class TestInitMongoDB {
	
	Morphia morphia;
	MongoClient cliente;
	Datastore datastore;
	Busqueda busquedaPersistida;
	
	@Before
	public void setUp(){
		morphia = new Morphia();
		morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);
		morphia.getMapper().getConverters().addConverter(LocalDateConverter.class);
		morphia.getMapper().getConverters().addConverter(LocalDateTimeConverter.class);
		morphia.getMapper().getConverters().addConverter(LocalTimeConverter.class);
		//morphia.mapPackage("ar.utn.frba.disenio.tp_anual.model");
		cliente = new MongoClient();
		datastore = morphia.createDatastore(cliente, "tp_anual_diseno_test");
	}
	
	@Test
	public void testPersistirBusqueda(){
		ParadaDeColectivo parada = new ParadaDeColectivo("55", new Point(5,5));
		List<POI> pois = new ArrayList<>();
		
		pois.add(parada);
		
		Terminal terminal = new Terminal("Retiro", new Double(55.5));
		terminal.setID(66);
		
		Busqueda busqueda = new Busqueda(pois, "berreta", "hoola morhpia", terminal.getNombre());
	
		datastore.save(busqueda);
		busquedaPersistida = datastore.find(Busqueda.class).get();
		assertEquals("55",busquedaPersistida.getResultados().get(0).getNombre());
		assertEquals((int)5,(int) busquedaPersistida.getResultados().get(0).getCoordenada().getLatitud());
		assertEquals((int)5, (int)busquedaPersistida.getResultados().get(0).getCoordenada().getLongitud());
		assertEquals(LocalDate.now(),busquedaPersistida.getFecha());
	}
	
	@After
	public void clean(){

		cliente.dropDatabase("tp_anual_diseno_test");
	}
}
