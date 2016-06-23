package util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public LocalDateTime getFecha(){
		return fecha;
	}
	public ProcesoActualizarTags(LocalDateTime fecha){
		repo = RepoPOIS.getInstance();
		POIsAActualizar = repo.getListaPOIS();
		this.fecha = fecha;
	}
	public void obtencionDeActualizaciones(){
		mapa=adapter.traducirArchivo();
	}
	/*public void execute(){
		
	}*/
	@Override
	public void run() {
		List<LocalComercial> listaLocales = new ArrayList<LocalComercial>();
		listaLocales = (List<LocalComercial>) POIsAActualizar.stream().filter(poi -> poi.getClass().equals(LocalComercial.class));
		listaLocales.stream().filter(local -> mapa.containsKey(local.getNombre())).forEach(local->local.actualizarTags(mapa.get(local.getNombre())));
		ResultadoProceso resultado = new ResultadoProceso();
		gestionadorDeProcesos.addResultado(resultado);//Aca hay que mandar el resultado cargado, volo o martin haganlo.
		
	}
	@Override
	public void setGestionadorProcesos(GestionadorProcesos gestionador) {
		this.gestionadorDeProcesos = gestionador;
		
	}
}
