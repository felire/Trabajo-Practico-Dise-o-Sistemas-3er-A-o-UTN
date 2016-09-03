package ar.utn.frba.disenio.tp_anual.model;

import java.time.LocalDateTime;

import ar.utn.frba.disenio.tp_anual.schedule.process.ProcesoStatus;

import javax.persistence.*;

import org.uqbarproject.jpa.java8.extras.convert.LocalDateTimeConverter;

@Entity
public class ResultadoProceso {
	
	@Id @GeneratedValue
	private Integer id;
	
	Integer cantidadAfectados;
	@Convert (converter = LocalDateTimeConverter.class)
	LocalDateTime fechaEjecucion;
	@Enumerated
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
