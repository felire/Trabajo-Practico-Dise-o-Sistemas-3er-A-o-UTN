package ar.utn.frba.disenio.tp_anual.gestion;



import ar.utn.frba.disenio.tp_anual.externo.ObserverTerminal;
import util.Busqueda;
import util.CreadorDeReportes;
import util.Timer;

public class GestorBusquedas implements ObserverTerminal{
	private Timer timer;
	
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
