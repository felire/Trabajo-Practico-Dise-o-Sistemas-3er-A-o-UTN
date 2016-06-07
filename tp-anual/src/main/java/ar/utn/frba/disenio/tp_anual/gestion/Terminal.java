package ar.utn.frba.disenio.tp_anual.gestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.Timer;

import ar.utn.frba.disenio.tp_anual.externo.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.externo.ServicioMail;
import ar.utn.frba.disenio.tp_anual.poi.POI;
import util.Busqueda;
import util.ReportePorFecha;

public class Terminal {
	private String nombre;
	private BuscadorPOIs buscadorPOIS;
	private List<ObserverTerminal> listaObservers;
	private double tiempoMaximo;
	
	public Terminal(BuscadorPOIs buscadorPOIS, String nombre, double tiempoMaximo){
		this.buscadorPOIS=buscadorPOIS;
		this.nombre = nombre;
		this.tiempoMaximo = tiempoMaximo;
		this.listaObservers = new ArrayList<ObserverTerminal>();
	}	
	
	public String getNombre(){
		return nombre;
	}
	
	public void setTiempoMaximo(double tiempoMaximo){
		this.tiempoMaximo = tiempoMaximo;
	}
	
	public void addObserver(ObserverTerminal observer){
		this.listaObservers.add(observer);
	}
	
	public void deleteObserver(ObserverTerminal observer){
		this.listaObservers.remove(observer);
	}
    
	public List<POI> buscar(String palabraClave, String servicio){
		double inicio = System.currentTimeMillis();
		List<POI> buscados= buscadorPOIS.buscarPOIs(palabraClave,servicio);
		Busqueda busqueda = new Busqueda(buscados, palabraClave, servicio, (double) (System.currentTimeMillis() - inicio)/1000, this.getNombre());
		this.notificarObservers(busqueda);
		return buscados;
	}
	
	public void notificarObservers(Busqueda busqueda){
		listaObservers.stream().forEach(observer->observer.notificar(busqueda, this.tiempoMaximo));
	}
	
}
