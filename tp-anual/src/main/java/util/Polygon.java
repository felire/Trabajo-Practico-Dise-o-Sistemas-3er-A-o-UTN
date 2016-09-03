package util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class Polygon extends org.uqbar.geodds.Polygon{
	
	@Id @GeneratedValue
	private Integer id;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="poligono_id")
	List<Point> puntos;
	
	public Polygon(){
		super();
		puntos = new ArrayList<Point>();
	}
	public void add (Point point){
		super.add(point);
		this.puntos.add(point);
	}
	
}
