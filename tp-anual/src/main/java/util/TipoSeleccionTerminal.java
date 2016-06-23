package util;

import java.util.List;

import ar.utn.frba.disenio.tp_anual.externo.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.gestion.Terminal;

public interface TipoSeleccionTerminal {
	public abstract void agregarAcciones(List<ObserverTerminal> acciones);
	public abstract void quitarAcciones(List<ObserverTerminal> acciones);
}
