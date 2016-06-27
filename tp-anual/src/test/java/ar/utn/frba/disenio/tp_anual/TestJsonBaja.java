package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ar.utn.frba.disenio.tp_anual.adapter.JsonTraduccion;
import ar.utn.frba.disenio.tp_anual.json.JsonBaja;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.schedule.process.ProcesoBajaPOIs;

public class TestJsonBaja{

	private RepoPOIS repo;
	private JsonTraduccion jsonTraductor = new JsonTraduccion();
	private List<JsonBaja> listaABorrar;
	private String jsonPois = "["
	                           + "{ \"id\": 1,"
	                           + "\"fecha\": \"2016-07-23-14-30\""
	                           + "},"
	                           + "{ \"id\": 2,"
	                           + "\"fecha\": \"2016-07-23-14-31\""
	                           + "}"
	                           +"]";
	private ProcesoBajaPOIs proceso;
	
	@Before
	public void setUp(){
		repo = RepoPOIS.getInstance();
		proceso = new ProcesoBajaPOIs(repo,jsonTraductor,jsonPois);
		listaABorrar = jsonTraductor.traductorBajaPOIs(proceso);
	}
	
	
	@Test
	public void testTraduccion() {
		assertEquals((Object)1, listaABorrar.get(0).getId());
		assertEquals("2016-07-23-14-30", listaABorrar.get(0).getFecha());
		assertEquals((Object)2, listaABorrar.get(1).getId());
		assertEquals("2016-07-23-14-31", listaABorrar.get(1).getFecha());
	}

}
