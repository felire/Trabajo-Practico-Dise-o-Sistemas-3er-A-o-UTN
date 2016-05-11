package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnit44Runner.class) // Nos ayudamos de este runner en JUnit para usar las anotaciones de Mockito
public class TestFiltrarCGP{
	@Mock BuscadorCGP_I buscadorCGP;
	@InjectMocks BuscadorPOIS buscadorPOIS = new BuscadorPOIS();
	@Test
	  public void pruebaLLamadoFiltrarCGPs(){
		buscadorPOIS.filtrarCGPs("CGP 1");
	    verify(buscadorCGP).filtrarCGPs("CGP 1"); // ¿En verdad se llamo a este método?
	  }

}
