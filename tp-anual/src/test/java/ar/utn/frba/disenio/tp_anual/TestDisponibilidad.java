package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Test;

public class TestDisponibilidad extends Init{
	
	/* Tests Disponibilidad de Banco */
    
    @Test
    public void testDisponibilidadSucursalBanco()
    {
    	assertTrue( banco.estaDisponible(fechaBanco));
    }	
    
    @Test
    public void testDisponibilidadServiciosDeBanco()
    {
    	assertTrue( banco.estaDisponible(fechaBanco, "Asesoramiento Financiero"));
    }
    
    /* Tests Disponibilidad de Local Comercial */
	
    @Test
    public void testDisponibilidadLocalComercial()
    {
    	assertTrue( localComercial.estaDisponible(fecha));
    }
    
    @Test
    public void testDisponibilidadLocalComercialFalla()
    {
    	assertFalse( localComercial.estaDisponible(fechaCuandoEstaCerrado));
    }
    
    /*Tests Disponibilidad de CGP */
    
    @Test
    public void testDisponibilidadServiciosDeCGP()
    {
    	assertTrue(cGP.estaDisponible(fechaRentas,"Rentas"));
    	assertFalse( cGP.estaDisponible(fechaRentas,"Libreria"));
    }
    
    @Test
    public void testDisponibilidadDeCGP()
    {
    	assertTrue( cGP.estaDisponible(fechaRentas));
    }
    
    /* Test disponibilidad Parada de colectivo */
    
    @Test
    public void testDisponibilidadDe55()
    {
    	assertTrue( paradaDe55.estaDisponible(fecha));
    }
    
}
