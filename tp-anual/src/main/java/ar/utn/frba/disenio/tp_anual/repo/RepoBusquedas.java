package ar.utn.frba.disenio.tp_anual.repo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import com.mongodb.MongoClient;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.ParadaDeColectivo;
import server.Bootstrap;
import util.Point;

public class RepoBusquedas extends RepoGenerico{
	
	private static RepoBusquedas instance;
//	public static Datastore dataStore;
//	
//	public static void initMorphia(){
//		Morphia morphia = new Morphia();
//
//		// tell Morphia where to find your classes
//		// can be called multiple times with different packages or classes
//		morphia.mapPackage("ar.utn.frba.disenio.tp_anual.model");
//
//		// create the Datastore connecting to the default port on the local host
//		dataStore = morphia.createDatastore(new MongoClient(), "morphia_example");
//		dataStore.ensureIndexes();
//		
//		Busqueda busqueda1 = new Busqueda();
//		busqueda1.setFraseBuscada("azucar");
//		busqueda1.setTerminal("Once");
//		Busqueda busqueda2 = new Busqueda();
//		busqueda2.setFraseBuscada("sal");
//		busqueda2.setTerminal("Retiro");
//		
//		dataStore.save(busqueda1);
//		dataStore.save(busqueda2);
//	}
	
	public static RepoBusquedas getInstance(){
		if(instance == null){
			instance= new RepoBusquedas();
		}
		return instance;
	}
	
	public void persistirBusqueda(Busqueda busqueda){
		super.persistirNuevoObjeto(busqueda);
	}
	
	public void borrarBusqueda(Busqueda busqueda){
		super.borrarObjeto(busqueda);
	}
	
	public List<Busqueda> filtrarTrucho(LocalDate desde, LocalDate hasta, Integer cantidad, String terminal){
		Busqueda busqueda1= new Busqueda();
		Busqueda busqueda2= new Busqueda();
		busqueda1.setId(1);
		busqueda1.setTerminal("holaa");
		busqueda1.setDemora(10.1);
		busqueda1.setFecha(LocalDate.now());
		busqueda1.setFraseBuscada("wachooo");
		busqueda2.setId(2);
		busqueda2.setTerminal("chau");
		busqueda2.setDemora(11.21);
		busqueda2.setFecha(LocalDate.now());
		busqueda2.setFraseBuscada("genioo");
		List<Busqueda> retorno= new ArrayList<Busqueda>();
		retorno.add(busqueda2);
		retorno.add(busqueda1);
		return retorno;
	}
	
	public Busqueda buscarPorIDTrucho(Integer ID){
		
		ParadaDeColectivo parada = new ParadaDeColectivo("asd", new Point(4,5));
		parada.setDireccion("Berraco 453");
		ParadaDeColectivo parada2 = new ParadaDeColectivo("parada", new Point(4,5));
		parada.setDireccion("homero 181");
		List<POI> pois= new ArrayList<POI>();
		pois.add(parada);
		pois.add(parada2);
		
		Busqueda busqueda1= new Busqueda(pois,"holaa","daa","fsd");
		busqueda1.setId(1);
		busqueda1.setDemora(10.1);
		busqueda1.setFecha(LocalDate.now());
		
		return busqueda1;
	}
	
//	public void borrarTodasLasBusquedas(){
//		getListaBusquedas().forEach(busqueda -> borrarBusqueda(busqueda));
//	}
//	
//	public List<Busqueda> filtrar(LocalDate desde, LocalDate hasta, Integer cantidad, String terminal){
//		List<Busqueda> busquedas=this.getListaBusquedas();
//		return busquedas.stream().filter(bus->bus.getFecha().isAfter(desde) 
//				&& bus.getFecha().isBefore(hasta) 
//				&& bus.getTerminal().equals(terminal)
//				&& bus.getResultados().size() == cantidad).collect(Collectors.toList());
//	}
	
//	@SuppressWarnings("unchecked")
//	public List<Busqueda> getListaBusquedas(){
//		org.mongodb.morphia.query.Query<Busqueda> query = dataStore.createQuery(Busqueda.class);
//		return query.asList();
//	}
	
	public Busqueda buscarPorID(Integer ID){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		return entityManager.find(Busqueda.class, ID);
	}

}
