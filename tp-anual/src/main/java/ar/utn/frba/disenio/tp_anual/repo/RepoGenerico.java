package ar.utn.frba.disenio.tp_anual.repo;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.mongodb.morphia.Datastore;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;


public class RepoGenerico {
	
	public void persistirNuevoObjeto(Object objeto){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(objeto);
		tx.commit();
	}
	
	public void actualizarObjeto(Object objeto){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.merge(objeto);
		tx.commit();
	}
	
	public void borrarObjeto(Object objeto){
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		Query desactivarConstraint = entityManager.createNativeQuery(" SET FOREIGN_KEY_CHECKS=0;");
		Query activarConstraint = entityManager.createNativeQuery("SET FOREIGN_KEY_CHECKS=1;");
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		desactivarConstraint.executeUpdate();
		entityManager.remove(objeto);
		tx.commit();
		tx.begin();
		activarConstraint.executeUpdate();
		tx.commit();		
		}
	
}
