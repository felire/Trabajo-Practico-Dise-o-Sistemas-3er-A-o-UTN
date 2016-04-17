package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public class Servicio {
	private String nombre;
	private List<Disponibilidad> diasDisponibles;
	
	public boolean estaDisponibe(LocalDateTime fecha)
	{
		return diasDisponibles.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(fecha));
	}
	
	public String toString()
	{
		return nombre;
	}
}
