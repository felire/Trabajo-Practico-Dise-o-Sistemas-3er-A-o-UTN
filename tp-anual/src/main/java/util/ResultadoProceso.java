package util;

import java.time.LocalDateTime;

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
