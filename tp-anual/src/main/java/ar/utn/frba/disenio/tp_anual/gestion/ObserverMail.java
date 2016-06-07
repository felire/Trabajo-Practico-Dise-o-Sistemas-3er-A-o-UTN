package ar.utn.frba.disenio.tp_anual.gestion;

import ar.utn.frba.disenio.tp_anual.externo.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.externo.ServicioMail;
import util.Busqueda;

public class ObserverMail implements ObserverTerminal{
	private ServicioMail servicioMail;
	public void notificar(Busqueda busqueda, double tiempoMaximo){
		if(busqueda.getTiempoDemorado() >= tiempoMaximo){
			this.avisarPorMail(busqueda.getTiempoDemorado());
		}
	}
	public void avisarPorMail(double tardanza){
		servicioMail.reportarTardanza(tardanza);
	}
	
}
