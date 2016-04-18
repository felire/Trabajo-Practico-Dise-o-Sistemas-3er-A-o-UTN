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
	LocalTime localTime;
	LocalDate localDateCuandoEstaCerrado;
	LocalDateTime fechaCuandoEstaCerrado;
	LocalDateTime fecha;
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
	}
	
    @Test
    public void testDisponibilidadLocalComercial()
    {
    	assertEquals(true, localComercial.estaDisponible(fecha, ""));
    }
    
    @Test
    public void testDisponibilidadLocalComercialFalla(){
    	assertEquals(false, localComercial.estaDisponible(fechaCuandoEstaCerrado,""));
    }
}
