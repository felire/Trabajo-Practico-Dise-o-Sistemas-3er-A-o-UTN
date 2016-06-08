package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestBusqueda extends Init{
	
	@Test
	public void buscarLocalComercial()
	{
		//Busco por tag
		assertTrue((buscadorPOIS.buscarPOIs("papelera")).contains(localComercial));
		//Busco por nombre de rubro
		assertTrue((buscadorPOIS.buscarPOIs("Libreria")).contains(localComercial));
		//Busco parte de un tag
		assertTrue((buscadorPOIS.buscarPOIs("utile")).contains(localComercial));
	}

	@Test
	public void buscarParadaColectivo()
	{
		//Busco por tag
		assertTrue((buscadorPOIS.buscarPOIs("bondi")).contains(paradaDe55));
		//Busco por linea
		assertTrue((buscadorPOIS.buscarPOIs("55")).contains(paradaDe55));
	}
	
	@Test
	public void buscarBanco()
	{
		//Busco parte de un tag tag
		assertTrue((buscadorPOIS.buscarPOIs("prestamo")).contains(banco));
		//Busco por servicio
		assertTrue((buscadorPOIS.buscarPOIs("Asesoramiento")).contains(banco));
	}
	
	@Test
	public void buscarCGP()
	{
		//Busco por tag
		assertTrue((buscadorPOIS.buscarPOIs("agip")).contains(cGP));
		//Busco por servicio
		assertTrue((buscadorPOIS.buscarPOIs("Rentas")).contains(cGP));
	}
	
	@Test
	public void aasetteoID()
	{
		assertTrue((cGP.getID()==1));
		assertTrue((banco.getID()==2));
		assertTrue((localComercial.getID()==3));
		assertTrue((paradaDe55.getID()==4));
	}
	
	@Test
	public void busquedaYEliminacionDePOIPorID()
	{
		//Eliminacion por ID
		assertTrue((buscadorPOIS.buscarPOIs("agip")).contains(cGP));
		buscadorPOIS.bajaPOI(cGP);
		assertFalse((buscadorPOIS.buscarPOIs("agip")).contains(cGP));
		
	}
	
	
}
