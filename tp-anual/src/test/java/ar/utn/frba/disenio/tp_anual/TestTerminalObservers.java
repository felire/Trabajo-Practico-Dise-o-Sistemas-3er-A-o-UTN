package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnit44Runner;

import ar.utn.frba.disenio.tp_anual.externo.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.externo.ServicioExternoCGP;
import ar.utn.frba.disenio.tp_anual.externo.ServicioMail;
import ar.utn.frba.disenio.tp_anual.gestion.BuscadorCGP;
import ar.utn.frba.disenio.tp_anual.gestion.BuscadorPOIs;
import ar.utn.frba.disenio.tp_anual.gestion.GestorBusquedas;
import ar.utn.frba.disenio.tp_anual.gestion.ObserverMail;
import ar.utn.frba.disenio.tp_anual.gestion.Terminal;
import util.CreadorDeReportes;

import org.mockito.runners.MockitoJUnit44Runner;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
@RunWith(MockitoJUnit44Runner.class)

public class TestTerminalObservers extends TestInit{
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
	
	@Test
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
		alberdi.buscar("cGP", null);
		alberdi.buscar("banco", null);
		rivadavia.buscar("localComercial", null);
		assertEquals(2, gestorBusquedas.busquedasParcialesPorTerminal().size());
		assertEquals("Rivadavia", gestorBusquedas.busquedasParcialesPorTerminal().get(0).getUsuario());
		assertEquals("Alberdi", gestorBusquedas.busquedasParcialesPorTerminal().get(1).getUsuario());
	}
	
}
