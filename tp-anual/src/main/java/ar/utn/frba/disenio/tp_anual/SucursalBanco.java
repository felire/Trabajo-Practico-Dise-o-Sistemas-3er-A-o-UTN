package ar.utn.frba.disenio.tp_anual;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Polygon;

public class SucursalBanco extends POI{

	private List<Servicio> servicios;
	
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
		if(isEmpty(valorX)) return true;
		return servicios.stream().anyMatch(servicio -> servicio.toString().equals(valorX) && servicio.estaDisponibe(fecha));
	}
	public Boolean estaDisponibel(LocalDateTime fecha)
	{
		return servicios.stream().anyMatch(servicio -> servicio.estaDisponibe(fecha));
	}
	
}
