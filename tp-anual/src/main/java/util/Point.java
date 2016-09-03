package util;

public class Point extends org.uqbar.geodds.Point{
	
	private double aX;
	private double aY;
	//private org.uqbar.geodds.Point punto;
	
	public double aY(){
		return this.aY;
	}
	
	public double aX(){
		return this.aX;
	}
	
	public Point(double aX, double aY) {
		super(aX, aY);
		this.aX = aX;
		this.aY = aY;
		//punto = new org.uqbar.geodds.Point(aX,aY);
	}
	
}
