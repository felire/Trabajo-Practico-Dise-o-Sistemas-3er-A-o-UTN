package ar.utn.frba.disenio.tp_anual.gestion;

import ar.utn.frba.disenio.tp_anual.externo.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.externo.ServicioMail;
import util.Busqueda;
import util.Timer;

public class ObserverMail implements ObserverTerminal{
	private ServicioMail servicioMail;
	private Timer timer;
	private double tiempoMaximo;
	public ObserverMail(){
		timer = new Timer();
	}
	public void notificar(Busqueda busqueda){
		Double demora = timer.finalizar();
		if(demora >= tiempoMaximo){
			this.avisarPorMail(demora);
		}
	}
	public void preNotificar(){
		timer.iniciar();
	}
	public void avisarPorMail(double tardanza){
		servicioMail.reportarTardanza(tardanza);
	}
	public void setTiempoMaximo(double tiempo){
		this.tiempoMaximo = tiempo;
	}
	
}
