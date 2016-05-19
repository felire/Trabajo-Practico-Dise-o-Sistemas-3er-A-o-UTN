package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBusqueda extends TestInit{
	
	@Test
	public void buscarLocalComercial()
	{
		//Busco por tag
		assertEquals(true,(buscadorPOIS.buscarPOIs("papelera")).contains(localComercial));
		//Busco por nombre de rubro
		assertEquals(true,(buscadorPOIS.buscarPOIs("Libreria")).contains(localComercial));
		//Busco parte de un tag
		assertEquals(true,(buscadorPOIS.buscarPOIs("utile")).contains(localComercial));
	}

	@Test
	public void buscarParadaColectivo()
	{
		//Busco por tag
		assertEquals(true,(buscadorPOIS.buscarPOIs("bondi")).contains(paradaDe55));
		//Busco por linea
		assertEquals(true,(buscadorPOIS.buscarPOIs("55")).contains(paradaDe55));
	}
	
	@Test
	public void buscarBanco()
	{
		//Busco parte de un tag tag
		assertEquals(true,(buscadorPOIS.buscarPOIs("prestamo")).contains(banco));
		//Busco por servicio
		assertEquals(true,(buscadorPOIS.buscarPOIs("Asesoramiento")).contains(banco));
	}
	
	@Test
	public void buscarCGP()
	{
		//Busco por tag
		assertEquals(true,(buscadorPOIS.buscarPOIs("agip")).contains(cGP));
		//Busco por servicio
		assertEquals(true,(buscadorPOIS.buscarPOIs("Rentas")).contains(cGP));
	}
	
	@Test
	public void setteoID()
	{
		assertEquals(true,(cGP.getID()==1));
		assertEquals(true,(banco.getID()==2));
		assertEquals(true,(localComercial.getID()==3));
		assertEquals(true,(paradaDe55.getID()==4));
	}
	
	@Test
	public void busquedaYEliminacionDePOIPorID()
	{
		//Busqueda por ID
		assertEquals(true,(buscadorPOIS.buscarPorID(1)==cGP));
		//Eliminacion por ID
		assertEquals(true,(buscadorPOIS.buscarPOIs("agip")).contains(cGP));
		buscadorPOIS.bajaPOI(cGP);
		assertEquals(false,(buscadorPOIS.buscarPOIs("agip")).contains(cGP));
		//Dar de alta al POI viejo le da un nuevo ID
		buscadorPOIS.altaPOI(cGP);
		assertEquals(true,(cGP.getID()==5));
	}
	
	
}
