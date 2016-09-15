package ar.utn.frba.disenio.tp_anual.observer;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;

public interface ObserverTerminal {
	public abstract void notificar(Busqueda busqueda);
	public abstract void preNotificar();
}
