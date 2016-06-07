package util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Timer;

import ar.utn.frba.disenio.tp_anual.externo.InterfazTerminal;
import ar.utn.frba.disenio.tp_anual.externo.ServicioMail;
import ar.utn.frba.disenio.tp_anual.poi.POI;

public class MandarMailDecorator extends TerminalDecorator{
	private ServicioMail servicioMail;
	private Timer timer;
	public MandarMailDecorator(InterfazTerminal terminal, int tiempoMaximoEspera){
		super(terminal);
		this.timer=new Timer(tiempoMaximoEspera*1000,TardanzaBusqueda);//Le doy el tiempo en milisegundos y que hacer si se supera ese tiempo
		this.timer.setRepeats(false);
		
	}
	ActionListener TardanzaBusqueda = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
			avisarPorMail((float)timer.getDelay()/1000);
		}		
    };
    public void avisarPorMail(double tardanza){
		servicioMail.reportarTardanza(tardanza);
	}
    
    public List<POI> buscar(String palabraClave, String servicio){
    	timer.start();
    	List<POI> buscados = getTerminal().buscar(palabraClave, servicio);
    	timer.stop();
		timer.restart();
		return buscados;
    }
}
