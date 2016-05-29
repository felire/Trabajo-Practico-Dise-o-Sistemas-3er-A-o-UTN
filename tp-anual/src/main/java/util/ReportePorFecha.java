package util;

import java.time.LocalDateTime;

public class ReportePorFecha {
	private LocalDateTime fecha;
	private Integer busquedas;
	
	public ReportePorFecha(LocalDateTime fecha, Integer busquedas){
		this.fecha = fecha;
		this.busquedas = busquedas;
	}
}
