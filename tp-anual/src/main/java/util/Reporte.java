package util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.poi.POI;

public class Reporte {
	List<POI> buscados;
	LocalDateTime fecha;
	String fraseBuscada;
	String servicioBuscado;
	BigDecimal tiempoDemorado;
	
	public Reporte(List<POI> buscados, String fraseBuscada, String servicioBuscado, BigDecimal demora){
		this.buscados = buscados;
		this.fecha = LocalDateTime.now(); //Lo seteamos con la fecha actual
		this.fraseBuscada = fraseBuscada;
		this.servicioBuscado = servicioBuscado;
		this.tiempoDemorado = demora;
	}
	
	public Boolean mismaFecha(LocalDateTime otraFecha){
		return this.fecha.equals(otraFecha);
	}
}
