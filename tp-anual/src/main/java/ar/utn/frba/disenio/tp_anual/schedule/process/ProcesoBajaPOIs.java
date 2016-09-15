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

	private JsonTraduccion traductor;
	private String json;
	private List<JsonBajaFecha> poisABorrar;
	
	public ProcesoBajaPOIs(JsonTraduccion traductor, String json, LocalDateTime fecha){
		this.setFecha(fecha);
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
	public Integer elementosAfectados(){
		return this.obtenerPOIsABorrar().size();
	}

	@Override
	public void accion() throws Exception {
		this.borradoDePOIs();
	}

}
