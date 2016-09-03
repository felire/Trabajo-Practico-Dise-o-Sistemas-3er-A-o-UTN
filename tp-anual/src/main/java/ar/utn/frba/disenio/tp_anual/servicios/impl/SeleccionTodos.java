package ar.utn.frba.disenio.tp_anual.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import util.Point;
import util.Polygon;

import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import ar.utn.frba.disenio.tp_anual.servicios.TipoSeleccionTerminal;

public class SeleccionTodos extends TipoSeleccionTerminal{

	@Override
	public List<Terminal> terminalesSeleccionadas(){
		return RepoTerminales.getInstance().getListaTerminales();
	}
	
}
