package ar.utn.frba.disenio.tp_anual;

import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends PrestadorDeServicios{
	public Polygon comuna;
	
	public CGP(String nombre,Polygon comuna, Point coordenada)
	{
		super(nombre,coordenada);
		this.comuna = comuna;		
	}
	
	public void setServicios(List<Servicio> servicios) {
		this.servicios.addAll(servicios);
	}
	
	public Boolean esCercano(Point coordenada) 
	{
		return this.comuna.isInside(coordenada);
	}


}
