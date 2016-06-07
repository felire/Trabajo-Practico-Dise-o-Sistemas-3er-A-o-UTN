package ar.utn.frba.disenio.tp_anual.externo;

import java.time.LocalDateTime;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.poi.POI;
import util.Busqueda;

public interface InterfazTerminal {
	public abstract List<POI> buscarPOIs(String palabraClave);
	public abstract List<POI> buscarPOIs(String palabraClave, String servicio);
	public abstract List<Busqueda> getBusquedas();
	public abstract String getNombre();
	public abstract List<POI> buscar(String palabraClave, String servicio);
	public abstract Integer cantidadBusquedas();
	public abstract long busquedasEnFecha(LocalDateTime fecha);
	public abstract List<Integer> resultadosPorBusqueda();
	public abstract Integer totalResultadosTerminal();
}
