package util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Polygon;

import ar.utn.frba.disenio.tp_anual.externo.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.gestion.RepoTerminales;
import ar.utn.frba.disenio.tp_anual.gestion.Terminal;

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
		comunasSeleccionadas.stream().
		forEach(comuna->this.terminalesFiltradas(comuna).stream().
				forEach(terminal-> acciones.stream().
						forEach(accion->terminal.addObserver(accion))));
	}
	public void quitarAcciones(List<ObserverTerminal> acciones){
		comunasSeleccionadas.stream().
		forEach(comuna->this.terminalesFiltradas(comuna).stream().
				forEach(terminal-> acciones.stream().
						forEach(accion->terminal.deleteObserver(accion))));
	}
	public List<Terminal> terminalesFiltradas(Polygon comuna){
		return repo.getListaTerminales().stream().filter(terminal->terminal.getComuna().equals(comuna)).collect(Collectors.toList());
	}

	@Override
	public Integer numeroDeTerminalesAfectadas(){
		return comunasSeleccionadas.size();//esta mal, despues alguien arreglelo
	}
	

}
