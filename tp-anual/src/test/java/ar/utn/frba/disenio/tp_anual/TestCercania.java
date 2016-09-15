package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

public class TestCercania extends Init{
	
	/* Tests de Local Comercial */
	
	@Test
	public void testPuntoCercanoLocalComercial(){
		assertTrue( localComercial.esCercano(new Point(-34.806537, -58.447796)));
	}
	
	/* Tests de CGP */
	
    @Test
    public void testPuntosCercanosACGP()
    {
    	assertTrue( cGP.esCercano(puntoEnLaComuna));
    	assertFalse( cGP.esCercano(puntoFueraDeLaComuna));
    }
    /*Tests de Banco */
    
    @Test
    public void testPuntoCercanoBanco(){
    	assertTrue( banco.esCercano(new Point(-34.603690, -58.416492)));
    	assertFalse(banco.esCercano(new Point(-34.603214, -58.421003)));
    }
    
    /*Tests de Parada De Colectivo */
    
    @Test
    public void testPuntoCercanoParadaColectivo(){
    	assertTrue( paradaDe55.esCercano(new Point(-34.598384, -58.420269)));
    	assertFalse( paradaDe55.esCercano(new Point(-34.599647, -58.420462)));
    }
    
}
