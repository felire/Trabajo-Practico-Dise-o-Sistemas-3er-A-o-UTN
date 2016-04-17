package ar.utn.frba.disenio.tp_anual;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Polygon;

public class SucursalBanco extends POI{

	private List<Servicio> servicios;
	private static Integer horarioBancarioArranque = 10;
	private static Integer horarioBancarioCierre = 15;
	
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
		return (fecha.getHour()<horarioBancarioArranque ||
				fecha.getHour()>horarioBancarioCierre);
	}

	public Boolean estaDisponible(LocalDateTime fecha)
	{
		if(fueraDeHorarioBancario(fecha)) return false;
		return servicios.stream().anyMatch(servicio -> servicio.estaDisponibe(fecha));
	}
	
}
