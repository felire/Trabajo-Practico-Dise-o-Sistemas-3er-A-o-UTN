package ar.utn.frba.disenio.tp_anual;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;

public abstract class PrestadorDeServicios extends POI{
	private List<Servicio> servicios;
	public PrestadorDeServicios(Point coordenada)
	{
		super(coordenada);
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
	
	public Boolean estaDisponible(LocalDateTime fecha, String valorX) 
	{
		return servicios.stream().anyMatch(servicio -> servicio.toString().equals(valorX) && servicio.estaDisponible(fecha));
	}
	public Boolean estaDisponible(LocalDateTime fecha) 
	{
		return servicios.stream().anyMatch(servicio -> servicio.estaDisponible(fecha));
	}
	
	public Boolean esBuscado(String palabraClave){
		return this.servicios.stream().anyMatch(servicio -> servicio.toString().contains(palabraClave)) || super.esBuscado(palabraClave);
	}
}