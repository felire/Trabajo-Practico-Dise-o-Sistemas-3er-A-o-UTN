package ar.utn.frba.disenio.tp_anual.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.servicios.TipoSeleccionTerminal;

public class SeleccionAdministrados extends TipoSeleccionTerminal{
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
	@Override
	public List<Terminal> terminalesSeleccionadas(){
		return terminalesSeleccionadas;
	}
}
