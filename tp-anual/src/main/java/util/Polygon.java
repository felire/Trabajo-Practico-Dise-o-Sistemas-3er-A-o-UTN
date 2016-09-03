package util;

import java.util.ArrayList;
import java.util.List;

public class Polygon extends org.uqbar.geodds.Polygon{
	
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
