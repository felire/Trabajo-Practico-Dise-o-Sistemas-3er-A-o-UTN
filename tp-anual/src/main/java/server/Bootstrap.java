package server;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import ar.utn.frba.disenio.tp_anual.model.CGP;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.ParadaDeColectivo;
import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.SucursalBanco;
import ar.utn.frba.disenio.tp_anual.model.Usuario;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import util.Point;
import util.Polygon;

public class Bootstrap implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps{
	
	public void init() {
		SucursalBanco nuevoPOI = new SucursalBanco("Nuevo", new Point(500, 500));
		nuevoPOI.addTag("banco");
		nuevoPOI.addTag("nuevo");
		nuevoPOI.setHorarioBancario();
		Polygon comuna = new Polygon();
		comuna.setNombre("Palermo");
		comuna.add(new Point(1,1));
		comuna.add(new Point(1,2));
		comuna.add(new Point(5,5));
		POI otroPOI = new CGP("CGP 1", null, new Point(1,1));
		ParadaDeColectivo parada = new ParadaDeColectivo("asd", new Point(4,5));
		RepoPOIS.getInstance().altaPOI(parada);
		RepoPOIS.getInstance().altaPOI(nuevoPOI);
		RepoPOIS.getInstance().altaPOI(otroPOI);
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
