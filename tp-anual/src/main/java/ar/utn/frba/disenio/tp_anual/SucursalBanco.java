package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;

public class SucursalBanco extends POI{

	private List<Servicio> servicios;
	private static DisponibilidadHoraria horarioBancario;
	
	public SucursalBanco(){
		servicios = new ArrayList<Servicio>();
		setHorarioBancario();
	}

	private void setHorarioBancario() {
		FranjaHoraria franjaHoraria = new FranjaHoraria(LocalTime.of(10, 0),LocalTime.of(15, 0));
		horarioBancario = new DisponibilidadHoraria(DayOfWeek.MONDAY, 
				DayOfWeek.FRIDAY, franjaHoraria);
	}
	
	public void addServicio(Servicio servicio)
	{
		servicios.add(servicio);
	}
	
	public void deleteServicio(Servicio servicio)
	{
		servicios.remove(servicio);
	}

	public Boolean estaDisponible(LocalDateTime fecha, String valorX) 
	{
		if(!enHorarioBancario(fecha)) return false;
		if(valorX==null || valorX.equals("")) return servicios.stream().anyMatch(servicio -> servicio.estaDisponible(fecha));
		return servicios.stream().anyMatch(servicio -> servicio.toString().equals(valorX) && servicio.estaDisponible(fecha));
	}
	
	private boolean enHorarioBancario(LocalDateTime fecha) {
		return horarioBancario.estaDisponible(fecha);
	}	
}
