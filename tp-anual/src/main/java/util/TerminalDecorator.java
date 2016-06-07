package util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import ar.utn.frba.disenio.tp_anual.externo.InterfazTerminal;
import ar.utn.frba.disenio.tp_anual.poi.POI;

public abstract class TerminalDecorator implements InterfazTerminal{
	private InterfazTerminal terminal;
	public TerminalDecorator(InterfazTerminal terminal){
		this.terminal = terminal;
	}
	public List<Busqueda> getBusquedas(){
		return terminal.getBusquedas();
	}
	public InterfazTerminal getTerminal(){
		return terminal;
	}
	public String getNombre(){
		return terminal.getNombre();
	}
	public List<POI> buscarPOIs(String palabraClave){
		return terminal.buscar(palabraClave, null);
	}
    
	public List<POI> buscarPOIs(String palabraClave, String servicio){
		return terminal.buscar(palabraClave, servicio);
	}
	public abstract List<POI> buscar(String palabraClave, String servicio);
	
	public Integer cantidadBusquedas(){
		return terminal.cantidadBusquedas();
	}
	
	public long busquedasEnFecha(LocalDateTime fecha){
		return terminal.busquedasEnFecha(fecha);
	}
	public List<Integer> resultadosPorBusqueda(){
		return terminal.resultadosPorBusqueda();
	}
	
	public Integer totalResultadosTerminal(){
		return terminal.totalResultadosTerminal();
	}
	
}
