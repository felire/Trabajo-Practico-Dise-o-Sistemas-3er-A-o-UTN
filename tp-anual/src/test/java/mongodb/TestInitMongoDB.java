package mongodb;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import util.Point;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.mongodb.MongoClient;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.ParadaDeColectivo;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;

public class TestInitMongoDB {
	
	Morphia morphia;
	Datastore datastore;
	Busqueda busquedaPersistida;
	
	@Before
	public void setUp(){
		morphia = new Morphia();
		morphia.mapPackage("ar.utn.frba.disenio.tp_anual.model");
		datastore = morphia.createDatastore(new MongoClient(), "tp_anual_diseno_test");
	}
	
	@Test
	public void testPersistirBusqueda(){
		ParadaDeColectivo parada = new ParadaDeColectivo("55", new Point(5,5));
		List<POI> pois = new ArrayList<>();
		pois.add(parada);
		
		Terminal terminal = new Terminal("Retiro", new Double(55.5));
		terminal.setID(66);
		
		Busqueda busqueda = new Busqueda(null, "hola morphia", "hoola morhpia", terminal);
	
		datastore.save(busqueda);
		
		busquedaPersistida = datastore.createQuery(Busqueda.class).get();
		System.out.println(busquedaPersistida);
	}
	
	@After
	public void clean(){
		datastore.delete(busquedaPersistida);
	}

}
