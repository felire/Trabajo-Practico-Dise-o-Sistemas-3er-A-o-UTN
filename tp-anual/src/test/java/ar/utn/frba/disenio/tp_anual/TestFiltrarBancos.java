package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnit44Runner.class)
public class TestFiltrarBancos {
	
	@Mock ServicioExternoBanco servicioExterno;
	//@InjectMocks BuscadorPOIS buscadorPOIS = new BuscadorPOIS();
	@InjectMocks BuscadorBanco buscadorBanco = new BuscadorBanco();
	private GestionadorPOIS buscadorPOIS = new GestionadorPOIS();
	
	@Before
	public void setUp(){
		buscadorPOIS.agregarBuscadorExterno(buscadorBanco);
	}
	@Test
	public void pruebaLLamadoFiltrarBancos(){
		buscadorPOIS.filtrarPOIs("Banco 1");
	    verify(servicioExterno).search("Banco 1", null); // ¿En verdad se llamo a este método?
	  }
	

}
