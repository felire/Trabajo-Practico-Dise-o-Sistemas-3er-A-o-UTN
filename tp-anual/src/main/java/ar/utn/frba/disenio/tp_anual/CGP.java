package ar.utn.frba.disenio.tp_anual;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends PrestadorDeServicios{
	private Polygon comuna;
	
	public CGP(String nombre,Polygon comuna, Point coordenada)
	{
		super(coordenada);
		this.nombre = nombre;
		this.comuna = comuna;
		
	}
	
	public Boolean esCercano(Point coordenada) 
	{
		return this.comuna.isInside(coordenada);
	}

	

}
