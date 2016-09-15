package ar.utn.frba.disenio.tp_anual.servicios.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Polygon;

import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import ar.utn.frba.disenio.tp_anual.servicios.TipoSeleccionTerminal;

public class SeleccionComuna extends TipoSeleccionTerminal{
	
	List<Polygon> comunasSeleccionadas;
	
	public SeleccionComuna(){
		comunasSeleccionadas = new ArrayList<Polygon>();
	}
	public void addComuna(Polygon comuna){
		comunasSeleccionadas.add(comuna);
	}
	public void removeComuna(Polygon comuna){
		comunasSeleccionadas.remove(comuna);
	}
	public List<Terminal> terminalesFiltradas(Polygon comuna){
		return RepoTerminales.getInstance().getListaTerminales().stream().filter(terminal->terminal.getComuna().equals(comuna)).collect(Collectors.toList());
	}
	public List<Terminal> terminalesAfectadas(){
		List<Terminal> terminalesAfectadas = new ArrayList<Terminal>();
		comunasSeleccionadas.stream().
		forEach(comuna->terminalesAfectadas.addAll(this.terminalesFiltradas(comuna)));
		return terminalesAfectadas;
	}
	@Override
	public List<Terminal> terminalesSeleccionadas(){
		return this.terminalesAfectadas();
	}
	

}
