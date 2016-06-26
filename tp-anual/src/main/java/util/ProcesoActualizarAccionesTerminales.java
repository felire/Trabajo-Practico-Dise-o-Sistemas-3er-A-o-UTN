package util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.gestion.RepoTerminales;
import ar.utn.frba.disenio.tp_anual.externo.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.gestion.GestionadorProcesos;
import ar.utn.frba.disenio.tp_anual.gestion.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.gestion.Terminal;

public class ProcesoActualizarAccionesTerminales extends ProcesoGeneral{
	private GestionadorProcesos gestionadorDeProcesos;
	private LocalDateTime fecha;
	private TipoSeleccionTerminal seleccion;
	private List<ObserverTerminal> listaAccionesAAgregar;
	private List<ObserverTerminal> listaAccionesAQuitar;
	
	public ProcesoActualizarAccionesTerminales(LocalDateTime fecha){
		listaAccionesAAgregar = new ArrayList<ObserverTerminal>();
		listaAccionesAQuitar = new ArrayList<ObserverTerminal>();
		this.fecha = fecha;
	}
	
	public void addAccionAAgregar(ObserverTerminal accion){
		listaAccionesAAgregar.add(accion);
	}
	public void removeAccionAAgregar(ObserverTerminal accion){
		listaAccionesAAgregar.remove(accion);
	}
	public void addAccionAQuitar(ObserverTerminal accion){
		listaAccionesAQuitar.add(accion);
	}
	public void removeAccionAQuitar(ObserverTerminal accion){
		listaAccionesAQuitar.remove(accion);
	}
	public void agregarAcciones(){
		seleccion.agregarAcciones(listaAccionesAAgregar);
	}
	public void quitarAcciones(){
		seleccion.quitarAcciones(listaAccionesAQuitar);
	}

	@Override
	public void run() {
		
		try {
			this.accion();
		} catch (Exception e) {
			handleError();
		}
		
		ResultadoProceso resultado = new ResultadoProceso(seleccion.numeroDeTerminalesAfectadas(),fecha, estado);
		gestionadorDeProcesos.addResultado(resultado);//Aca hay que mandar el resultado cargado, volo o martin haganlo.
	}

	@Override
	public LocalDateTime getFecha() {
		return fecha;
	}

	@Override
	public void setGestionadorProcesos(GestionadorProcesos gestionador) {
		this.gestionadorDeProcesos = gestionador;
		
	}

	@Override
	public void accion() throws Exception {
		this.agregarAcciones();
		this.quitarAcciones();	
	}
}
