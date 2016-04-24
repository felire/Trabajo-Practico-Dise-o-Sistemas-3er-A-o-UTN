package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public abstract class PrestadorDeServicios extends POI{
	private List<Servicio> servicios;
	public PrestadorDeServicios(String nombre,Point coordenada)
	{
		super(nombre,coordenada);
		servicios = new ArrayList<Servicio>();
	}
	
	public void addServicio(Servicio servicio)
	{
		servicios.add(servicio);
	}
	
	public void deleteServicio(Servicio servicio)
	{
		servicios.remove(servicio);
	}
	
	public Boolean estaDisponible(DayOfWeek dia, LocalTime hora, String valorX) 
	{
		return servicios.stream().anyMatch(servicio -> servicio.toString().equals(valorX) && servicio.estaDisponible(dia,hora));
	}
	public Boolean estaDisponible(DayOfWeek dia, LocalTime hora) 
	{
		return servicios.stream().anyMatch(servicio -> servicio.estaDisponible(dia,hora));
	}
	
	public Boolean esBuscado(String palabraClave){
		return this.servicios.stream().anyMatch(servicio -> servicio.toString().contains(palabraClave)) || super.esBuscado(palabraClave);
	}
}
