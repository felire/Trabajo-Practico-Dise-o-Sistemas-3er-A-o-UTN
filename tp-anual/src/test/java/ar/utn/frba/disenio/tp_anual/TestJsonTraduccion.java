package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class TestJsonTraduccion extends Init{
	private JsonTraduccion jsonTraductor = new JsonTraduccion();
	private List<JsonBanco> listaBancos;
	private String jSonBancos = "["
	                             + "{ \"banco\": \"Banco de la Plaza\","
	                             + "\"x\": -35.9338322,"
	                             + "\"y\": 72.348353,"
	                             + "\"sucursal\": \"Avellaneda\","
	                             + "\"gerente\": \"Javier Loeschbor\","
	                             + "\"servicios\": [ \"cobro cheques\", \"depósitos\", \"extracciones\", \"transferencias\", \"créditos\"]"
	                             + "},"
	                             + "{ \"banco\": \"Banco de la Plaza\","
	                             + "\"x\": -35.9345681,"
	                             + "\"y\": 72.344546,"
	                             + "\"sucursal\": \"Caballito\","
	                             + "\"gerente\": \"Fabián Fantaguzzi\","
	                             + "\"servicios\": [ \"depósitos\", \"extracciones\", \"transferencias\", \"seguros\"]"
	                             + "}"
	                             +"]";
	
	@Before
	public void setUp(){
		listaBancos = jsonTraductor.traductor(jSonBancos);
	}
	@Test
	public void compararValoresLeidos(){
		assertEquals("Banco de la Plaza", listaBancos.get(0).getBanco());
		assertEquals("Avellaneda", listaBancos.get(0).getSucursal());
		assertEquals("Javier Loeschbor", listaBancos.get(0).getGerente());
		
		assertEquals("Banco de la Plaza", listaBancos.get(1).getBanco());
		assertEquals("Caballito", listaBancos.get(1).getSucursal());
		assertEquals("Fabián Fantaguzzi", listaBancos.get(1).getGerente());
		assertEquals(-35.9338322, listaBancos.get(0).getX(),0.000000001);
	}

}
