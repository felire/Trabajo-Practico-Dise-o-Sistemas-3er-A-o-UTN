package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnit44Runner;

import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.observer.ObserverMail;
import ar.utn.frba.disenio.tp_anual.servicios.ServicioMail;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorPOIs;
import ar.utn.frba.disenio.tp_anual.servicios.impl.GestorBusquedas;

import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
@RunWith(MockitoJUnit44Runner.class)


public class TestMail extends Init{
	
	@Mock ServicioMail servicioMail;
	@InjectMocks ObserverMail observer = new ObserverMail();
	
	@Before
	public void setUp(){
		
	}
	@Test
	public void pruebaLLamadoMail(){		
		observer.avisarPorMail(7.3);
		verify(servicioMail).reportarTardanza(7.3);
			    
	  }

}
