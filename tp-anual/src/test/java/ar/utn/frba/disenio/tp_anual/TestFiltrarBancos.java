package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnit44Runner;

import ar.utn.frba.disenio.tp_anual.adapter.CreadorDeBancos;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.servicios.ServicioExternoBanco;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorBanco;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorPOIs;

import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnit44Runner.class)
public class TestFiltrarBancos extends Init {
	
	@Mock ServicioExternoBanco servicioExterno;
	@InjectMocks BuscadorBanco buscadorBanco = new BuscadorBanco();
	private CreadorDeBancos creador = new CreadorDeBancos();
	private BuscadorPOIs buscadorPOIS = new BuscadorPOIs();
	
	@Before
	public void setUp(){
		buscadorBanco.setCreadorDeBancos(creador);
		buscadorPOIS.agregarBuscadorExterno(buscadorBanco);
	}
	@Test
	public void pruebaLLamadoFiltrarBancos(){		
		buscadorPOIS.buscarPOIs("Banco 1", "Servicio 1");
		verify(servicioExterno).search("Banco 1", "Servicio 1");
		buscadorPOIS.buscarPOIs("Banco 1",null);
		verify(servicioExterno).search("Banco 1", null);
			    
	  }

}
