package util;

import javax.persistence.*;

@Entity
public class Point extends org.uqbar.geodds.Point{
	
	@Id @GeneratedValue
	private Integer id;
	
	private double latitud;
	private double longitud;
	
	public double getLatitud() {
		return latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public Point(double aX, double aY) {
		super(aX, aY);
		this.latitud = aX;
		this.longitud = aY;
	}
	
}
