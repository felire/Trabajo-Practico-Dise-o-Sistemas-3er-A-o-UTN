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

import ar.utn.frba.disenio.tp_anual.externo.InterfazTerminal;
import ar.utn.frba.disenio.tp_anual.externo.ServicioMail;
import ar.utn.frba.disenio.tp_anual.poi.POI;
import util.Busqueda;
import util.ReportePorFecha;

public class Terminal implements InterfazTerminal{
	private String nombre;
	private BuscadorPOIs buscadorPOIS;
	private List<Busqueda> busquedas;

	
	public Terminal(int tiempoMaximoEspera, BuscadorPOIs buscadorPOIS, String nombre){
		this.buscadorPOIS=buscadorPOIS;
		this.nombre = nombre;
	}	
	
	public List<Busqueda> getBusquedas(){
		return busquedas;
	}
	public String getNombre(){
		return nombre;
	}
	
    
    public List<POI> buscarPOIs(String palabraClave){
		return this.buscar(palabraClave, null);
	}
    
	public List<POI> buscarPOIs(String palabraClave, String servicio){
		return this.buscar(palabraClave, servicio);
	}
	public List<POI> buscar(String palabraClave, String servicio){
		return buscadorPOIS.buscarPOIs(palabraClave);
	}
	
	
	public Integer cantidadBusquedas(){
		return busquedas.size();
	}
	
	public long busquedasEnFecha(LocalDateTime fecha){
		return busquedas.stream().filter(busqueda-> busqueda.mismaFecha(fecha)).count();
	}
	public List<Integer> resultadosPorBusqueda(){
		return busquedas.stream().map(busqueda->busqueda.cantidadResultados()).collect(Collectors.toList());
	}
	
	public Integer totalResultadosTerminal(){
		return this.resultadosPorBusqueda().stream().mapToInt(integer -> integer.intValue()).sum();
	}
	
}
