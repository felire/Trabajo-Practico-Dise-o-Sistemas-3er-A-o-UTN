package ar.utn.frba.disenio.tp_anual.adapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.google.common.io.Files;

public class AdapterActualizacionLocalComercial{
	private TraductorGeneral traductor;
	private String pathArchivo;
	public void setTraductor(TraductorGeneral traductor){
		this.traductor = traductor;
	}
	public void setPathArchivo(String path){
		this.pathArchivo = path;
	}
	public Map<String, List<String>> traducirArchivo() throws IOException{
		File file = new File(pathArchivo);
		String textoArchivo = this.archivoATexto(file);
		return this.traductor.traducirArchivo(textoArchivo);
	}
	public String archivoATexto(File file) throws IOException{
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String linea;
		String contenido = "";
		while((linea=br.readLine()) != null){
			contenido = contenido + "\n" + linea;
		}
		return contenido;
	}
}
