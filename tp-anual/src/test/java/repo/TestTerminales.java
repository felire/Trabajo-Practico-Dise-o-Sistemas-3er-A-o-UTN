package repo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import util.Point;
import util.Polygon;

public class TestTerminales {
	
	private RepoTerminales repo;
	private Terminal terminal1;
	private Terminal terminal2;
	private ObserverTerminal observer;
	private ObserverTerminal observer2;
	private Terminal terminal3;
	private Terminal terminal4;
	private Polygon comuna2;
	private Polygon comuna3;
	
	@Before
	public void setUp(){
		terminal1 = new Terminal("Terminal 1", 5000);
		terminal2 = new Terminal("Terminal 2", 5000);
		terminal1.addObserver(observer);
		repo = RepoTerminales.getInstance();
		repo.registrarTerminal(terminal1);
		comuna2 = new Polygon();
		comuna2.setNombre("Caballo");
		comuna2.add(new Point(12,13));
		comuna2.add(new Point(20,30));
		comuna3 = new Polygon();
		comuna3.setNombre("Caballo");
		comuna3.add(new Point(12,13));
		comuna3.add(new Point(20,30));
		
		terminal3 = new Terminal("asdasd", 11);
		terminal3.setComuna(comuna2);
		terminal4 = new Terminal("asdd", 11);
		terminal4.setComuna(comuna3);
		RepoTerminales.getInstance().registrarTerminal(terminal3);
		RepoTerminales.getInstance().registrarTerminal(terminal4);
	}
	
	@Test
	public void testVariasTerm(){
		repo.getListaTerminales();
	}
	
	@Test
	public void testAgregarTerminales(){
		repo.registrarTerminal(terminal2);
		Terminal primera = (Terminal) repo.buscarPorID(terminal1.getID());
		Terminal segunda = (Terminal) repo.buscarPorID(terminal2.getID());
		assertEquals(terminal1, primera);
		assertEquals(terminal2, segunda);
	}
	
	@Test
	public void testModificarTerminales(){		
		terminal1.setNombre("Hola");
		repo.modificarTerminal(terminal1);
		Terminal modificada = (Terminal) repo.buscarPorID(terminal1.getID());
		assertEquals(modificada.getNombre(),"Hola");
	}
	
	// Tests de Observers //
	
	@Test
	public void testRecuperarObserver(){
		Terminal conObserver = (Terminal) repo.buscarPorID(terminal1.getID());
		assertEquals(conObserver.getListaObservers().get(0),observer);
	}
	
	@Test
	public void testBajaObserver(){
		terminal1.deleteObserver(observer);
		repo.modificarTerminal(terminal1);
		Terminal sinObserver = (Terminal) repo.buscarPorID(terminal1.getID());
		assertEquals(sinObserver.getListaObservers().isEmpty(),true);
	}
	
	@Test
	public void testAltaObserver(){
		terminal1.deleteObserver(observer);
		terminal1.addObserver(observer2);
		repo.modificarTerminal(terminal1);
		Terminal con2Observers = (Terminal) repo.buscarPorID(terminal1.getID());
		assertEquals(con2Observers.getListaObservers().get(0),observer2);
	}
	
	@Test
	public void testearBuscarPorNombreComuna(){
		assertTrue(RepoTerminales.getInstance().getComuna("Caballo") == comuna2);
	}
	
	
	
	@After
	public void clean(){
		repo.borrarTerminal(terminal1);
		repo.borrarTerminal(terminal2);
		repo.borrarTerminal(terminal3);
		repo.borrarTerminal(terminal4);
		observer= null;
		terminal1 = null;
		terminal2 = null;
	}

}
