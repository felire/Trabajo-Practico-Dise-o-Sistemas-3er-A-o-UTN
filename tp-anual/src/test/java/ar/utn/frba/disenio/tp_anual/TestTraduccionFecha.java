package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.utn.frba.disenio.tp_anual.json.JsonBaja;
import ar.utn.frba.disenio.tp_anual.json.JsonBajaFecha;

public class TestTraduccionFecha {

	private JsonBaja poi;
	private JsonBajaFecha poiConFecha;
	
	@Before
	public void setUp(){
		poi = new JsonBaja();
		poi.setId(1);
		poi.setFecha("2016-07-23-14-30");		
		poiConFecha = new JsonBajaFecha(poi);		
	}
	
	@Test
	public void testFechaCorrecta() {
		assertEquals((Object)1,poiConFecha.getId());
		assertEquals((Object)2016,poiConFecha.getFecha().getYear());
		assertEquals((Object)07,poiConFecha.getFecha().getMonthValue());
		assertEquals((Object)23,poiConFecha.getFecha().getDayOfMonth());
		assertEquals((Object)14,poiConFecha.getFecha().getHour());
		assertEquals((Object)30,poiConFecha.getFecha().getMinute());
	}

}
