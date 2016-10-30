package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import org.junit.FixMethodOrder;

import ar.utn.frba.disenio.tp_anual.model.CGP;
import ar.utn.frba.disenio.tp_anual.model.LocalComercial;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.ParadaDeColectivo;
import ar.utn.frba.disenio.tp_anual.model.Rubro;
import ar.utn.frba.disenio.tp_anual.model.Servicio;
import ar.utn.frba.disenio.tp_anual.model.SucursalBanco;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import util.DisponibilidadHoraria;
import util.FranjaHoraria;
import util.Point;
import util.Polygon;

@RunWith(DescriptionSorterRunner.class)
public class Init {
	/* Variables de Local Comercial */
	public LocalComercial localComercial;
	Rubro rubro;
	LocalDate localDate;
	LocalDate localDateCuandoEstaCerrado;
	LocalTime localTime;
	LocalDateTime fechaCuandoEstaCerrado;
	LocalDateTime fecha;
	Point coordenadaLocalComercial;
	Set<DisponibilidadHoraria> disponibilidadesLocalComercial;
		
	/* Variables de Banco */
	protected SucursalBanco banco;
	Servicio asesoramientoFinanciero;
	Servicio cajeroElectronico; //Falta instanciar este con su set de disponibilidades etc
	LocalDate localDateBanco;
	LocalDate localDateCajero;
	LocalDateTime fechaBanco;
	LocalDateTime fechaCajero;
	Point coordenadaBanco;
	Set<DisponibilidadHoraria> disponibilidadesAsesorFinanciero;
	
	 
	/* Variables de CGP */
	protected CGP cGP;
	Servicio rentas;
	Servicio libreria;//Falta instanciar este con su set de disponibilidades etc
	LocalDate localDateRentas;
	LocalDate localDateLibreria;
	LocalDateTime fechaRentas;
	LocalDateTime fechaLibreria;
	Point puntoEnLaComuna;
	Point puntoFueraDeLaComuna;
	Point coordenadaCGP;
	Set<DisponibilidadHoraria> disponibilidadesRentas;
	
	/*Variables de Parada de Colectivo */
	
	ParadaDeColectivo paradaDe55;
	ParadaDeColectivo paradaDe144;
	ParadaDeColectivo otraParadaDe55;
	Point posicionDel55;
	Point posicionDel144;
	Point posicionDelOtro55;
	
	/* Variables de Programa Principal */
	RepoPOIS buscadorPOIS;
	List<POI> resultadosDeBusqueda;
	
