package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestJsonBaja {

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
	
	@Before
	public void setUp(){
		listaABorrar = jsonTraductor.traductorBajaPOIs(jsonPois);
	}
	
	
	@Test
	public void testTraduccion() {
		assertEquals((Object)1, listaABorrar.get(0).getId());
		assertEquals("2016-07-23-14-30", listaABorrar.get(0).getFecha());
		assertEquals((Object)2, listaABorrar.get(1).getId());
		assertEquals("2016-07-23-14-31", listaABorrar.get(1).getFecha());
	}

}
