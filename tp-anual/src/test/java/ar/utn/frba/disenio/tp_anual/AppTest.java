package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
	POI localComercial;
	LocalDate localDate;
	LocalDate localDateCuandoEstaCerrado;
	LocalTime localTime;
	LocalDateTime fechaCuandoEstaCerrado;
	LocalDateTime fecha;
	
	SucursalBanco banco;
	Servicio asesoramientoFinanciero;
	Servicio cajeroElectronico;
	LocalDate localDateBanco;
	LocalDate localDateCajero;
	LocalDateTime fechaBanco;
	LocalDateTime fechaCajero;

	@Before
	public void init()
	{
		FranjaHoraria franjaHoraria = new FranjaHoraria(LocalTime.of(10, 0),LocalTime.of(18, 0));
		DisponibilidadHoraria disponibilidad = new DisponibilidadHoraria(DayOfWeek.MONDAY, 
				DayOfWeek.FRIDAY, franjaHoraria);
		localComercial = new LocalComercial(500, disponibilidad);
		localDate = LocalDate.of(2016, 4, 12);
		fecha = localDate.atTime(15, 00);
		localDateCuandoEstaCerrado = LocalDate.of(2016, 4, 18);
		fechaCuandoEstaCerrado = localDateCuandoEstaCerrado.atTime(19, 00);
		
		banco = new SucursalBanco();
		DisponibilidadHoraria disponibilidadAsesor = new DisponibilidadHoraria(DayOfWeek.MONDAY,
				DayOfWeek.WEDNESDAY,franjaHoraria);
		asesoramientoFinanciero = new Servicio("Asesoramiento Financiero",disponibilidadAsesor);
		DisponibilidadHoraria disponibilidadCajero = new DisponibilidadHoraria(DayOfWeek.TUESDAY,
				DayOfWeek.THURSDAY,franjaHoraria);
		cajeroElectronico = new Servicio("Cajero Electronico",disponibilidadCajero);
		localDateBanco = LocalDate.of(2016, 4, 18);
		fechaBanco = localDateBanco.atTime(13, 00);
		localDateCajero = LocalDate.of(2016, 4, 20);
		fechaCajero = localDateCajero.atTime(14, 00);
		banco.addServicio(asesoramientoFinanciero);
		banco.addServicio(cajeroElectronico);
			
	}
	
    @Test
    public void testDisponibilidadLocalComercial()
    {
    	assertEquals(true, localComercial.estaDisponible(fecha, ""));
    }
    
    @Test
    public void testDisponibilidadLocalComercialFalla()
    {
    	assertEquals(false, localComercial.estaDisponible(fechaCuandoEstaCerrado,""));
    }
    
    @Test
    public void testDisponibilidadSucursalBanco()
    {
    	assertEquals(true, banco.estaDisponible(fechaBanco,""));
    }	
    
    @Test
    public void testDisponibilidadServiciosEspecificosDeBanco()
    {
    	assertEquals(true, banco.estaDisponible(fechaBanco, "Asesoramiento Financiero"));
    	assertEquals(false, banco.estaDisponible(fechaBanco, "Cajero Electronico"));
    	assertEquals(true, banco.estaDisponible(fechaCajero, "Cajero Electronico"));
    }
}
