package ar.utn.frba.disenio.tp_anual.model;

import java.time.LocalDateTime;
import java.util.Set;

import util.DisponibilidadHoraria;

public class Servicio {
	

	private String nombre;
	private Set<DisponibilidadHoraria> diasDisponibles;
	
	public Servicio(String nombre, Set<DisponibilidadHoraria> diasDisponibles) {
		this.nombre = nombre;
		this.diasDisponibles = diasDisponibles;
	}
	
	public boolean estaDisponible(LocalDateTime fecha)
	{
		return diasDisponibles.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(fecha));
	}
	public String getNombre() {
		return nombre;
	}
	public String toString()
	{
		return nombre;
	}
}
