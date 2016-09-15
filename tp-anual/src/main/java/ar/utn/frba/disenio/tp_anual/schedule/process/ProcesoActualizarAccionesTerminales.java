package ar.utn.frba.disenio.tp_anual.schedule.process;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.ResultadoProceso;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import ar.utn.frba.disenio.tp_anual.servicios.TipoSeleccionTerminal;

public class ProcesoActualizarAccionesTerminales extends ProcesoGeneral{
	private TipoSeleccionTerminal seleccion;
	private List<ObserverTerminal> listaAccionesAAgregar;
	private List<ObserverTerminal> listaAccionesAQuitar;
	
	public ProcesoActualizarAccionesTerminales(LocalDateTime fecha){
		listaAccionesAAgregar = new ArrayList<ObserverTerminal>();
		listaAccionesAQuitar = new ArrayList<ObserverTerminal>();
		this.setFecha(fecha);
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
	@Override
	public Integer elementosAfectados(){
		return seleccion.numeroDeTerminalesAfectadas();
	}
	@Override
	public void accion() throws Exception {
		seleccion.agregarAcciones(listaAccionesAAgregar);
		seleccion.quitarAcciones(listaAccionesAQuitar);	
	}
}
