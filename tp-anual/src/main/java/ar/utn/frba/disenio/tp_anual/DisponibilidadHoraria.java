package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

public class DisponibilidadHoraria {
	private ArrayList<DiaLaboral> disponibilidades;
	
	public DisponibilidadHoraria(ArrayList<DiaLaboral> disponibilidades){
		this.disponibilidades=disponibilidades;		
	}

	public Boolean estaDisponible(DayOfWeek dia, LocalTime hora){
		return disponibilidades.stream().anyMatch(disponibilidad -> (disponibilidad.estaDisponible(dia, hora)));
	}
}
