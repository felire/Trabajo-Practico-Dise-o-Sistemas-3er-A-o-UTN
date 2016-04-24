package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DisponibilidadDelDia {
	private DayOfWeek dia;
	private List<FranjaHoraria> franjasDelDia;
	
	public DisponibilidadDelDia(DayOfWeek dia, ArrayList<FranjaHoraria> franjas){
		this.dia=dia;
		this.franjasDelDia=franjas;		
	}
	
	public Boolean diaDisponible(DayOfWeek dia){
		return this.dia==dia;
	}
	
	public Boolean horaDisponible(LocalTime hora){
		return franjasDelDia.stream().anyMatch(franja-> franja.contiene(hora));
	}
}
