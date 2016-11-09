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

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import com.mongodb.MongoClient;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.ParadaDeColectivo;
import server.Bootstrap;
import util.BigDecimalConverter;
import util.LocalDateConverter;
import util.LocalDateTimeConverter;
import util.LocalTimeConverterMorphia;
import util.Point;

public class RepoBusquedas{
	
	private static RepoBusquedas instance;
	Morphia morphia;
	MongoClient cliente;
	Datastore dataStore;
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
	
	private RepoBusquedas(){
		morphia = new Morphia();
		morphia.getMapper().getConverters().addConverter(BigDecimalConverter.class);
		morphia.getMapper().getConverters().addConverter(LocalDateConverter.class);
		morphia.getMapper().getConverters().addConverter(LocalDateTimeConverter.class);
		morphia.getMapper().getConverters().addConverter(LocalTimeConverterMorphia.class);
		//morphia.mapPackage("ar.utn.frba.disenio.tp_anual.model");
		cliente = new MongoClient();
		dataStore = morphia.createDatastore(cliente, "tp_anual_busquedas");
	}
	
	public static RepoBusquedas getInstance(){
		if(instance == null){
			instance= new RepoBusquedas();
		}
		return instance;
	}
	
	public void persistirBusqueda(Busqueda busqueda){
		dataStore.save(busqueda);
	}
	
	/*public void borrarBusqueda(Busqueda busqueda){
		super.borrarObjeto(busqueda);
	}*/
	/*public List<Busqueda> traerBusquedas(){
		return datastore.
	}*/
	
	public List<Busqueda> filtrarTrucho(LocalDate desde, LocalDate hasta, Integer cantidad, String terminal){
		Busqueda busqueda1= new Busqueda();
		Busqueda busqueda2= new Busqueda();
		busqueda1.setId(new ObjectId("507f1f77bcf86cd799439011"));
		busqueda1.setTerminal("holaa");
		busqueda1.setDemora(10.1);
		busqueda1.setFecha(LocalDate.now());
		busqueda1.setFraseBuscada("wachooo");
		busqueda2.setId(new ObjectId("507f1f77bcf86cd799435011"));
		busqueda2.setTerminal("chau");
		busqueda2.setDemora(11.21);
		busqueda2.setFecha(LocalDate.now());
		busqueda2.setFraseBuscada("genioo");
		List<Busqueda> retorno= new ArrayList<Busqueda>();
		retorno.add(busqueda2);
		retorno.add(busqueda1);
		return retorno;
	}
	
	public Busqueda buscarPorIDTrucho(ObjectId ID){
		
		ParadaDeColectivo parada = new ParadaDeColectivo("asd", new Point(4,5));
		parada.setDireccion("Berraco 453");
		ParadaDeColectivo parada2 = new ParadaDeColectivo("parada", new Point(4,5));
		parada.setDireccion("homero 181");
		List<POI> pois= new ArrayList<POI>();
		pois.add(parada);
		pois.add(parada2);
		
		Busqueda busqueda1= new Busqueda(pois,"holaa","daa","fsd");
		busqueda1.setId(new ObjectId());
		busqueda1.setDemora(10.1);
		busqueda1.setFecha(LocalDate.now());
		
		return busqueda1;
	}
	
	/*public void borrarTodasLasBusquedas(){
		getListaBusquedas().forEach(busqueda -> borrarBusqueda(busqueda));
	}*/
	
	public List<Busqueda> filtrar(LocalDate desde, LocalDate hasta, Integer cantidad, String terminal){
		org.mongodb.morphia.query.Query<Busqueda> busquedas = this.getListaBusquedas();
		List<Busqueda> busquedass = busquedas.
				field("terminal").contains(terminal).
				field("fecha").greaterThan(desde).
				field("fecha").lessThan(hasta).asList();
		if(cantidad<busquedass.size()){
			return busquedass.subList(0, cantidad);
		}
		else{
			return busquedass;
		}
	}
	
	@SuppressWarnings("unchecked")
	public org.mongodb.morphia.query.Query<Busqueda> getListaBusquedas(){
		org.mongodb.morphia.query.Query<Busqueda> query = dataStore.createQuery(Busqueda.class);
		return query;
	}
	
	public List<Busqueda> todasLasBusquedas(){
		return dataStore.createQuery(Busqueda.class).asList();
	}
	public Busqueda buscarPorID(ObjectId ID){
		return dataStore.createQuery(Busqueda.class).filter("_id", ID).get();
	}
	
	/*public static Datastore getDataStore()
	{
		RepoBusquedas.initMorphia();
		return dataStore;
	}*/

}