	@Before
	public void t01_init()
	{
		/* Setup de Local Comercial */
		ArrayList<DayOfWeek> dias = new ArrayList<DayOfWeek>();
		dias.add(DayOfWeek.MONDAY);
		dias.add(DayOfWeek.TUESDAY);
		dias.add(DayOfWeek.WEDNESDAY);
		dias.add(DayOfWeek.THURSDAY);
		dias.add(DayOfWeek.FRIDAY);
		ArrayList<FranjaHoraria> franjaLocalComercial=new ArrayList<FranjaHoraria>();
		franjaLocalComercial.add(new FranjaHoraria(LocalTime.of(8,0),LocalTime.of(20,0)));
		DisponibilidadHoraria disponibilidadLocalComercial = new DisponibilidadHoraria(franjaLocalComercial, dias);
		disponibilidadesLocalComercial = new HashSet<>();
		disponibilidadesLocalComercial.add(disponibilidadLocalComercial);
		rubro = new Rubro("Libreria", new BigDecimal(0.5));
		coordenadaLocalComercial= new Point(-34.804824,-58.449473);
		localComercial = new LocalComercial("Local Comercial",rubro, disponibilidadesLocalComercial, coordenadaLocalComercial);
		localDate = LocalDate.of(2016, 4, 12);
		fecha = localDate.atTime(15, 00);
		localDateCuandoEstaCerrado = LocalDate.of(2016, 4, 18);
		fechaCuandoEstaCerrado = localDateCuandoEstaCerrado.atTime(21, 00);
		localComercial.addTag("utiles");
		localComercial.addTag("papelera");
		
		/* Setup de Banco */
		SucursalBanco.setHorarioBancario();
		coordenadaBanco = new Point(-34.604056, -58.411226);
		banco = new SucursalBanco("Banco UTN",coordenadaBanco);
		banco.addTag("prestamos");
		
		/* Setup de Servicios del Banco */
		ArrayList<DayOfWeek> diasAsesor = new ArrayList<DayOfWeek>();
		diasAsesor.add(DayOfWeek.MONDAY);
		diasAsesor.add(DayOfWeek.TUESDAY);
		diasAsesor.add(DayOfWeek.WEDNESDAY);
		diasAsesor.add(DayOfWeek.THURSDAY);
		diasAsesor.add(DayOfWeek.FRIDAY);
		ArrayList<FranjaHoraria> franjaHorariaAsesor=new ArrayList<FranjaHoraria>();
		franjaHorariaAsesor.add(new FranjaHoraria(LocalTime.of(12, 0),LocalTime.of(15, 0)));
		DisponibilidadHoraria disponibilidadAsesorFinanciero = new DisponibilidadHoraria(franjaHorariaAsesor, diasAsesor);
		disponibilidadesAsesorFinanciero = new HashSet<>();
		disponibilidadesAsesorFinanciero.add(disponibilidadAsesorFinanciero);
		asesoramientoFinanciero = new Servicio("Asesoramiento Financiero",disponibilidadesAsesorFinanciero);
		/* Fin Setup de servicios del banco */
			
		banco.addServicio(asesoramientoFinanciero);
		localDateBanco = LocalDate.of(2016, 4, 18);
		fechaBanco = localDateBanco.atTime(13, 00);
		localDateCajero = LocalDate.of(2016, 4, 20);
		fechaCajero = localDateCajero.atTime(14, 00);
		//banco.addServicio(cajeroElectronico);
		
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
		
		/*Setup Puntos a testear en la comuna del CGP */		
		puntoEnLaComuna = new Point(8,40);
		puntoFueraDeLaComuna = new Point(19,120);
		
		/* Setup de CGP */
		coordenadaCGP = new Point(0,10);
		cGP = new CGP("CGP1",polygon, coordenadaCGP);
		cGP.addTag("agip");
		
		/* Setup de Servicios del CGP */
		ArrayList<DayOfWeek> diasRentas = new ArrayList<DayOfWeek>();
		diasRentas.add(DayOfWeek.MONDAY);
		diasRentas.add(DayOfWeek.TUESDAY);
		diasRentas.add(DayOfWeek.WEDNESDAY);
		diasRentas.add(DayOfWeek.THURSDAY);
		diasRentas.add(DayOfWeek.FRIDAY);
		ArrayList<FranjaHoraria> franjaHorariaRentas=new ArrayList<FranjaHoraria>();
		franjaHorariaRentas.add(new FranjaHoraria(LocalTime.of(9, 0),LocalTime.of(15, 0)));
		DisponibilidadHoraria disponibilidadRentas = new DisponibilidadHoraria(franjaHorariaRentas, diasRentas);
		disponibilidadesRentas = new HashSet<>();
		disponibilidadesRentas.add(disponibilidadRentas);
		rentas = new Servicio("Rentas",disponibilidadesRentas);
		/* Fin Setup de servicios del CGP */
		
		localDateRentas = LocalDate.of(2016, 4, 18);
		fechaRentas = localDateRentas.atTime(9, 01);
		localDateLibreria = LocalDate.of(2016, 4, 17);
		fechaLibreria = localDateLibreria.atTime(14, 00);
		cGP.addServicio(rentas);
		
		/*Setup de Parada de Colectivo*/
		posicionDel55 = new Point(-34.597819, -58.420451);
		paradaDe55 = new ParadaDeColectivo("55", posicionDel55);
		paradaDe55.addTag("bondi");
		
		/* Setup de Buscador POIs*/
		/*buscadorPOIS = RepoPOIS.getInstance();
		buscadorPOIS.altaPOI(cGP);
		buscadorPOIS.altaPOI(banco);
		buscadorPOIS.altaPOI(localComercial);
		buscadorPOIS.altaPOI(paradaDe55);*/
	
	}
	@Test
	public void inicie(){
		assertTrue(true);
	}
}
