package util;

import java.time.LocalDateTime;

import ar.utn.frba.disenio.tp_anual.gestion.GestionadorProcesos;

public abstract class ProcesoGeneral implements Runnable{
	ProcesoStatus estado = ProcesoStatus.SUCCESS;
	
	public abstract LocalDateTime getFecha();
	public abstract void setGestionadorProcesos(GestionadorProcesos gestionador);
	public abstract void accion() throws Exception;
	
	public void handleError(){
		ErrorHandler.getInstance().handle(this);
	};
	
}
