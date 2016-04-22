package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class DisponibilidadHoraria {

	private DayOfWeek desdeDia;
	private DayOfWeek hastaDia;
	private Set<FranjaHoraria> franjas;
	
	public Boolean estaDisponible(LocalDateTime fecha){
		return (diaEstaDisponible(fecha) && horaEstaDisponible(fecha));
	}

	public DisponibilidadHoraria(DayOfWeek desde, DayOfWeek hasta, Set<FranjaHoraria> franjas){
		this.desdeDia = desde;
		this.hastaDia = hasta;
		this.franjas = franjas;
	}
	
	public DisponibilidadHoraria(DayOfWeek desde, DayOfWeek hasta, FranjaHoraria franja){
		this.desdeDia = desde;
		this.hastaDia = hasta;
		this.franjas = new HashSet<>();
		this.franjas.add(franja);
	}
	
	public DisponibilidadHoraria(DayOfWeek dia, FranjaHoraria franja){
		this.desdeDia = dia;
		this.hastaDia = dia;
		this.franjas = new HashSet<>();
		this.franjas.add(franja);
	}
	
	public DisponibilidadHoraria(DayOfWeek dia, Set<FranjaHoraria> franjas){
		this.desdeDia = dia;
		this.hastaDia = dia;
		this.franjas = franjas;
	}
	
	private Boolean horaEstaDisponible(LocalDateTime hora) {
		return franjas.stream().anyMatch(franja -> franja.contiene(hora.toLocalTime()));
	}

	private Boolean diaEstaDisponible(LocalDateTime fecha) {
		Integer dia = fecha.getDayOfWeek().getValue();
		return (desdeDia.getValue() <= dia && dia <= hastaDia.getValue());
	}
}
