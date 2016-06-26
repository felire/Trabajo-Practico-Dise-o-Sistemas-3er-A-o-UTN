package ar.utn.frba.disenio.tp_anual.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.servicios.TipoSeleccionTerminal;

public class SeleccionAdministrados implements TipoSeleccionTerminal{
	List<Terminal> terminalesSeleccionadas;
	public SeleccionAdministrados(){
		terminalesSeleccionadas = new ArrayList<Terminal>();
	}
	public void addTerminal(Terminal terminal){
		terminalesSeleccionadas.add(terminal);
	}
	public void removeTerminal(Terminal terminal){
		terminalesSeleccionadas.remove(terminal);
	}
	public void agregarAcciones(List<ObserverTerminal> acciones) {
		terminalesSeleccionadas
			.stream()
			.forEach(terminal->acciones
					.stream()
					.forEach(accion->terminal
							.addObserver(accion)));
	}
	
	public void quitarAcciones(List<ObserverTerminal> acciones){
		terminalesSeleccionadas
			.stream()
			.forEach(terminal->acciones
					.stream()
					.forEach(accion->terminal
							.deleteObserver(accion)));
	}
	
	@Override
	public Integer numeroDeTerminalesAfectadas() {
		return terminalesSeleccionadas.size();
	}
}
