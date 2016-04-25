package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBusqueda extends TestInit{
	
	@Test
	public void buscarLocalComercial()
	{
		//Busco por tag
		assertEquals(true,(buscadorPOIS.filtrarPOIs("papelera")).contains(localComercial));
		//Busco por nombre de rubro
		assertEquals(true,(buscadorPOIS.filtrarPOIs("Libreria")).contains(localComercial));
		//Busco parte de un tag
		assertEquals(true,(buscadorPOIS.filtrarPOIs("utile")).contains(localComercial));
	}

	@Test
	public void buscarParadaColectivo()
	{
		//Busco por tag
		assertEquals(true,(buscadorPOIS.filtrarPOIs("bondi")).contains(paradaDe55));
		//Busco por linea
		assertEquals(true,(buscadorPOIS.filtrarPOIs("55")).contains(paradaDe55));
	}
	
	@Test
	public void buscarBanco()
	{
		//Busco parte de un tag tag
		assertEquals(true,(buscadorPOIS.filtrarPOIs("prestamo")).contains(banco));
		//Busco por servicio
		assertEquals(true,(buscadorPOIS.filtrarPOIs("Asesoramiento")).contains(banco));
	}
	@Test
	public void buscarCGP()
	{
		//Busco por tag
		assertEquals(true,(buscadorPOIS.filtrarPOIs("agip")).contains(cGP));
		//Busco por servicio
		assertEquals(true,(buscadorPOIS.filtrarPOIs("Rentas")).contains(cGP));
	}
}
