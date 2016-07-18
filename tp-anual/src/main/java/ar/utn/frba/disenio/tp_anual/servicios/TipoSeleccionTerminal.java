package ar.utn.frba.disenio.tp_anual.servicios;

import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;

public abstract class TipoSeleccionTerminal {
	public void agregarAcciones(List<ObserverTerminal> acciones) {
		this.terminalesSeleccionadas()
			.stream()
			.forEach(terminal->acciones
					.stream()
					.forEach(accion->terminal
							.addObserver(accion)));
	}
	
	public void quitarAcciones(List<ObserverTerminal> acciones){
		this.terminalesSeleccionadas()
			.stream()
			.forEach(terminal->acciones
					.stream()
					.forEach(accion->terminal
							.deleteObserver(accion)));
	}
	public Integer numeroDeTerminalesAfectadas(){
		return this.terminalesSeleccionadas().size();
	}
	public abstract List<Terminal> terminalesSeleccionadas();
}
