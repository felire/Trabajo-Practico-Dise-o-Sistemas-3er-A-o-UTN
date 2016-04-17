package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Disponibilidad {

	private DayOfWeek desdeDia;
	private DayOfWeek hastaDia;
	private Set<FranjaHoraria> franjas;
	
	public Boolean estaDisponible(LocalDateTime fecha){
		Integer dia = fecha.getDayOfWeek().getValue();
		Integer hora = fecha.getHour();
		return (diaEstaDisponible(dia) && horaEstaDisponible(hora));
	
	}

	public Disponibilidad(DayOfWeek desde, DayOfWeek hasta, Set<FranjaHoraria> franjas){
		this.desdeDia = desde;
		this.hastaDia = hasta;
		this.franjas = franjas;
	}
	
	public Disponibilidad(DayOfWeek desde, DayOfWeek hasta, FranjaHoraria franja){
		this.desdeDia = desde;
		this.hastaDia = hasta;
		this.franjas = new HashSet<>();
		this.franjas.add(franja);
	}
	
	public Disponibilidad(DayOfWeek dia, FranjaHoraria franja){
		this.desdeDia = dia;
		this.hastaDia = dia;
		this.franjas = new HashSet<>();
		this.franjas.add(franja);
	}
	
	public Disponibilidad(DayOfWeek dia, Set<FranjaHoraria> franjas){
		this.desdeDia = dia;
		this.hastaDia = dia;
		this.franjas = franjas;
	}
	
	private boolean horaEstaDisponible(Integer hora) {
		return franjas.stream().anyMatch(franja -> franja.contiene(hora));
	}

	private Boolean diaEstaDisponible(Integer dia) {
		return (desdeDia.getValue() < dia && dia < hastaDia.getValue());
	}
}
