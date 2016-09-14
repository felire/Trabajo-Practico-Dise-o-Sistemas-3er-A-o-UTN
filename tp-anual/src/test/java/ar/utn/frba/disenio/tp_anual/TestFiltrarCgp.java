package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnit44Runner;
import util.Point;
import util.Polygon;
import ar.utn.frba.disenio.tp_anual.adapter.CreadorDeCGP;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.servicios.ServicioExternoCGP;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorCGP;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorPOIs;

import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnit44Runner.class)
public class TestFiltrarCgp extends Init{
	
	@Mock ServicioExternoCGP servicioExterno;
	@InjectMocks BuscadorCGP buscadorCGP = new BuscadorCGP();
	  //private BuscadorPOIs buscadorPOIS = BuscadorPOIs.getInstance();
	
	@Before
	public void setUp(){
		BuscadorPOIs.getInstance().agregarBuscadorExterno(buscadorCGP);
	}
	@Test
	public void pruebaLLamadoFiltrarCGPs(){		
		BuscadorPOIs.getInstance().buscarPOIs("PalabraClave",null);
		verify(servicioExterno).search("PalabraClave");
			    
	  }

}
