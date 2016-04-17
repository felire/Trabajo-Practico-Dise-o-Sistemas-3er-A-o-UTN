package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public class Disponibilidad {

	private DayOfWeek desdeDia;
	private DayOfWeek hastaDia;
	private List<FranjaHoraria> franjas;
	
	public Boolean estaDisponible(LocalDateTime fecha){
		Integer dia = fecha.getDayOfWeek().getValue();
		Integer hora = fecha.getHour();
		return (diaEstaDisponible(dia) && horaEstaDisponible(hora));
	
	}

	private boolean horaEstaDisponible(Integer hora) {
		return franjas.stream().anyMatch(franja -> franja.contiene(hora));
	}

	private Boolean diaEstaDisponible(Integer dia) {
		return (desdeDia.getValue() < dia && dia < hastaDia.getValue());
	}
}
