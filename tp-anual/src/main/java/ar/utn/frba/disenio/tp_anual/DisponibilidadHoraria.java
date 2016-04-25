package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class DisponibilidadHoraria {
	private ArrayList<DiaLaboral> disponibilidades;
	
	public DisponibilidadHoraria(ArrayList<DiaLaboral> disponibilidades){
		this.disponibilidades=disponibilidades;		
	}

	public Boolean estaDisponible(LocalDateTime fecha){
		return disponibilidades.stream().anyMatch(disponibilidad -> (disponibilidad.estaDisponible(fecha)));
	}
}
