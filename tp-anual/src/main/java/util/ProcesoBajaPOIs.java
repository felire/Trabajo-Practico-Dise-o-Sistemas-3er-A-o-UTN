package util;

import ar.utn.frba.disenio.tp_anual.gestion.*;
import ar.utn.frba.disenio.tp_anual.poi.POI;

import java.time.LocalDateTime;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.*;

public class ProcesoBajaPOIs extends ProcesoGeneral{

	private RepoPOIS repo;
	private GestionadorProcesos gestionadorDeProcesos;
	private JsonTraduccion traductor;
	private String json;
	private LocalDateTime fecha;
	private List<JsonBajaFecha> poisABorrar;
	
	public ProcesoBajaPOIs(RepoPOIS repo, JsonTraduccion traductor, String json){
		this.repo = repo;
		this.traductor = traductor;
		this.json = json;
	}
	
	public List<JsonBajaFecha> obtenerPOIsABorrar(){
		this.poisABorrar = traductor.traductorBaja(this);
		return poisABorrar;
	}
	
	public String getJson(){
		return json;
	}
	
	static void todosLosPOISExisten(RepoPOIS repo, List<JsonBajaFecha> poisABorrar) throws POINoExisteException{
		if(repo.getListaPOIS().containsAll(poisABorrar)){
			throw new POINoExisteException("Error: Uno o mas de los POIS a borrar no se encuentra en el repositorio");
		}
	}
	
	private void borradoDePOIs() throws POINoExisteException{
		
		todosLosPOISExisten(repo, poisABorrar);	
		this.obtenerPOIsABorrar()
			.stream()
			.forEach(
					poi -> this.repo.bajaPOI(this.repo.buscarPorID(poi.getId())));
	
	}
	
	@Override
	public void run(){
		
		try {
			this.accion();
		} catch (Exception e) {
			handleError();
		}
		
		ResultadoProceso resultado = new ResultadoProceso(this.obtenerPOIsABorrar().size(),fecha, estado);
		gestionadorDeProcesos.addResultado(resultado);//Aca hay que mandar el resultado cargado, volo o martin haganlo.
	}
	
	@Override
	public LocalDateTime getFecha(){
		return fecha;
	}
	
	@Override
	public void setGestionadorProcesos(GestionadorProcesos gestionador) {
		this.gestionadorDeProcesos = gestionador;
		
	}

	@Override
	public void accion() throws Exception {
		this.borradoDePOIs();
	}

}
