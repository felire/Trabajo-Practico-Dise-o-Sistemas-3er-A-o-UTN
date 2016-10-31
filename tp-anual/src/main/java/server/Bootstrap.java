package server;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.Usuario;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	
	public void init() {
		/*EntityManager entity = PerThreadEntityManagers.getEntityManager();
		Usuario user = new Usuario("a", "a", Rol.TERMINAL);
		EntityTransaction tx = entity.getTransaction();
		tx.begin();
		entity.persist(user);
		tx.commit();*/
		} //-->> Hay que ver como hacerlo para probar
	/*public void init(){
		withTransaction(() ->{
			Usuario user = new Usuario("felire", "a", Rol.ADMINISTRADOR);
			persist(user);
		});
		/*EntityManager entity = PerThreadEntityManagers.getEntityManager();
		entity.find(Usuario.class, "felire").getUser();*/
	//}*/
}
