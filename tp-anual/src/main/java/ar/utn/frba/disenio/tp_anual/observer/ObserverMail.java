package ar.utn.frba.disenio.tp_anual.observer;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;
import ar.utn.frba.disenio.tp_anual.servicios.ServicioMail;
import util.Timer;

import javax.persistence.*;


@Entity
@DiscriminatorValue(value="mail")
public class ObserverMail extends ObserverTerminal{
	
	@Transient
	private ServicioMail servicioMail;
	
	@Transient
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
		//servicioMail.reportarTardanza(tardanza);
	}
	public void setTiempoMaximo(double tiempo){
		this.tiempoMaximo = tiempo;
	}
	
}
