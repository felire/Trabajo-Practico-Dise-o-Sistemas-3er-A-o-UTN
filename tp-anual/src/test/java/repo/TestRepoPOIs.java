package repo;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	private List<POI> nuevosPOIs;
	
	@Before
	public void setUp(){
		nuevoPOI = null;
		nuevosPOIs = new ArrayList<POI>();
		repo = RepoPOIS.getInstance();
	}
	
	@Test
	public void testAgregarBanco() {
		nuevoPOI = new SucursalBanco("Santander Rio", new Point(500, 500));
		repo.altaPOI(nuevoPOI);
		
		SucursalBanco sucursalBanco = (SucursalBanco) repo.buscarPorID(nuevoPOI.getID());
		assertEquals(nuevoPOI, sucursalBanco);
	}
	
	@Test
	public void testModificarBanco() {
		//Crearlo y persistirlo
		nuevoPOI = new SucursalBanco("Santander Rio", new Point(500, 500));
		repo.altaPOI(nuevoPOI);
		
		//Ir a buscarlo
		SucursalBanco sucursalBanco = (SucursalBanco) repo.buscarPorID(nuevoPOI.getID());
		
		//Modificar
		sucursalBanco.setNombre("Santander River");
		repo.modificarPOI(sucursalBanco);
		
		//Ir a buscar el modificado
		SucursalBanco bancoModificado = (SucursalBanco) repo.buscarPorID(sucursalBanco.getID());
		
		assertEquals(bancoModificado.getNombre(), "Santander River");
	}
	
	@Test
	public void buscarPorPalabraClave() {
		SucursalBanco sucursalBanco = new SucursalBanco("Santander Rio", new Point(500, 500));
		repo.altaPOI(sucursalBanco);
		
		List<POI> poisEncontrados = repo.buscarPOIs("Santander");
	
		assertEquals(sucursalBanco, poisEncontrados.get(0));
	}
	
	@After
	public void clean(){
		try{
		repo.bajaPOI(nuevoPOI);
		for(POI poi: nuevosPOIs){
			repo.bajaPOI(poi);
		}
		} catch(IllegalArgumentException e){
			//Se intento borrar entidad sin id
		}
	}

}
