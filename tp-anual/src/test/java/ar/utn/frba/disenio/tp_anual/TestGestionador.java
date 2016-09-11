package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnit44Runner;
import util.Point;
import util.Polygon;
import ar.utn.frba.disenio.tp_anual.adapter.AdapterActualizacionLocalComercial;
import ar.utn.frba.disenio.tp_anual.adapter.TraductorStringLocalCom;
import ar.utn.frba.disenio.tp_anual.observer.ObserverMail;
import ar.utn.frba.disenio.tp_anual.schedule.process.GestionadorProcesos;
import ar.utn.frba.disenio.tp_anual.schedule.process.ProcesoActualizarTags;
import ar.utn.frba.disenio.tp_anual.schedule.process.ProcesoErrorMail;
import ar.utn.frba.disenio.tp_anual.schedule.process.ProcesoErrorReintentar;
import ar.utn.frba.disenio.tp_anual.schedule.process.ProcesoGeneral;
import ar.utn.frba.disenio.tp_anual.servicios.ServicioMail;
import ar.utn.frba.disenio.tp_anual.servicios.ServicioMailError;

@RunWith(MockitoJUnit44Runner.class)
public class TestGestionador {

	GestionadorProcesos gestionador = new GestionadorProcesos();
	TraductorStringLocalCom traductor;
	AdapterActualizacionLocalComercial adapter = new AdapterActualizacionLocalComercial();
	ProcesoGeneral proceso = new ProcesoActualizarTags(LocalDateTime.now(),adapter,gestionador);
	ProcesoGeneral proceso2 = new ProcesoActualizarTags(LocalDateTime.now(),adapter,gestionador);
	@Mock ServicioMailError servicioMail;
	@InjectMocks ProcesoErrorMail errorMail = new ProcesoErrorMail();
	ProcesoErrorReintentar errorR = new ProcesoErrorReintentar(5);
	//ProcesoGeneral decoradoMail = new ProcesoMail(proceso2);
	@Before
	public void setUp(){
		adapter.setPathArchivo("TextoLocalComercial.txt");
		traductor = new TraductorStringLocalCom();
		adapter.setTraductor(traductor);
		proceso.addTrata(errorMail);
		proceso2.addTrata(errorR);
		gestionador.agregarTarea(proceso);
		gestionador.agregarTarea(proceso2);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void test(){
		assertEquals(0, gestionador.getListaResultados().get(0).getCantidadAfectados().intValue());
	}
	@Test
	public void testMail(){
		verify(servicioMail).mandarMail();
	}
}
