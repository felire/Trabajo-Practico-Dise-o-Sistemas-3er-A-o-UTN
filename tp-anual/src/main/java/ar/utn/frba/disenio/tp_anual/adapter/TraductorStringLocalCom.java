package ar.utn.frba.disenio.tp_anual.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class TraductorStringLocalCom implements TraductorGeneral{
	public Map<String, List<String>> traducirArchivo(String texto){
		Scanner scanner = new Scanner(texto);
		scanner.useDelimiter("\n");
		Map<String, List<String>> mapa = new HashMap<String, List<String>>();
		String nombreFantasia;
		String tags;
		String linea;
		while(scanner.hasNextLine()){
			linea = scanner.nextLine();
			String[] separador = linea.split(";");
			nombreFantasia = separador[0];
			if(separador.length == 1){ //Verifico si hub tags o no
				tags = "";
			}
			else{
				tags = separador[1];
			}			
			String[] arrayTags = tags.split(" ");
			List<String> listaTags = new ArrayList<String>(); 
			if(!tags.equals("")){ //Si no hay tags, le paso una lista vacia al mapa
				for(int i = 0; i < arrayTags.length; i++){ // El metodo split devuelve un array, por lo que me veo obligado a usar un for
					listaTags.add(arrayTags[i]);
				}
			}		
			mapa.putIfAbsent(nombreFantasia, listaTags);
		}
		return mapa;
	}
}
