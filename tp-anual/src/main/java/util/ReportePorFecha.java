package util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReportePorFecha {
	private LocalDate fecha;
	private Integer busquedas;
	
	public ReportePorFecha(LocalDate fecha, Integer busquedas){
		this.fecha = fecha;
		this.busquedas = busquedas;
	}
}
