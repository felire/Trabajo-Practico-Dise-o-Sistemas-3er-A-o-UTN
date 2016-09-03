package ar.utn.frba.disenio.tp_anual.model;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Transient;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;



@Entity
@DiscriminatorValue(value = "cgp")
public class CGP extends PrestadorDeServicios{
	
	@Transient
	private Polygon comuna;
	
	public Polygon getComuna() {
		return comuna;
	}

	public void setComuna(Polygon comuna) {
		this.comuna = comuna;
	}

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
