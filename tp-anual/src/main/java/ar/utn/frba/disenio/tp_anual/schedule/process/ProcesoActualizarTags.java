package ar.utn.frba.disenio.tp_anual.schedule.process;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ar.utn.frba.disenio.tp_anual.adapter.AdapterActualizacionLocalComercial;
import ar.utn.frba.disenio.tp_anual.model.LocalComercial;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.ResultadoProceso;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorPOIs;
import exception.TagsVaciosException;

public class ProcesoActualizarTags extends ProcesoGeneral{
	private AdapterActualizacionLocalComercial adapter;
	List<POI> localesAfectados;
	
	public ProcesoActualizarTags(LocalDateTime fecha,AdapterActualizacionLocalComercial adapter, GestionadorProcesos gestionador){
		this.setFecha(fecha);
		this.adapter = adapter;
	}
	
	static void checkDeTags(Map<String, List<String>> mapa) throws TagsVaciosException{
		if(mapa.values().stream().anyMatch(listaTags -> listaTags.isEmpty())){
			throw new TagsVaciosException("Error: Se ingreso un Local Comercial sin palabras claves.\n");
		}
		
	}
	@Override
	public Integer elementosAfectados(){
		return localesAfectados.size();
	}
	@Override
	public void accion() throws IOException, TagsVaciosException{
		Map<String, List<String>> mapa=adapter.traducirArchivo();
		
		List<POI> listaLocales = new ArrayList<POI>();
		listaLocales =  RepoPOIS.getInstance().getListaPOIS()
				.stream()
				.filter(poi -> poi
					.getClass()
					.equals(LocalComercial.class)).collect(Collectors.toList());
		localesAfectados = listaLocales
				.stream()
				.filter(local -> mapa
					.containsKey(local.getNombre()))
				.collect(Collectors.toList());
			checkDeTags(mapa);
			localesAfectados.stream().forEach(local->local.actualizarTags(mapa.get(local.getNombre())));
	}
	
}
