package ar.utn.frba.disenio.tp_anual;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends POI{
	private Polygon comuna;
	private List<Servicio> servicios;
	
	public CGP(Polygon comuna, Point coordenada)
	{
		super(coordenada);
		servicios = new ArrayList<Servicio>();
		this.comuna = comuna;
		
	}
	public void addServicio(Servicio servicio)
	{
		servicios.add(servicio);
		listaTags.add(servicio.toString());
	}
	
	public void deleteServicio(Servicio servicio)
	{
		servicios.remove(servicio);
		listaTags.remove(servicio.toString());
	}
	
	public Boolean esCercano(Point coordenada) 
	{
		return this.comuna.isInside(coordenada);
	}

	public Boolean estaDisponible(LocalDateTime fecha, String valorX) 
	{
		if(valorX==null || valorX.equals("")) return servicios.stream().anyMatch(servicio -> servicio.estaDisponible(fecha));
		return servicios.stream().anyMatch(servicio -> servicio.toString().equals(valorX) && servicio.estaDisponible(fecha));
	}

}
