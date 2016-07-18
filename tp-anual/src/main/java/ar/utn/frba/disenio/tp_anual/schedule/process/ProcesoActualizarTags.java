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
	private GestionadorProcesos gestionadorDeProcesos;
	private LocalDateTime fecha;
	private AdapterActualizacionLocalComercial adapter;
	List<POI> localesAfectados;
	
	public LocalDateTime getFecha(){
		return fecha;
	}
	public ProcesoActualizarTags(LocalDateTime fecha,AdapterActualizacionLocalComercial adapter, GestionadorProcesos gestionador){
		this.fecha = fecha;
		this.adapter = adapter;
		this.gestionadorDeProcesos = gestionador;
	}
	
	
	@Override
	public void run() {
		System.out.println("ejecutando");
		
		try {
			this.accion();
		} catch (Exception e) {
			handleError();
		}
		
		ResultadoProceso resultado = new ResultadoProceso(localesAfectados.size(),fecha, estado);
		gestionadorDeProcesos.addResultado(resultado);//Aca hay que mandar el resultado cargado, volo o martin haganlo.
		
	}
		
	static void checkDeTags(Map<String, List<String>> mapa) throws TagsVaciosException{
		if(mapa.values().stream().anyMatch(listaTags -> listaTags.isEmpty())){
			throw new TagsVaciosException("Error: Se ingreso un Local Comercial sin palabras claves./n");
		}
		
	}
	@Override
	public void setGestionadorProcesos(GestionadorProcesos gestionador) {
		this.gestionadorDeProcesos = gestionador;
	}
	@Override
	public void accion() throws Exception {
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
		try{
			checkDeTags(mapa);
			localesAfectados.stream().forEach(local->local.actualizarTags(mapa.get(local.getNombre())));
		}
		catch(TagsVaciosException excepcion){
			System.out.println(excepcion);
		}
	}
	
}
