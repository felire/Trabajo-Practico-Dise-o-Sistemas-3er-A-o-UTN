package ar.utn.frba.disenio.tp_anual.servicios;

import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;

public interface TipoSeleccionTerminal {
	public abstract void agregarAcciones(List<ObserverTerminal> acciones);
	public abstract void quitarAcciones(List<ObserverTerminal> acciones);
	public abstract Integer numeroDeTerminalesAfectadas();
}
