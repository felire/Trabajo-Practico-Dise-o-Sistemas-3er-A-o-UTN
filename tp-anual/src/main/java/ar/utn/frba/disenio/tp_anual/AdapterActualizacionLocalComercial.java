package ar.utn.frba.disenio.tp_anual;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdapterActualizacionLocalComercial{
	private Scanner scanner;
	public AdapterActualizacionLocalComercial(String pathArchivo) throws FileNotFoundException{
			File file = new File(pathArchivo);
			this.scanner = new Scanner(file);
			scanner.useDelimiter("\n");
	}
	
	public Map<String, List<String>> traducirArchivo(){
		Map<String, List<String>> mapa = new HashMap<String, List<String>>();
		String nombreFantasia;
		String tags;
		String linea;
		while(scanner.hasNextLine()){
			linea = scanner.nextLine();
			String[] separador = linea.split(";");
			nombreFantasia = separador[0];
			tags = separador[1];
			String[] arrayTags = tags.split(" ");
			List<String> listaTags = new ArrayList<String>(); 
			for(int i = 0; i < arrayTags.length; i++){ // El metodo split devuelve un array, por lo que me veo obligado a usar un for
				listaTags.add(arrayTags[i]);
			}
			mapa.putIfAbsent(nombreFantasia, listaTags);
		}
		
		return mapa;
	}
}
