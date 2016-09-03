package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import util.Point;

public class TestAdapterPoint {
	
	private Point punto;
	@Before
	public void setUp(){
		punto = new Point(-34.804824,-58.449473);
	}
	@Test
	public void test() {
		assertTrue(punto.distance(new Point(-34.806537, -58.447796)) <= 0.5);
	}

}
