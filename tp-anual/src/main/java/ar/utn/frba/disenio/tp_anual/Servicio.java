package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

public class Servicio {
	private String nombre;
	private Set<DisponibilidadHoraria> diasDisponibles;
	
	public Servicio(String nombre, Set<DisponibilidadHoraria> diasDisponibles) {
		this.nombre = nombre;
		this.diasDisponibles = diasDisponibles;
	}
	
	public boolean estaDisponible(DayOfWeek dia,LocalTime hora)
	{
		return diasDisponibles.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(dia,hora));
	}
	
	public String toString()
	{
		return nombre;
	}
}
