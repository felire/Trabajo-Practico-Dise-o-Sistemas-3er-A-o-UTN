package util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Polygon extends org.uqbar.geodds.Polygon{
	
	@Id @GeneratedValue
	private Integer id;
	private String nombre;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="poligono_id")
	List<Point> puntos;
	
	public Polygon(){
		super();
		puntos = new ArrayList<Point>();
	}
	public void setNombre(String nombre){
		this.nombre = nombre;
	}
	public void add (Point point){
		super.add(point);
		this.puntos.add(point);
	}
	
}
