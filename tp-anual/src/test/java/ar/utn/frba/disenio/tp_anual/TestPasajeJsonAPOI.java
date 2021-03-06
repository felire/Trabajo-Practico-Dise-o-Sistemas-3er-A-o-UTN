package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import util.Point;
import util.Polygon;

import ar.utn.frba.disenio.tp_anual.adapter.CreadorDeBancos;
import ar.utn.frba.disenio.tp_anual.adapter.JsonTraduccion;
import ar.utn.frba.disenio.tp_anual.json.JsonBanco;
import ar.utn.frba.disenio.tp_anual.model.SucursalBanco;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorBanco;

public class TestPasajeJsonAPOI extends Init{
	
	private JsonTraduccion jsonTraductor = new JsonTraduccion();
	private List<JsonBanco> listaBancos;
	private BuscadorBanco buscadorBanco = new BuscadorBanco();
	private List<SucursalBanco> listaPOIS;
	private CreadorDeBancos creadorDeBancos;
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
		creadorDeBancos = new CreadorDeBancos();
		listaPOIS = creadorDeBancos.crearPOIS(listaBancos);
		
	}
	
	
	@Test
	public void testPasajePOIs(){
		assertEquals("Banco de la Plaza", listaPOIS.get(0).getNombre());
		assertEquals("cobro cheques", listaPOIS.get(0).getServicios().get(0).toString());
		assertEquals("Banco de la Plaza", listaPOIS.get(1).getNombre());
		assertEquals("depósitos", listaPOIS.get(1).getServicios().get(0).toString());
		assertEquals("seguros", listaPOIS.get(1).getServicios().get(3).toString());
		assertTrue( listaPOIS.get(0).esCercano(new Point(-35.9338322,72.348353)));
	}

}
