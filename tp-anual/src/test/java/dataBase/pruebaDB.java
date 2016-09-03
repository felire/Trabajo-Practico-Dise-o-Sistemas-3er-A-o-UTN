package dataBase;

import static org.junit.Assert.*;

import java.time.LocalTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.Transaction;
import org.junit.Test;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import util.FranjaHoraria;

public class pruebaDB {

	@Test
	public void test() {
		EntityManager entityManager = PerThreadEntityManagers.getEntityManager();
		FranjaHoraria franjaMorada = new FranjaHoraria(LocalTime.now(), LocalTime.now());
		EntityTransaction tx = entityManager.getTransaction();
		tx.begin();
		entityManager.persist(franjaMorada);
		tx.commit();
	}

}
