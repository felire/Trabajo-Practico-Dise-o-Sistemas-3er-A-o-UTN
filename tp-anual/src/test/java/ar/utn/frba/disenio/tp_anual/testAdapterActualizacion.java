package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class testAdapterActualizacion {
	
	AdapterActualizacionLocalComercial adapter;
	@Before
	public void setUp(){
		adapter = new AdapterActualizacionLocalComercial();
	}
	@Test
	public void test() {
		adapter.traducirArchivo();
	}

}
