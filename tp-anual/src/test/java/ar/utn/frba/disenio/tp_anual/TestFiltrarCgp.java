package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnit44Runner;

import ar.utn.frba.disenio.tp_anual.externo.ServicioExternoCGP;
import ar.utn.frba.disenio.tp_anual.gestion.BuscadorCGP;
import ar.utn.frba.disenio.tp_anual.gestion.BuscadorPOIs;
import ar.utn.frba.disenio.tp_anual.gestion.RepoPOIS;

import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnit44Runner.class)

public class TestFiltrarCgp {

	@Mock ServicioExternoCGP servicioExterno;
	@InjectMocks BuscadorCGP buscadorCGP = new BuscadorCGP();
	private BuscadorPOIs buscadorPOIS = new BuscadorPOIs();
	
	@Before
	public void setUp(){
		buscadorPOIS.agregarBuscadorExterno(buscadorCGP);
	}
	@Test
	public void TestFiltrarCGP() {
		buscadorPOIS.buscarPOIs("palabraClave");
	    verify(servicioExterno).search("palabraClave");
	}

}