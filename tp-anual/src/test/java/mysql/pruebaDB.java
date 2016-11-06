package mysql;

import static org.junit.Assert.*;

import java.time.LocalTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Transaction;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import util.FranjaHoraria;
import util.Point;
import util.Polygon;

public class pruebaDB {

	@Test
	public void test() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		//FranjaHoraria franjaMorada = new FranjaHoraria(LocalTime.now(), LocalTime.now());
		
		Polygon comuna2 = new Polygon();
		comuna2.setNombre("Caballito");
		comuna2.add(new Point(12,13));
		comuna2.add(new Point(20,30));
		
		Terminal terminal3 = new Terminal("Caballero", 15);
		terminal3.setComuna(comuna2);
    	RepoTerminales.getInstance().persistirNuevoObjeto(terminal3);
		RepoTerminales.getInstance().borrarTerminal(terminal3);
		/*EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		//entityManager.persist(franjaMorada);
		tx.commit();*/
	}

}
