package ar.utn.frba.disenio.tp_anual.gestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.swing.Timer;

import ar.utn.frba.disenio.tp_anual.externo.ServicioMail;
import ar.utn.frba.disenio.tp_anual.poi.POI;
import util.Busqueda;
import util.ReporteParcialPorUsuario;
import util.ReportePorFecha;
import util.ReportePorUsuario;

public class GestorBusquedas {
	
	private List<Terminal> terminales;
	
	public GestorBusquedas(){
		terminales = new ArrayList<Terminal>();
	}
	
	public void addTerminal(Terminal terminal){
		terminales.add(terminal);
	}
	public void deleteTerminal(Terminal terminal){
		terminales.remove(terminal);
	}
	public List<ReportePorFecha> busquedasPorFecha(){
		Set<LocalDateTime> fechasSinRepetir = new HashSet<>(); // Guardamos las fechas en las que se busco algo
		List<Busqueda> totalBusquedas = new ArrayList<Busqueda>();
		terminales.stream().forEach(terminal->totalBusquedas.addAll(terminal.getBusquedas()));
		List<ReportePorFecha> reportesPorFecha = new ArrayList<ReportePorFecha>();
		totalBusquedas.stream().forEach(busqueda->fechasSinRepetir.add(busqueda.getFecha()));
		
		fechasSinRepetir.stream().forEach(fecha->
			//Aca generamos cada reporte, filtrando por fecha y viendo la cantidad que hay despues de filtrar
			// ya que pide solo la cantidad de busquedas y no la cantidad de resultados por busqueda
			reportesPorFecha.add(new ReportePorFecha(fecha, (int) terminales.stream().map(terminal->terminal.busquedasEnFecha(fecha)).count()))
				
		);
		return reportesPorFecha;
	}
	
	public List<ReporteParcialPorUsuario> busquedasParcialesPorTerminal(){ //Retornamos una lista con la cantidad de busquedas por separado de cada terminal o usuario
		List<ReporteParcialPorUsuario> reportes = new ArrayList<ReporteParcialPorUsuario>();
		terminales.stream().forEach(terminal-> reportes.add(new ReporteParcialPorUsuario(terminal.getNombre(), terminal.resultadosPorBusqueda())));
		return reportes;
	}
	public List<ReportePorUsuario> busquedasPorTerminal(){
		List<ReportePorUsuario> reportes = new ArrayList<ReportePorUsuario>();
		terminales.stream().forEach(terminal->reportes.add(new ReportePorUsuario(terminal.getNombre(), terminal.totalResultadosTerminal())));
		return reportes;
	}
	  
}
