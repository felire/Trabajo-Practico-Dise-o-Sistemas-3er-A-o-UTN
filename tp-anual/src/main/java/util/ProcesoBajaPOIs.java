package util;

import ar.utn.frba.disenio.tp_anual.gestion.*;

import java.util.List;
import ar.utn.frba.disenio.tp_anual.*;

public class ProcesoBajaPOIs {

	private RepoPOIS repo;
	private JsonTraduccion traductor;
	private String json;
	
	public ProcesoBajaPOIs(RepoPOIS repo, JsonTraduccion traductor, String json){
		this.repo = repo;
		this.traductor = traductor;
		this.json = json;
	}
	
	public List<JsonBaja> obtenerPOIs(){
		return this.traductor.traductorBajaPOIs(json);
	}
	
	public List<JsonBajaFecha> obtenerPOIsABorrar(){
		return traductor.traductorBaja(this.json);
	}
	
	public void execute(){
		this.obtenerPOIsABorrar().stream().forEach(poi -> this.repo.bajaPOI(this.repo.buscarPorID(poi.getId())));
	}
}
