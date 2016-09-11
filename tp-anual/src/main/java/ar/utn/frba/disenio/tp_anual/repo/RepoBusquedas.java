package ar.utn.frba.disenio.tp_anual.repo;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	
	@SuppressWarnings("unchecked")
	public List<Busqueda> getListaBusquedas(){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query busquedas = entityManager.createQuery("FROM Busqueda");
		return (List<Busqueda>) busquedas.getResultList();
		
	}
	
	public Busqueda buscarPorID(Integer ID){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		return entityManager.find(Busqueda.class, ID);
	}

}
