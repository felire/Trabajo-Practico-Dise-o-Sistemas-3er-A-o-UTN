package ar.utn.frba.disenio.tp_anual.schedule.process;

import java.time.LocalDateTime;

import ar.utn.frba.disenio.tp_anual.model.ResultadoProceso;

public abstract class ProcesoGeneral implements Runnable{
	private ProcesoStatus estado;
	private LocalDateTime fecha;
	private GestionadorProcesos gestionador;
	public abstract void accion() throws Exception;
	
	public void setFecha(LocalDateTime fecha){
		this.fecha = fecha;
	}
	public LocalDateTime getFecha(){
		return this.fecha;
	}
	public void setGestionadorProcesos(GestionadorProcesos gestionador){
		this.gestionador = gestionador;
	}
	
	public void run(){
		try {
			this.accion();
		} catch (Exception e) {
			estado = ProcesoStatus.ERROR;
		}
	
		ResultadoProceso resultado = new ResultadoProceso(this.elementosAfectados(),this.getFecha(), this.getEstado());
		this.gestionador.addResultado(resultado);
	}
	public abstract Integer elementosAfectados();
	public void setEstado(ProcesoStatus estado){
		this.estado = estado;
	}
	public ProcesoStatus getEstado(){
		return this.estado;
	}
	
}
