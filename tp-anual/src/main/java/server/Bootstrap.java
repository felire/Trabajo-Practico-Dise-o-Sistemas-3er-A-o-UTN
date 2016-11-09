package server;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import com.mongodb.MongoClient;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;
import ar.utn.frba.disenio.tp_anual.model.CGP;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.ParadaDeColectivo;
import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.SucursalBanco;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.model.Usuario;
import ar.utn.frba.disenio.tp_anual.observer.ObserverMail;
import ar.utn.frba.disenio.tp_anual.repo.RepoBusquedas;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import util.Point;
import util.Polygon;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	
	public void init() {
		SucursalBanco nuevoPOI = new SucursalBanco("Nuevo", new Point(500, 500));
		nuevoPOI.addTag("banco");
		nuevoPOI.addTag("nuevo");
		nuevoPOI.setDireccion("Thames 1221");
		nuevoPOI.setHorarioBancario();
		Polygon comuna = new Polygon();
		comuna.setNombre("Palermo");
		comuna.add(new Point(1,1));
		comuna.add(new Point(1,2));
		comuna.add(new Point(5,5));
		POI otroPOI = new CGP("CGP 1", null, new Point(1,1));
		otroPOI.setDireccion("Merlusa 225");
		ParadaDeColectivo parada = new ParadaDeColectivo("asd", new Point(4,5));
		parada.setDireccion("Berraco 453");
//		RepoPOIS.getInstance().altaPOI(parada);
//		RepoPOIS.getInstance().altaPOI(nuevoPOI);
//		RepoPOIS.getInstance().altaPOI(otroPOI);
		EntityManager entity = PerThreadEntityManagers.getEntityManager();
		Usuario user = new Usuario("felire", "a", Rol.ADMINISTRADOR);
		Usuario user2 = new Usuario("a", "a", Rol.TERMINAL);
		
		Polygon comuna2 = new Polygon();
		comuna2.setNombre("Caballito");
		comuna2.add(new Point(12,13));
		comuna2.add(new Point(20,30));
		
		Terminal terminal3 = new Terminal("conAccion", 15);
		terminal3.setComuna(comuna2);
		terminal3.addObserver(new ObserverMail());
//		RepoTerminales.getInstance().persistirNuevoObjeto(terminal3);
		
		Terminal terminal1 = new Terminal("Once", 10);
		Terminal terminal2 = new Terminal("Retiro", 10);
		terminal1.setComuna(comuna);
		terminal2.setComuna(comuna);
//		RepoTerminales.getInstance().persistirNuevoObjeto(terminal1);
//		RepoTerminales.getInstance().persistirNuevoObjeto(terminal2);
//		EntityTransaction tx = entity.getTransaction();

//		tx.begin();
//		
//		entity.persist(user);
//		entity.persist(user2);
//		tx.commit();
		
//		RepoBusquedas.initMorphia();
		
		} //-->> Hay que ver como hacerlo para probar
//	public void init(){
//		withTransaction(() ->{
//			Usuario user = new Usuario("felire", "a", Rol.ADMINISTRADOR);
//			persist(user);
//		});
//		EntityManager entity = PerThreadEntityManagers.getEntityManager();
//		entity.find(Usuario.class, "felire").getUser();
//	}
}
