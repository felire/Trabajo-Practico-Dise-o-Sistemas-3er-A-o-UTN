package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import ar.utn.frba.disenio.tp_anual.gestion.GestionadorProcesos;
import util.ProcesoActualizarTags;
import util.ProcesoGeneral;
import ar.utn.frba.disenio.tp_anual.AdapterActualizacionLocalComercial;

public class TestGestionador {

	GestionadorProcesos gestionador = new GestionadorProcesos();
	AdapterActualizacionLocalComercial adapter = new AdapterActualizacionLocalComercial("TextoLocalComercial.txt");
	ProcesoGeneral proceso = new ProcesoActualizarTags(LocalDateTime.now(),adapter,gestionador);
	@Before
	public void setUp(){
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
