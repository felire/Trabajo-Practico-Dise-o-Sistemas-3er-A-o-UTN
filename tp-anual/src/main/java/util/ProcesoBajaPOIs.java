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
	private boolean errorCatcher=false;//agrego boolean para cachear errores
	
	public ProcesoBajaPOIs(RepoPOIS repo, JsonTraduccion traductor, String json){
		this.repo = repo;
		this.traductor = traductor;
		this.json = json;
	}
	
	public List<JsonBajaFecha> obtenerPOIsABorrar(){
		return traductor.traductorBaja(this.json);
	}
	
	@Override
	public void run(){
		this.obtenerPOIsABorrar()
			.stream()
			.forEach(poi -> this.repo
					.bajaPOI(this.repo
							.buscarPorID(poi.getId())));
		ResultadoProceso resultado = new ResultadoProceso(this.obtenerPOIsABorrar().size(),fecha,!errorCatcher);
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
