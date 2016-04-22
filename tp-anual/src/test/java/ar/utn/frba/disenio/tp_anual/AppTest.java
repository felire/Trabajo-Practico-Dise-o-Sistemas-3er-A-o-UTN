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

import junit.framework.Assert;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	/* Variables de Local Comercial */
	POI localComercial;
	Rubro rubro;
	LocalDate localDate;
	LocalDate localDateCuandoEstaCerrado;
	LocalTime localTime;
	LocalDateTime fechaCuandoEstaCerrado;
	LocalDateTime fecha;
	Point coordenadaLocalComercial;
	Set<DisponibilidadHoraria> disponibilidades;
	
	/* Variables de Banco */
	SucursalBanco banco;
	Servicio asesoramientoFinanciero;
	Servicio cajeroElectronico;
	LocalDate localDateBanco;
	LocalDate localDateCajero;
	LocalDateTime fechaBanco;
	LocalDateTime fechaCajero;
	Point coordenadaBanco;
	
	 
	/* Variables de CGP */
	CGP cGP;
	Servicio rentas;
	Servicio libreria;
	LocalDate localDateRentas;
	LocalDate localDateLibreria;
	LocalDateTime fechaRentas;
	LocalDateTime fechaLibreria;
	Point puntoEnLaComuna;
	Point puntoFueraDeLaComuna;
	Point coordenadaCGP;
	
	/* Variables de Programa Principal */
	BuscadorPOIS buscadorPOIS;
	List<POI> resultadosDeBusqueda;
	
	@Before
	public void init()
	{
		/* Setup de Local Comercial */
		FranjaHoraria franjaHoraria = new FranjaHoraria(LocalTime.of(10, 0),LocalTime.of(18, 0));
		DisponibilidadHoraria disponibilidad = new DisponibilidadHoraria(DayOfWeek.MONDAY, 
				DayOfWeek.FRIDAY, franjaHoraria);
		disponibilidades = new HashSet<>();
		disponibilidades.add(disponibilidad);
		rubro = new Rubro("Libreria", 500);
		coordenadaLocalComercial= new Point(0,0);
		localComercial = new LocalComercial("Local Comercial",rubro, disponibilidades, coordenadaLocalComercial);
		localDate = LocalDate.of(2016, 4, 12);
		fecha = localDate.atTime(15, 00);
		localDateCuandoEstaCerrado = LocalDate.of(2016, 4, 18);
		fechaCuandoEstaCerrado = localDateCuandoEstaCerrado.atTime(19, 00);
		
		/* Setup de Banco */
		coordenadaBanco = new Point(0,15);
		banco = new SucursalBanco(coordenadaBanco);
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
		banco.setNombre("Banco");
		
		/* Setup de poligono usado como comuna en CGP */
		Polygon polygon = new Polygon();
		Point punto1 = new Point(0,0);
		Point punto2 = new Point(0,100);
		Point punto3 = new Point(100,100);
		Point punto4 = new Point(100,0);
		polygon.add(punto1);
		polygon.add(punto2);
		polygon.add(punto3);
		polygon.add(punto4);
		
		/* Setup de CGP */
		coordenadaCGP = new Point(0,10);
		cGP = new CGP(polygon, coordenadaCGP);
		FranjaHoraria franjaHorariaRentas = new FranjaHoraria(LocalTime.of(9, 00),LocalTime.of(19, 00));
		DisponibilidadHoraria disponibilidadRentas = new DisponibilidadHoraria(DayOfWeek.MONDAY,
				DayOfWeek.SATURDAY,franjaHorariaRentas);
		FranjaHoraria franjaHorariaLibreria = new FranjaHoraria(LocalTime.of(11, 00),LocalTime.of(20, 00));
		DisponibilidadHoraria disponibilidadLibreria = new DisponibilidadHoraria(DayOfWeek.MONDAY,
				DayOfWeek.SUNDAY,franjaHorariaLibreria);
		rentas = new Servicio("Rentas",disponibilidadRentas);
		libreria = new Servicio("Libreria", disponibilidadLibreria);
		localDateRentas = LocalDate.of(2016, 4, 18);
		fechaRentas = localDateRentas.atTime(9, 01);
		localDateLibreria = LocalDate.of(2016, 4, 17);
		fechaLibreria = localDateLibreria.atTime(14, 00);
		cGP.addServicio(rentas);
		cGP.addServicio(libreria);
		puntoEnLaComuna = new Point(8,40);
		puntoFueraDeLaComuna = new Point(19,120);
		cGP.setNombre("CGP");
		
		/* Setup de Programa Principal*/
		buscadorPOIS = new BuscadorPOIS();
		buscadorPOIS.addPOI(cGP);
		buscadorPOIS.addPOI(banco);
		buscadorPOIS.addPOI(localComercial);
	
	}
	
	
	/* Tests de Local Comercial */
	@Test
	public void testPuntoCercanoLocalComercial(){
		assertEquals(true, localComercial.esCercano(new Point(0,1)));
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
    
    
    /* Tests de Banco */
    
    @Test
    public void testDisponibilidadSucursalBanco()
    {
    	assertEquals(true, banco.estaDisponible(fechaBanco,""));
    }	
    
    @Test
    public void testDisponibilidadServiciosDeBanco()
    {
    	assertEquals(true, banco.estaDisponible(fechaBanco, "Asesoramiento Financiero"));
    	assertEquals(false, banco.estaDisponible(fechaBanco, "Cajero Electronico"));
    	assertEquals(true, banco.estaDisponible(fechaCajero, "Cajero Electronico"));
    }
    
    
    /* Tests de CGP */
    
    @Test
    public void testPuntosCercanosACGP()
    {
    	assertEquals(true, cGP.esCercano(puntoEnLaComuna));
    	assertEquals(false, cGP.esCercano(puntoFueraDeLaComuna));
    }
    
    @Test
    public void testDisponibilidadServiciosDeCGP()
    {
    	assertEquals(true, cGP.estaDisponible(fechaRentas,"Rentas"));
    	assertEquals(false, cGP.estaDisponible(fechaRentas,"Libreria"));
    	assertEquals(true, cGP.estaDisponible(fechaLibreria,"Libreria"));
    }
    
    @Test
    public void testDisponibilidadDeCGP()
    {
    	assertEquals(true, cGP.estaDisponible(fechaRentas,""));
    	assertEquals(true, cGP.estaDisponible(fechaLibreria,""));
    }
    
    @Test
    public void testBusquedasPorProgramaPrincipal()
    {
    	/* Busquedas por servicio */
    	resultadosDeBusqueda = buscadorPOIS.filtrarPOIs("Lib");
    	assertEquals(true, resultadosDeBusqueda.contains(cGP));
    	
    	resultadosDeBusqueda = buscadorPOIS.filtrarPOIs("Caj");
    	assertEquals(true, resultadosDeBusqueda.contains(banco));
    	
    	/* Busqueda por nombre */
    	resultadosDeBusqueda = buscadorPOIS.filtrarPOIs("Local");
    	assertEquals(true, resultadosDeBusqueda.contains(localComercial));
    	
    	/* Busqueda por nombre de un POI deleteado */
    	buscadorPOIS.deletePOI(localComercial);
    	resultadosDeBusqueda = buscadorPOIS.filtrarPOIs("Local");
    	assertEquals(false, resultadosDeBusqueda.contains(localComercial));
    	
    	/* Busqueda por servicio de un servicio deleteado */
    	banco.deleteServicio(cajeroElectronico);
    	resultadosDeBusqueda = buscadorPOIS.filtrarPOIs("Caj");
    	assertEquals(false, resultadosDeBusqueda.contains(banco));
    }
}
