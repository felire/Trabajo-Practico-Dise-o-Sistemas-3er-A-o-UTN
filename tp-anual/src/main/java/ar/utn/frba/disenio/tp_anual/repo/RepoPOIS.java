package ar.utn.frba.disenio.tp_anual.repo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.utn.frba.disenio.tp_anual.model.POI;
import util.FranjaHoraria;

public class RepoPOIS extends RepoGenerico
{
	private static RepoPOIS instance;
	
	public static RepoPOIS getInstance(){
		if(instance == null){
			instance= new RepoPOIS();
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public List<POI> getListaPOIS(){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		String query = "from POI";
		Query hibernateQuery = entityManager.createQuery(query);
		return (List<POI>) hibernateQuery.getResultList();
	}
	
	//Consulta
	public List<POI> buscarPOIs(String palabraClave){
		List<POI> pois = getListaPOIS();
		return pois.stream().filter(poi -> poi.esBuscado(palabraClave)).collect(Collectors.toList());
	};
	
	public POI buscarPorID(Integer poiID){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		return entityManager.find(POI.class, poiID);
	}
	
	//Alta
	public void altaPOI(POI nuevoPOI){
		super.persistirNuevoObjeto(nuevoPOI);
	}

	//Baja
	public void bajaPOI(POI poi){
		super.borrarObjeto(poi);
	}
	
	//Modificación
	public void modificarPOI(POI modificado){
		super.actualizarObjeto(modificado);
	}

}
