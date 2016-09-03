package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnit44Runner;
import util.Point;
import util.Polygon;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.observer.ObserverMail;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.servicios.ServicioExternoCGP;
import ar.utn.frba.disenio.tp_anual.servicios.ServicioMail;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorCGP;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorPOIs;
import ar.utn.frba.disenio.tp_anual.servicios.impl.GestorBusquedas;
import util.reportes.CreadorDeReportes;

import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
@RunWith(MockitoJUnit44Runner.class)

public class TestTerminalObservers extends Init{
	@Mock ServicioMail servicioMail;
	@InjectMocks ObserverMail observerMail = new ObserverMail();
	GestorBusquedas gestorBusquedas = new GestorBusquedas(new CreadorDeReportes());
	Terminal terminal = new Terminal(new BuscadorPOIs(), "Terminus", 0);
	Terminal alberdi = new Terminal(new BuscadorPOIs(), "Alberdi", 0);
	Terminal rivadavia = new Terminal(new BuscadorPOIs(), "Rivadavia", 0);
	
	@Before
	public void setUp(){
		terminal.addObserver(observerMail);
		terminal.addObserver(gestorBusquedas);
		alberdi.addObserver(gestorBusquedas);
		rivadavia.addObserver(gestorBusquedas);
	}

	
	/*@Test

	public void testObserverMail(){
		terminal.buscar("Banco Santander", null);
		verify(servicioMail).reportarTardanza(gestorBusquedas.getBusquedas().get(0).getTiempoDemorado());
	}
	
	@Test
	public void testBusquedasPorFecha(){
		alberdi.buscar("cGP", null);
		alberdi.buscar("banco", null);
		rivadavia.buscar("localComercial", null);
		assertEquals(1, gestorBusquedas.busquedasPorFecha().size());
		assertEquals(LocalDate.now(), gestorBusquedas.busquedasPorFecha().get(0).getFecha());
		assertEquals(3, (int) gestorBusquedas.busquedasPorFecha().get(0).getBusquedas());
	}
	
	@Test
	public void testBusquedasParcialesPorTerminal(){
		alberdi.buscar("bondi", null);
		alberdi.buscar("agip", null);
		rivadavia.buscar("prestamos", null);
		assertEquals(2, gestorBusquedas.busquedasParcialesPorTerminal().size());
		assertEquals("Rivadavia", gestorBusquedas.busquedasParcialesPorTerminal().get(0).getUsuario());
		assertEquals("Alberdi", gestorBusquedas.busquedasParcialesPorTerminal().get(1).getUsuario());
	}
	
	@Test
	public void testBusquedasPorTerminal(){
		alberdi.buscar("bondi", null);
		alberdi.buscar("agip", null);
		rivadavia.buscar("a", null);
		assertEquals(2, gestorBusquedas.busquedasPorTerminal().size());
		assertEquals("Rivadavia", gestorBusquedas.busquedasPorTerminal().get(0).getUsuario());
		assertEquals("Alberdi", gestorBusquedas.busquedasPorTerminal().get(1).getUsuario());
		assertEquals(6, (int) gestorBusquedas.busquedasPorTerminal().get(0).getBusquedas());
		assertEquals(4, (int) gestorBusquedas.busquedasPorTerminal().get(1).getBusquedas());
	}

	*/

	
	@After
	public void tearDown(){
		gestorBusquedas = new GestorBusquedas(new CreadorDeReportes());
	}

}
