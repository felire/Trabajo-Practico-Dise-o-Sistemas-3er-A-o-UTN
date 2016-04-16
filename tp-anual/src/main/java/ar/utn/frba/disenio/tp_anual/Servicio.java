package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public class Servicio {//Atencion, para modelar esto podriamos hacer que cada dia maneje sus horarios ya que algunos negocios son asi que se y.
	private String nombre;
	private int horarioArranque;
	private int horarioCierre;
	private List<DayOfWeek> diasDeAtencion;
	public void addDiaLaboral(DayOfWeek dia)
	{
		diasDeAtencion.add(dia);
	}
	public void deleteDiaLaboral(DayOfWeek dia)
	{
		diasDeAtencion.remove(dia);
	}
	public boolean estaDisponibe(LocalDateTime fecha)
	{
		return (diasDeAtencion.contains(fecha.getDayOfWeek()) && fecha.getHour() > horarioArranque && fecha.getHour()< horarioCierre);
	}
	
	public String toString()
	{
		return nombre;
	}
}
