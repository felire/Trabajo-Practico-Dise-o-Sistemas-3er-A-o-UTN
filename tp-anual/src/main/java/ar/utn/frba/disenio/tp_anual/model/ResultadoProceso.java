package ar.utn.frba.disenio.tp_anual.model;

import java.time.LocalDateTime;

import ar.utn.frba.disenio.tp_anual.schedule.process.ProcesoStatus;

public class ResultadoProceso {
	Integer cantidadAfectados;
	LocalDateTime fechaEjecucion;
	ProcesoStatus resultadoDelProceso;
	
	public ResultadoProceso(Integer cantidadAfectados, LocalDateTime fechaEjecucion, ProcesoStatus resultado){
		this.cantidadAfectados = cantidadAfectados;
		this.fechaEjecucion = fechaEjecucion;
		this.resultadoDelProceso = resultado;		
	}	
	public Integer getCantidadAfectados(){
		return cantidadAfectados;
	}
}
