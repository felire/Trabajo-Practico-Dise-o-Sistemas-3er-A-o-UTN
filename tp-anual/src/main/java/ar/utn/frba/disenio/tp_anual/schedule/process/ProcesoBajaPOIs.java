package ar.utn.frba.disenio.tp_anual.schedule.process;

import ar.utn.frba.disenio.tp_anual.json.JsonBajaFecha;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.ResultadoProceso;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import exception.POINoExisteException;

import java.time.LocalDateTime;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.*;
import ar.utn.frba.disenio.tp_anual.adapter.JsonTraduccion;

public class ProcesoBajaPOIs extends ProcesoGeneral{

	private GestionadorProcesos gestionadorDeProcesos;
	private JsonTraduccion traductor;
	private String json;
	private LocalDateTime fecha;
	private List<JsonBajaFecha> poisABorrar;
	
	public ProcesoBajaPOIs(JsonTraduccion traductor, String json){
		this.traductor = traductor;
		this.json = json;
	}
	
	public List<JsonBajaFecha> obtenerPOIsABorrar(){
		this.poisABorrar = traductor.traductorBaja(this.getJson());
		return poisABorrar;
	}
	
	public String getJson(){
		return json;
	}
	
	public void todosLosPOISExisten( List<JsonBajaFecha> poisABorrar) throws POINoExisteException{
		if(RepoPOIS.getInstance().getListaPOIS().containsAll(poisABorrar)){
			throw new POINoExisteException("Error: Uno o mas de los POIS a borrar no se encuentra en el repositorio");
		}
	}
	
	public void borradoDePOIs() throws POINoExisteException{
		
		todosLosPOISExisten(poisABorrar);	
		this.obtenerPOIsABorrar()
			.stream()
			.forEach(
					poi -> RepoPOIS.getInstance().bajaPOI(RepoPOIS.getInstance().buscarPorID(poi.getId())));
	
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
