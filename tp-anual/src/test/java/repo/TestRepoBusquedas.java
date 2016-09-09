package repo;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.SucursalBanco;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoBusquedas;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import util.Point;

public class TestRepoBusquedas {
	
	private RepoPOIS repoPOIS;
	private RepoBusquedas repoBusquedas;
	private Terminal terminal1;
	private SucursalBanco sucursalBanco1;
	private SucursalBanco sucursalBanco2;
	private Busqueda busqueda;
	
	@Before
	public void setUp(){
		terminal1 = new Terminal("Terminal 1", 5000);
		
		sucursalBanco1 = new SucursalBanco("Santander Rio", new Point(500, 500));
		sucursalBanco2 = new SucursalBanco("Nacion", new Point(300,300));
		sucursalBanco1.addTag("sucu");
		sucursalBanco1.addTag("uno");
		sucursalBanco2.addTag("sucu");
		sucursalBanco2.addTag("dos");
		
		repoBusquedas = RepoBusquedas.getInstance();
		repoPOIS = RepoPOIS.getInstance();
		repoPOIS.altaPOI(sucursalBanco1);
		repoPOIS.altaPOI(sucursalBanco2);
		
		List<POI> buscados = new ArrayList<POI>();
		buscados.add(sucursalBanco1);
		busqueda = new Busqueda(buscados,"1","","terminal");
	}
	
	// No anda la persistencia de busqueda, me parece que es un tema del mapeo //
	
	@Test
	public void TestGuardarBusquedas(){
		repoBusquedas.persistirBusqueda(busqueda);
		List<Busqueda> listaDeBusquedas = repoBusquedas.getListaBusquedas();
		assertEquals(listaDeBusquedas.size(),1);
	}
	
	@After
	public void clean(){
		repoBusquedas.borrarBusqueda(busqueda);
		repoPOIS.bajaPOI(sucursalBanco1);
		repoPOIS.bajaPOI(sucursalBanco2);
		busqueda = null;
		terminal1 = null;
		sucursalBanco1 = null;
		sucursalBanco2 = null;
	}
	
	

}
