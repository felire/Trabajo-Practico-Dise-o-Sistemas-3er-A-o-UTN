package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DisponibilidadHoraria {
	private List<FranjaHoraria> franjas;
	private List<DayOfWeek> dias;
	
	public DisponibilidadHoraria(ArrayList<FranjaHoraria> franjas, ArrayList<DayOfWeek>  dias){
		this.franjas = franjas;
		this.dias = dias;		
	}
	public Boolean diaDisponible(LocalDateTime fecha){
		return this.dias.contains(fecha.getDayOfWeek());
	}
	public Boolean horaDisponible(LocalDateTime fecha){
		return this.franjas.stream().anyMatch(franja->franja.contiene(fecha.toLocalTime()));
	}
	public Boolean estaDisponible(LocalDateTime fecha){
		return diaDisponible(fecha) && horaDisponible(fecha);
	}
}
