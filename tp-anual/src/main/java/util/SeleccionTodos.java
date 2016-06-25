package util;

import java.util.ArrayList;
import java.util.List;

import org.uqbar.geodds.Polygon;

import ar.utn.frba.disenio.tp_anual.externo.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.gestion.RepoTerminales;
import ar.utn.frba.disenio.tp_anual.gestion.Terminal;

public class SeleccionTodos implements TipoSeleccionTerminal{
	RepoTerminales repo;
	
	public SeleccionTodos(){
		repo=RepoTerminales.getInstance();
	}
	public void agregarAcciones(List<ObserverTerminal> acciones) {
		repo.getListaTerminales().stream().forEach(terminal->acciones.stream().
				forEach(accion->terminal.addObserver(accion)));
	}
	public void quitarAcciones(List<ObserverTerminal> acciones){
		repo.getListaTerminales().stream().forEach(terminal->acciones.stream().
				forEach(accion->terminal.deleteObserver(accion)));
	}
	
	@Override
	public Integer numeroDeTerminalesAfectadas() {
		return repo.getListaTerminales().size();
	}
	
}
