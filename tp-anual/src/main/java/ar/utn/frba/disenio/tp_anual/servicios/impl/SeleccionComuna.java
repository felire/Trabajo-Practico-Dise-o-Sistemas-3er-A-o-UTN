package ar.utn.frba.disenio.tp_anual.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Polygon;

import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import ar.utn.frba.disenio.tp_anual.servicios.TipoSeleccionTerminal;

public class SeleccionComuna implements TipoSeleccionTerminal{
	
	List<Polygon> comunasSeleccionadas;
	RepoTerminales repo;
	
	public SeleccionComuna(){
		comunasSeleccionadas = new ArrayList<Polygon>();
		repo=RepoTerminales.getInstance();
	}
	public void addComuna(Polygon comuna){
		comunasSeleccionadas.add(comuna);
	}
	public void removeComuna(Polygon comuna){
		comunasSeleccionadas.remove(comuna);
	}
	
	public void agregarAcciones( List<ObserverTerminal> acciones) {
		this.terminalesAfectadas().stream().forEach(terminal-> acciones.stream().
				forEach(accion->terminal.addObserver(accion)));
	}
	public void quitarAcciones(List<ObserverTerminal> acciones){
		this.terminalesAfectadas().stream().forEach(terminal-> acciones.stream().
				forEach(accion->terminal.deleteObserver(accion)));
	}
	public List<Terminal> terminalesFiltradas(Polygon comuna){
		return repo.getListaTerminales().stream().filter(terminal->terminal.getComuna().equals(comuna)).collect(Collectors.toList());
	}
	public List<Terminal> terminalesAfectadas(){
		List<Terminal> terminalesAfectadas = new ArrayList<Terminal>();
		comunasSeleccionadas.stream().
		forEach(comuna->terminalesAfectadas.addAll(this.terminalesFiltradas(comuna)));
		return terminalesAfectadas;
	}
	@Override
	public Integer numeroDeTerminalesAfectadas(){
		return this.terminalesAfectadas().size();
	}
	

}
