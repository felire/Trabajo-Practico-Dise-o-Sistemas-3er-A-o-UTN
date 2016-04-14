package ar.utn.frba.disenio.tp_anual;

import java.sql.Date;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class CGP extends POI{
	private Polygon comuna;
	
	public CGP(Polygon comuna)
	{
		this.comuna = comuna;
	}
	public Boolean esCercano(Coordenada coordenada) {
		Point punto = new Point(coordenada.getLatitud(), coordenada.getLongitud());
		return this.comuna.isInside(punto);
	}

	public Boolean estaDisponible(Date fecha, String valorX) {
		// TODO Auto-generated method stub
		return null;
	}

}
