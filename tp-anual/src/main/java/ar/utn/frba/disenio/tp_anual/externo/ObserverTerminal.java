package ar.utn.frba.disenio.tp_anual.externo;

import util.Busqueda;

public interface ObserverTerminal {
	public abstract void notificar(Busqueda busqueda);
	public abstract void preNotificar();
}
