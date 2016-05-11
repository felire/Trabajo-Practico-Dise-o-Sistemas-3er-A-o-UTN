package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;


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
	
	@Mock BuscadorBancoI buscadorBanco;
	@InjectMocks BuscadorPOIS buscadorPOIS = new BuscadorPOIS();
	@Test
	public void pruebaLLamadoFiltrarBancos(){
		buscadorPOIS.filtrarBancos("Banco 1", "Servicio 1");
	    verify(buscadorBanco).filtrarBancos("Banco 1", "Servicio 1"); // ¿En verdad se llamo a este método?
	  }
	

}
