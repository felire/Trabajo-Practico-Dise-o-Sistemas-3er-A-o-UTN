package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DiaLaboral {
	private DayOfWeek dia;
	private List<FranjaHoraria> franjasDelDia;
	
	public DiaLaboral(DayOfWeek dia, ArrayList<FranjaHoraria> franjas){
		this.dia=dia;
		this.franjasDelDia=franjas;		
	}
	
	public Boolean diaDisponible(DayOfWeek dia){
		return this.dia==dia;
	}
	
	public Boolean horaDisponible(LocalTime hora){
		return franjasDelDia.stream().anyMatch(franja-> franja.contiene(hora));
	}

	
    public Boolean estaDisponible(LocalDateTime fecha) {
		return this.diaDisponible(fecha.getDayOfWeek()) && horaDisponible(fecha.toLocalTime());
	}
}
