package ar.utn.frba.disenio.tp_anual.repo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;

public class RepoBusquedas extends RepoGenerico{
	
	private static RepoBusquedas instance;
	
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
	
	public void borrarTodasLasBusquedas(){
		getListaBusquedas().forEach(busqueda -> borrarBusqueda(busqueda));
	}
	
	public List<Busqueda> filtrar(LocalDate desde, LocalDate hasta, Integer cantidad, String terminal){
		List<Busqueda> busquedas=this.getListaBusquedas();
		return busquedas.stream().filter(bus->bus.getFecha().isAfter(desde) 
				&& bus.getFecha().isBefore(hasta) 
				&& bus.getTerminal().equals(terminal)
				&& bus.getResultados().size() == cantidad).collect(Collectors.toList());
	}
	
	@SuppressWarnings("unchecked")
	public List<Busqueda> getListaBusquedas(){
		org.mongodb.morphia.query.Query<Busqueda> query = datastore.createQuery(Busqueda.class);
		List<Busqueda> employees = query.asList();
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query busquedas = entityManager.createQuery("FROM Busqueda");
		return (List<Busqueda>) busquedas.getResultList();
		
	}
	
	public Busqueda buscarPorID(Integer ID){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		return entityManager.find(Busqueda.class, ID);
	}

}
