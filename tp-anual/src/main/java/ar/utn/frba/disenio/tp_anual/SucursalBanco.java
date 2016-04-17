package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

import org.uqbar.geodds.Polygon;

public class SucursalBanco extends POI{

	private List<Servicio> servicios;
	private static DisponibilidadHoraria horarioBancario;
	
	public SucursalBanco(){
		setHorarioBancario();
	}

	private void setHorarioBancario() {
		FranjaHoraria franjaHoraria = new FranjaHoraria(10,15);
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
		if(fueraDeHorarioBancario(fecha)) return false;
		return servicios.stream().anyMatch(servicio -> servicio.toString().equals(valorX) && servicio.estaDisponibe(fecha));
	}
	

	private boolean fueraDeHorarioBancario(LocalDateTime fecha) {
		return horarioBancario.estaDisponible(fecha);
	}

	public Boolean estaDisponible(LocalDateTime fecha)
	{
		if(fueraDeHorarioBancario(fecha)) return false;
		return servicios.stream().anyMatch(servicio -> servicio.estaDisponibe(fecha));
	}
	
}
