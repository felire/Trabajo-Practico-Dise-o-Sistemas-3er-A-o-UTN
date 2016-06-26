package util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ar.utn.frba.disenio.tp_anual.AdapterActualizacionLocalComercial;
import ar.utn.frba.disenio.tp_anual.gestion.BuscadorPOIs;
import ar.utn.frba.disenio.tp_anual.gestion.GestionadorProcesos;
import ar.utn.frba.disenio.tp_anual.gestion.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.poi.LocalComercial;
import ar.utn.frba.disenio.tp_anual.poi.POI;

public class ProcesoActualizarTags extends ProcesoGeneral{
	private GestionadorProcesos gestionadorDeProcesos;
	private RepoPOIS repo;
	private LocalDateTime fecha;
	private AdapterActualizacionLocalComercial adapter;
	private Map<String, List<String>> mapa = new HashMap<String, List<String>>();
	private List<POI> POIsAActualizar;
	private boolean errorCatcher=false;//agrego boolean para catchear errores
	
	public LocalDateTime getFecha(){
		return fecha;
	}
	public ProcesoActualizarTags(LocalDateTime fecha,AdapterActualizacionLocalComercial adapter, GestionadorProcesos gestionador){
		repo = RepoPOIS.getInstance();
		POIsAActualizar = repo.getListaPOIS();
		this.fecha = fecha;
		this.adapter = adapter;
		this.gestionadorDeProcesos = gestionador;
	}
	
	public void obtencionDeActualizaciones(){
		mapa=adapter.traducirArchivo();
	}
	
	
	@Override
	public void run() {
		System.out.println("ejecutando");
		this.obtencionDeActualizaciones();
		
		List<POI> listaLocales = new ArrayList<POI>();
		listaLocales =  POIsAActualizar
				.stream()
				.filter(poi -> poi
					.getClass()
					.equals(LocalComercial.class)).collect(Collectors.toList());
		List<POI> localesAfectados = listaLocales
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
			errorCatcher=true;
		}
		ResultadoProceso resultado = new ResultadoProceso(localesAfectados.size(),fecha,!errorCatcher);
		errorCatcher=false;
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
	
}
