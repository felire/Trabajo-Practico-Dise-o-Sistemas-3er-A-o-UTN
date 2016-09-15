package util;

import java.time.LocalTime;

public class FranjaHoraria {

	private LocalTime desdeHorario;
	private LocalTime hastaHorario;
	
	public Boolean contiene(LocalTime hora){
		return (desdeHorario.isBefore(hora) && hora.isBefore(hastaHorario));
	}
	
	public FranjaHoraria(LocalTime desde, LocalTime hasta){
		this.desdeHorario = desde;
		this.hastaHorario = hasta;
	}
}
