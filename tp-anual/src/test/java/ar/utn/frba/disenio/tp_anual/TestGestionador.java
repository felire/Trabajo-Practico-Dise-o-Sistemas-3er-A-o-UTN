package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import ar.utn.frba.disenio.tp_anual.adapter.AdapterActualizacionLocalComercial;
import ar.utn.frba.disenio.tp_anual.adapter.TraductorStringLocalCom;
import ar.utn.frba.disenio.tp_anual.schedule.process.GestionadorProcesos;
import ar.utn.frba.disenio.tp_anual.schedule.process.ProcesoActualizarTags;
import ar.utn.frba.disenio.tp_anual.schedule.process.ProcesoGeneral;

public class TestGestionador {

	GestionadorProcesos gestionador = new GestionadorProcesos();
	TraductorStringLocalCom traductor;
	AdapterActualizacionLocalComercial adapter = new AdapterActualizacionLocalComercial();
	ProcesoGeneral proceso = new ProcesoActualizarTags(LocalDateTime.now(),adapter,gestionador);
	@Before
	public void setUp(){
		adapter.setPathArchivo("TextoLocalComercial.txt");
		traductor = new TraductorStringLocalCom();
		adapter.setTraductor(traductor);
		gestionador.agregarTarea(proceso);
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
}
