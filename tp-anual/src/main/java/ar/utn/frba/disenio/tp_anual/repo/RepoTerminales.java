package ar.utn.frba.disenio.tp_anual.repo;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import util.Polygon;

public class RepoTerminales extends RepoGenerico{
	
	private static RepoTerminales instance;
	
	public static RepoTerminales getInstance(){
		if(instance == null){
			instance= new RepoTerminales();
		}
		return instance;
	}
		
	public Terminal buscarPorID(long terminalID){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		return entityManager.find(Terminal.class, terminalID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Terminal> getListaTerminales(){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query terminales = entityManager.createQuery("FROM Terminal");
		return (List<Terminal>) terminales.getResultList();	
	}
	
	@SuppressWarnings("unchecked")
	public List<Terminal> buscarPorComuna(String comuna){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query terminales = entityManager.createQuery("SELECT Terminal.id, Terminal.nombre, Terminal.comuna_id FROM Terminal, Polygon WHERE Terminal.comuna_id = Polygon.id AND Polygon.nombre = :nombre");
		terminales.setParameter("nombre", comuna);
		return (List<Terminal>) terminales.getResultList();	
	}
	
	public List<Polygon> getComunas(){
		List<Polygon> lista = new ArrayList<Polygon>();
		this.getListaTerminales().stream().forEach(terminal ->lista.add(terminal.getComuna()));
		return lista;
	}
	
/*	public Set<String> getNombreComunas(){
		Set<String> set = new HashSet<String>();
		this.getComunas().stream().forEach(comuna -> {
			if (comuna.getNombre() == null) set.add(" ");
			else set.add(comuna.getNombre());
		});
		return set;
	}*/
	
	public void registrarTerminal(Terminal terminal){
		super.persistirNuevoObjeto(terminal);
	}
	
	public void borrarTerminal(Terminal terminal){
		super.borrarObjeto(terminal);
	}
	
	public void modificarTerminal(Terminal terminal){
		super.actualizarObjeto(terminal);
	}
	
}
