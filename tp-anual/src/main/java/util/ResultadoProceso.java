package util;

import java.time.LocalDateTime;

public class ResultadoProceso {
	Integer cantidadAfectados;
	LocalDateTime fechaEjecucion;
	Boolean resultadoDelProceso;
	
	public ResultadoProceso(Integer cantidadAfectados, LocalDateTime fechaEjecucion, Boolean resultadoDelProceso){
		this.cantidadAfectados = cantidadAfectados;
		this.fechaEjecucion = fechaEjecucion;
		this.resultadoDelProceso = resultadoDelProceso;		
	}	
}
