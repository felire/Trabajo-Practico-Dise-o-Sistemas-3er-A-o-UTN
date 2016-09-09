package repo;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.SucursalBanco;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import util.Point;

public class TestRepoPOIs {

	private RepoPOIS repo;
	private POI nuevoPOI;
	
	@Before
	public void setUp(){
		repo = RepoPOIS.getInstance();
	}
	
	@Test
	public void testAgregarBanco() {
		nuevoPOI = new SucursalBanco("Santander Rio", new Point(500, 500));
		nuevoPOI.addTag("sucu");
		nuevoPOI.addTag("uno");
		repo.altaPOI(nuevoPOI);
		
		SucursalBanco sucursalBanco = (SucursalBanco) repo.buscarPorID(nuevoPOI.getID());
		assertEquals(nuevoPOI, sucursalBanco);
		assertEquals(sucursalBanco.getListaTags().contains("sucu"),true);
	}
	
	@After
	public void clean(){
		repo.bajaPOI(nuevoPOI);
		nuevoPOI = null;
	}

}
