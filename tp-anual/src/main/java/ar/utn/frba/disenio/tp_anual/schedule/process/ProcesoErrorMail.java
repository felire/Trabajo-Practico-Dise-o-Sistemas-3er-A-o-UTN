package ar.utn.frba.disenio.tp_anual.schedule.process;

import ar.utn.frba.disenio.tp_anual.servicios.ServicioMailError;

public class ProcesoErrorMail implements ProcesoError{
	private ServicioMailError servicioMail;
	
	@Override
	public void accion(ProcesoGeneral proceso) {
		servicioMail.mandarMail();	
	}
	
}
