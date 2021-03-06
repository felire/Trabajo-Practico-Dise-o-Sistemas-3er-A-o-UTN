package ar.utn.frba.disenio.tp_anual.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.utn.frba.disenio.tp_anual.observer.ObserverMail;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorPOIs;
import ar.utn.frba.disenio.tp_anual.servicios.impl.GestorBusquedas;
import util.Polygon;
import util.reportes.CreadorDeReportes;

@Entity
public class Terminal {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String nombre;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "terminal_id")
	private List<ObserverTerminal> listaObservers;
	
	@ManyToOne(cascade = {CascadeType.REFRESH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
	@JoinColumn(name = "comuna_id")
	private Polygon comuna;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
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
		Busqueda busqueda = new Busqueda(buscados, palabraClave, servicio, this.nombre);
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
	
	public void setearComuna(String nombreComuna){
		this.comuna = RepoTerminales.getInstance().getComuna(nombreComuna);
	}
	
	
	public long getID() {
		return id;
	}
	public void setID(long id) {
		this.id = id;
	}
	
	public String getNombreComuna(){
		return this.comuna.getNombre();
	}
	
	public String getUrl(){
		return "/terminales/"+Long.toString(this.getID());
	}
	
	public Usuario getUsuario(){
		return usuario;
	}
	
	public String getUser(){
		return this.getUsuario().getUser();
	}
	
	public String getPass(){
		return this.getUsuario().getPass();
	}
	
	public void setUsuario(Usuario usuario){
		this.usuario = usuario;
	}
	
	public String getUrlNueva(){
		return "/terminales/nueva";
	}
	
	public Boolean getTieneMailActivo(){
		return this.getListaObservers().stream().anyMatch(accion-> accion.getClass().equals(ObserverMail.class));
	}
	
	public Boolean getTieneGestorBusquedas(){
		return this.getListaObservers().stream().anyMatch(accion-> accion.getClass().equals(GestorBusquedas.class));
	}
	
	public void actualizarObservers(List<ObserverTerminal> lista){
		this.listaObservers.clear();
		this.listaObservers = lista;
	}
	
	public Boolean tieneGestor(){
	return this.listaObservers.stream().anyMatch(observer -> observer.getClass().equals(GestorBusquedas.class));
	}
	
	public GestorBusquedas getGestor(){
		return (GestorBusquedas) this.listaObservers.stream().filter(observer -> observer.getClass().equals(GestorBusquedas.class)).findAny().get();
	}
}
