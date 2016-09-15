package ar.utn.frba.disenio.tp_anual.schedule.process;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.ResultadoProceso;
import exception.TagsVaciosException;

public abstract class ProcesoGeneral implements Runnable{
	private ProcesoStatus estado;
	private LocalDateTime fecha;
	private GestionadorProcesos gestionador;
	private List<ProcesoError> trataErrores;
	public abstract void accion() throws Exception;
	
	public ProcesoGeneral(){
		trataErrores = new ArrayList<ProcesoError>();
	}
	public void addTrata(ProcesoError trata){
		trataErrores.add(trata);
	}
	public void deleteTrata(ProcesoError trata){
		trataErrores.remove(trata);
	}
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
			estado = ProcesoStatus.SUCCESS;
			this.accion();
		}
		catch (Exception e) {
			estado = ProcesoStatus.ERROR;
			this.notificar();
			System.out.println(e);
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
	public void notificar(){
		trataErrores.stream().forEach(error -> error.accion(this));
	}
	
}
