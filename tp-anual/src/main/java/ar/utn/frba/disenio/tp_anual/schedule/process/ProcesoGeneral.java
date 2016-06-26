package ar.utn.frba.disenio.tp_anual.schedule.process;

import java.time.LocalDateTime;

import util.ErrorHandler;

public abstract class ProcesoGeneral implements Runnable{
	public ProcesoStatus estado = ProcesoStatus.SUCCESS;
	
	public abstract LocalDateTime getFecha();
	public abstract void setGestionadorProcesos(GestionadorProcesos gestionador);
	public abstract void accion() throws Exception;
	
	public void handleError(){
		ErrorHandler.getInstance().handle(this);
	};
	
}
