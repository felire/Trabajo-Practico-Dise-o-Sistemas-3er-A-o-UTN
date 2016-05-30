package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnit44Runner;

import ar.utn.frba.disenio.tp_anual.externo.ServicioMail;
import ar.utn.frba.disenio.tp_anual.gestion.BuscadorPOIs;
import ar.utn.frba.disenio.tp_anual.gestion.GestorBusquedas;

import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnit44Runner.class)


public class TestMail {
	
	private BuscadorPOIs buscadorPOIS = new BuscadorPOIs();
	@Mock ServicioMail servicioMail;
	@InjectMocks GestorBusquedas gestor=new GestorBusquedas(5,buscadorPOIS);
	
	
	@Test
	public void pruebaLLamadoMail(){		
		gestor.avisarPorMail(7.3);
		verify(servicioMail).reportarTardanza(7.3);
			    
	  }

}
