package ar.utn.frba.disenio.tp_anual;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends POI{
	private Polygon comuna;
	private List<Servicio> servicios;
	
	public CGP(Polygon comuna)
	{
		servicios = new ArrayList<Servicio>();
		this.comuna = comuna;
	}
	public void addServicio(Servicio servicio)
	{
		servicios.add(servicio);
	}
	
	public void deleteServicio(Servicio servicio)
	{
		servicios.remove(servicio);
	}
	
	public Boolean esCercano(Coordenada coordenada) 
	{
		Point punto = new Point(coordenada.getLatitud(), coordenada.getLongitud());
		return this.comuna.isInside(punto);
	}

	public Boolean estaDisponible(LocalDateTime fecha, String valorX) 
	{
		return servicios.stream().anyMatch(servicio -> servicio.toString().equals(valorX) && servicio.estaDisponibe(fecha));
	}
	
	public Boolean estaDisponibel(LocalDateTime fecha)
	{
		return servicios.stream().anyMatch(servicio -> servicio.estaDisponibe(fecha));
	}

}
