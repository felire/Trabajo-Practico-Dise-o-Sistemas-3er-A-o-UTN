package repo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.SucursalBanco;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorPOIs;
import util.Point;

public class TestTerminales {
	
	private RepoTerminales repo;
	private Terminal terminal1;
	private Terminal terminal2;
	private BuscadorPOIs buscador;
	
	@Before
	public void setUp(){
		terminal1 = new Terminal(buscador,"Terminal 1", 5000);
		terminal2 = new Terminal(buscador,"Terminal 2", 5000);
		repo = RepoTerminales.getInstance();
		buscador = BuscadorPOIs.getInstance();
	}
	
	@Test
	public void testAgregarTerminales(){
		
		repo.registrarTerminal(terminal1);
		repo.registrarTerminal(terminal2);
		
		Terminal primera = (Terminal) repo.buscarPorID(terminal1.getID());
		Terminal segunda = (Terminal) repo.buscarPorID(terminal2.getID());
		
		assertEquals(terminal1, primera);
		assertEquals(terminal2, segunda);
	}
	
	@Test
	public void testModificarTerminales(){
		
		terminal1.setNombre("Hola");
		
		//Deberia mandarse con actualizarTerminal pero me rompe
		repo.registrarTerminal(terminal1);
		
		Terminal modificada = (Terminal) repo.buscarPorID(terminal1.getID());
		assertEquals(modificada.getNombre(),"Hola");
		
	}
	
	
	@After
	public void clean(){
		repo.borrarTerminal(terminal1);
		repo.borrarTerminal(terminal2);
		terminal1 = null;
		terminal2 = null;
	}

}
