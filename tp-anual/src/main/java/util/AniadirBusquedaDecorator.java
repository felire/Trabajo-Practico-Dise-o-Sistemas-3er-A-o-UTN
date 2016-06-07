package util;

import java.util.List;

import ar.utn.frba.disenio.tp_anual.externo.InterfazTerminal;
import ar.utn.frba.disenio.tp_anual.poi.POI;

public class AniadirBusquedaDecorator extends TerminalDecorator{
	
	public AniadirBusquedaDecorator(InterfazTerminal terminal){
		super(terminal);
	}
	
	public List<POI> buscar(String palabraClave, String servicio){
		double inicio = System.currentTimeMillis();
		List<POI> buscados= getTerminal().buscar(palabraClave, servicio);
		Busqueda busqueda = new Busqueda(buscados, palabraClave, servicio, (double) (System.currentTimeMillis() - inicio)/1000);
		getTerminal().getBusquedas().add(busqueda);
		return buscados;
	}
}
