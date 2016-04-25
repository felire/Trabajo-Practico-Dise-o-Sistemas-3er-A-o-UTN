package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class TestCercania extends TestInit{
	
	/* Tests de Local Comercial */
	
	@Test
	public void testPuntoCercanoLocalComercial(){
		assertEquals(true, localComercial.esCercano(new Point(0,1)));
	}
	
/* Tests de CGP */
	
    @Test
    public void testPuntosCercanosACGP()
    {
    	assertEquals(true, cGP.esCercano(puntoEnLaComuna));
    	assertEquals(false, cGP.esCercano(puntoFueraDeLaComuna));
    }
    /*Tests de Banco */
    
    @Test
    public void testPuntoCercanoBanco(){
    	assertEquals(true, banco.esCercano(new Point(0,14.9)));
    }
    
    /*Tests de Parada De Colectivo */
    
    @Test
    public void testPuntoCercanoParadaColectivo(){
    	assertEquals(true, paradaDe55.esCercano(new Point(0, 0.1)));
    }
}
