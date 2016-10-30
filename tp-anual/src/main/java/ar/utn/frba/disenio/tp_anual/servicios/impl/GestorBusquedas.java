package ar.utn.frba.disenio.tp_anual.servicios.impl;



import ar.utn.frba.disenio.tp_anual.model.Busqueda;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import util.Timer;
import util.reportes.CreadorDeReportes;

import javax.persistence.*;

@Entity
@DiscriminatorValue(value= "busquedas")
public class GestorBusquedas extends ObserverTerminal{
	
	@Transient
	private Timer timer;
	
	@Transient
	private CreadorDeReportes creadorDeReportes;
	
	public GestorBusquedas(CreadorDeReportes creador){
		this.creadorDeReportes = creador;
		timer = new Timer();
	}
	public void notificar(Busqueda busqueda){
		busqueda.setDemora(timer.finalizar());
		creadorDeReportes.agregarBusqueda(busqueda);
	}
	public void preNotificar(){
			timer.iniciar();
	}
	  
}
