package ar.utn.frba.disenio.tp_anual.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.swing.Timer;

import org.uqbar.geodds.Polygon;

import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.servicios.ServicioMail;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorPOIs;
import util.reportes.ReportePorFecha;

public class Terminal {
	
	//private long id;
	private String nombre;
	private BuscadorPOIs buscadorPOIS;
	private List<ObserverTerminal> listaObservers;
	private Polygon comuna;
	
	public Terminal(BuscadorPOIs buscadorPOIS, String nombre, double tiempoMaximo){
		this.buscadorPOIS=buscadorPOIS;
		this.nombre = nombre;
		this.listaObservers = new ArrayList<ObserverTerminal>();
	}	
	
	public String getNombre(){
		return nombre;
	}
	
	public void addObserver(ObserverTerminal observer){
		this.listaObservers.add(observer);
	}
	
	public boolean tieneObserver(ObserverTerminal observer){
		return this.listaObservers.contains(observer);
	}
	
	public void deleteObserver(ObserverTerminal observer){
		this.listaObservers.remove(observer);
	}
    
	public List<POI> buscar(String palabraClave, String servicio){
		this.preNotificarObservers(); //Podriamos hacer que notifique solo a los que necesitan el pre
		List<POI> buscados= buscadorPOIS.buscarPOIs(palabraClave,servicio);
		Busqueda busqueda = new Busqueda(buscados, palabraClave, servicio, this.getNombre());
		this.notificarObservers(busqueda);
		return buscados;
	}
	
	public void notificarObservers(Busqueda busqueda){
		listaObservers.stream().forEach(observer->observer.notificar(busqueda));
	}
	public void preNotificarObservers(){
		listaObservers.stream().forEach(observer->observer.preNotificar());
	}
	public Polygon getComuna() {
		return comuna;
	}

	public List<ObserverTerminal> getListaObservers(){
		return listaObservers;
	}
	
	public void setComuna(Polygon comuna) {
		this.comuna = comuna;
	}
	
}
