package util;

public class Point extends org.uqbar.geodds.Point{
	
	private double aX;
	private double aY;
	//private org.uqbar.geodds.Point punto;
	
	public Point(double aX, double aY) {
		super(aX, aY);
		this.aX = aX;
		this.aY = aY;
		//punto = new org.uqbar.geodds.Point(aX,aY);
	}
	
	public double distance(Point point){
		return super.distance(new org.uqbar.geodds.Point(point.aX, point.aY));
	}
	
}
