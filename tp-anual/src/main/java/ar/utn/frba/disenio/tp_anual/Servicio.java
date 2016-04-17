package ar.utn.frba.disenio.tp_anual;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Servicio {
	private String nombre;
	private Set<DisponibilidadHoraria> diasDisponibles;
	
	public Servicio(String nombre, Set<DisponibilidadHoraria> diasDisponibles) {
		super();
		this.nombre = nombre;
		this.diasDisponibles = diasDisponibles;
	}

	public Servicio(String nombre, DisponibilidadHoraria disponibilidad) {
		super();
		this.nombre = nombre;
		this.diasDisponibles = new HashSet<>();
		this.diasDisponibles.add(disponibilidad);
	}
	
	public boolean estaDisponibe(LocalDateTime fecha)
	{
		return diasDisponibles.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(fecha));
	}
	
	public String toString()
	{
		return nombre;
	}
}
