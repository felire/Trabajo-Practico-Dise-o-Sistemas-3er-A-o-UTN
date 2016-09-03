package ar.utn.frba.disenio.tp_anual.model;

import java.util.List;

import javax.persistence.*;

import util.Point;
import util.Polygon;

@Entity
public class CGP extends PrestadorDeServicios{
	
	@OneToOne (cascade = CascadeType.ALL)
	@JoinColumn (name = "comuna")
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
