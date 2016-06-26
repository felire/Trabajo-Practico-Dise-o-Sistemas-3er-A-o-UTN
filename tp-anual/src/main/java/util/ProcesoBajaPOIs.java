package util;

import ar.utn.frba.disenio.tp_anual.gestion.*;

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
	private boolean errorCatcher=false;//agrego boolean para cachear errores
	
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
	
	public void informarError(){
		errorCatcher = true;
	}
	
	private void borradoDePOIs(){
		try{
			todosLosPOISExisten(repo, poisABorrar);	
			this.obtenerPOIsABorrar()
				.stream()
				.forEach(poi -> this.repo
					.bajaPOI(this.repo
						.buscarPorID(poi.getId())));
		}
		catch(POINoExisteException excepcion){
			System.out.println(excepcion);
			errorCatcher = true;
		}
	}
	
	@Override
	public void run(){
		this.borradoDePOIs();
		ResultadoProceso resultado = new ResultadoProceso(this.obtenerPOIsABorrar().size(),fecha,!errorCatcher);
		errorCatcher = false;
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
}
