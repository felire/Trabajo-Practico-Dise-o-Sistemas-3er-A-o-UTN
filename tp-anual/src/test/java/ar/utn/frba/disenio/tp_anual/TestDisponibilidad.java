package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Test;

public class TestDisponibilidad extends TestInit{
	
/* Tests Disponibilidad de Banco */
    
    @Test
    public void testDisponibilidadSucursalBanco()
    {
    	assertEquals(true, banco.estaDisponible(fechaBanco));
    }	
    
    @Test
    public void testDisponibilidadServiciosDeBanco()
    {
    	assertEquals(true, banco.estaDisponible(fechaBanco, "Asesoramiento Financiero"));
    }
    
/* Tests Disponibilidad de Local Comercial */
	
    @Test
    public void testDisponibilidadLocalComercial()
    {
    	assertEquals(true, localComercial.estaDisponible(fecha));
    }
    
    @Test
    public void testDisponibilidadLocalComercialFalla()
    {
    	assertEquals(false, localComercial.estaDisponible(fechaCuandoEstaCerrado));
    }
    
    /*Tests Disponibilidad de CGP */
    
    @Test
    public void testDisponibilidadServiciosDeCGP()
    {
    	assertEquals(true, cGP.estaDisponible(fechaRentas,"Rentas"));
    	assertEquals(false, cGP.estaDisponible(fechaRentas,"Libreria"));
    }
    
    @Test
    public void testDisponibilidadDeCGP()
    {
    	assertEquals(true, cGP.estaDisponible(fechaRentas));
    }
    
    /* Test disponibilidad Parada de colectivo */
    
    @Test
    public void testDisponibilidadDe55()
    {
    	assertEquals(true, paradaDe55.estaDisponible(fecha));
    }
    
}
