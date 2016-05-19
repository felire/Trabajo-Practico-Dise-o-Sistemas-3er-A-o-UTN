package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnit44Runner;

import ar.utn.frba.disenio.tp_anual.externo.ServicioExternoBanco;
import ar.utn.frba.disenio.tp_anual.gestion.BuscadorBanco;
import ar.utn.frba.disenio.tp_anual.gestion.BuscadorPOIs;
import ar.utn.frba.disenio.tp_anual.gestion.RepoPOIS;

import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnit44Runner.class)
public class TestFiltrarBancos {
	
	@Mock ServicioExternoBanco servicioExterno;
	@InjectMocks BuscadorBanco buscadorBanco = new BuscadorBanco();
	private BuscadorPOIs buscadorPOIS = new BuscadorPOIs();
	
	@Before
	public void setUp(){
		buscadorPOIS.agregarBuscadorExterno(buscadorBanco);
	}
	@Test
	public void pruebaLLamadoFiltrarBancos(){
		buscadorPOIS.buscarPOIs("Banco 1");
		buscadorPOIS.buscarPOIs("Banco 1", "Servicio 1");
	    verify(servicioExterno).search("Banco 1", null); // ¿En verdad se llamo a este método?    
	    verify(servicioExterno).search("Banco 1", "Servicio 1");
	  }
	

}
