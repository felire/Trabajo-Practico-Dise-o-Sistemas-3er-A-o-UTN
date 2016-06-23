package util;

import java.time.LocalDateTime;

import ar.utn.frba.disenio.tp_anual.gestion.GestionadorProcesos;

public abstract class ProcesoGeneral implements Runnable{
	public abstract LocalDateTime getFecha();
	public abstract void setGestionadorProcesos(GestionadorProcesos gestionador);
}
