package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.Point;
import util.Polygon;

public class TestAdapterPoint {
	
	private Point punto;
	private Polygon polygon;
	@Before
	public void setUp(){
		punto = new Point(-34.804824,-58.449473);
		polygon = new Polygon();
		polygon.add(new Point(0,0));
		polygon.add(new Point(0,100));
		polygon.add(new Point(100,100));
		polygon.add(new Point(100,0));
	}
	@Test
	public void test() {
		assertTrue(punto.distance(new Point(-34.806537, -58.447796)) <= 0.5);
		assertTrue(polygon.isInside(new Point(50,50)));
	}

}
