package ar.utn.frba.disenio.tp_anual;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

public class testAdapterActualizacion {
	
	AdapterActualizacionLocalComercial adapter;
	File file ;
	Scanner scanner;
	Map<String, List<String>> mapa;
	@Before
	public void setUp() throws IOException{
		file = new File("TextoLocalComercial.txt");
		
		adapter = new AdapterActualizacionLocalComercial("TextoLocalComercial.txt");
		mapa = adapter.traducirArchivo();
	}
	@Test
	public void test() {
		assertEquals("tag1", mapa.get("nombre").get(0));
		assertEquals("tag2", mapa.get("nombre").get(1));
		assertEquals("tag3", mapa.get("nombre").get(2));
		assertEquals("tag4", mapa.get("nombre2").get(0));
		assertEquals("tag5", mapa.get("nombre2").get(1));
		assertEquals("tag6", mapa.get("nombre2").get(2));
		assertEquals("tag7", mapa.get("nombre3").get(0));
		assertEquals("tag8", mapa.get("nombre3").get(1));
		assertEquals("tag9", mapa.get("nombre3").get(2));
	}

}
