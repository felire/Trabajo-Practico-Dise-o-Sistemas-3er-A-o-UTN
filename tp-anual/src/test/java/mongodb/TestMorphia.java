package mongodb;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;
import ar.utn.frba.disenio.tp_anual.model.CGP;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.ParadaDeColectivo;
import util.MongoDBUtil;

public class TestMorphia {

	private Datastore dataStore;
	private final static String fraseBuscadaField = "fraseBuscada";
	private final static String fraseBuscada = "Testing persistence on mongodb 12345654321";
	private final static String fraseBuscadaConPOIs = "Testing persistence on mongodb 12345654321 with POIs";
	private final static String fraseBuscadaPreviaModificacion = "Testing persistence on mongodb 12345654321 and pre modification";
	private final static String fraseBuscadaPostModificacion = "Testing persistence on mongodb 12345654321 and post modification";
	
	@Before
	public void setUp(){
		dataStore = MongoDBUtil.getDataStore();
	}
	
	@Test
	public void testPersistirBusqueda() {
		Busqueda busqueda = new Busqueda();
		busqueda.setFraseBuscada(fraseBuscada);
		dataStore.save(busqueda);
		List<Busqueda>  busquedasPersistidas = dataStore.find(Busqueda.class).asList();
		Boolean fraseEncontrada = busquedasPersistidas.stream().anyMatch(tieneFrase(fraseBuscada));		
		assertTrue(fraseEncontrada);
	}

	private Predicate<Busqueda> tieneFrase(String frase){
		return b -> b.getFraseBuscada().equals(frase);
	}
	
	@Test
	public void testPersistirBusquedaConPOIs() {
		Busqueda busqueda = new Busqueda();
		busqueda.setFraseBuscada(fraseBuscadaConPOIs);
		List<POI> pois = crearPOIs();
		busqueda.setResultados(pois);
		
		dataStore.save(busqueda);
		
		List<Busqueda>  busquedasPersistidas = dataStore.find(Busqueda.class).asList();
		Busqueda busquedaPersistidaConPOIs = busquedasPersistidas.stream().filter(tieneFrase(fraseBuscadaConPOIs))
														.findFirst()
														.get();		
		assertNotNull(busquedaPersistidaConPOIs);
		assertEquals(pois.size(), busquedaPersistidaConPOIs.getResultados().size());
		pois.stream().forEach(poi -> assertEquals(poi.getNombre(), busquedaPersistidaConPOIs.getResultados().get(pois.indexOf(poi)).getNombre()));	
	}

	@Test
	public void testPersistirYModificarBusqueda() {
		Busqueda busqueda = new Busqueda();
		busqueda.setFraseBuscada(fraseBuscadaPreviaModificacion);
		dataStore.save(busqueda);
		
		UpdateOperations<Busqueda> modificarBusqueda = dataStore.createUpdateOperations(Busqueda.class)
				.set(fraseBuscadaField, fraseBuscadaPostModificacion);
		
		Query<Busqueda> buscarConFraseQuery = dataStore.createQuery(Busqueda.class)
				.field("fraseBuscada")
				.equal(fraseBuscadaPreviaModificacion);
		
		dataStore.update(buscarConFraseQuery, modificarBusqueda);
		
		Busqueda busquedaModificada = dataStore.find(Busqueda.class).field(fraseBuscadaField).equal(fraseBuscadaPostModificacion).get();
		assertNotNull(busquedaModificada);
	}
	
	@After
	public void clean(){
		Query<Busqueda> busquedasDePruebaQuery = dataStore.createQuery(Busqueda.class);
		
		busquedasDePruebaQuery.or(
			 busquedasDePruebaQuery.criteria(fraseBuscadaField).equal(fraseBuscada),
			 busquedasDePruebaQuery.criteria(fraseBuscadaField).equal(fraseBuscadaConPOIs),
			 busquedasDePruebaQuery.criteria(fraseBuscadaField).equal(fraseBuscadaPostModificacion)
			);
		
        dataStore.delete(busquedasDePruebaQuery);
	}
	
	private List<POI> crearPOIs(){
		List<POI> pois = new ArrayList<>();
		ParadaDeColectivo paradaColectivo = new ParadaDeColectivo();
		paradaColectivo.setNombre("Test mongo parada de colectivo");
		CGP cgp = new CGP();
		cgp.setNombre("Test mongo CGP");
		pois.add(paradaColectivo);
		pois.add(cgp);
		return pois;
	}
	
}
