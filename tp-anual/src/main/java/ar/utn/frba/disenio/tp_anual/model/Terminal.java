package ar.utn.frba.disenio.tp_anual.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.bson.types.ObjectId;

import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;

import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorPOIs;
import util.Polygon;

@Entity
@org.mongodb.morphia.annotations.Entity
public class Terminal {
	
	@Id
	@GeneratedValue
	@org.mongodb.morphia.annotations.Id
	private long id;
	
	private String nombre;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "terminal_id")
	private List<ObserverTerminal> listaObservers;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "comuna_id")
	private Polygon comuna;
	
	@SuppressWarnings("unused")
	private Terminal(){};
	
	public Terminal(String nombre, double tiempoMaximo){
		this.nombre = nombre;
		this.listaObservers = new ArrayList<ObserverTerminal>();
	}	
	
	public void setNombre(String nombre){
		this.nombre = nombre;
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
		List<POI> buscados= BuscadorPOIs.getInstance().buscarPOIs(palabraClave,servicio);
		Busqueda busqueda = new Busqueda(buscados, palabraClave, servicio, this);
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
	
	public long getID() {
		return id;
	}
	public void setID(long id) {
		this.id = id;
	}
}
