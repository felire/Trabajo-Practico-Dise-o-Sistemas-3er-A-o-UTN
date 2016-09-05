package util.reportes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;


public class CreadorDeReportes {
	
	
	private List<Busqueda> busquedas;
	
	public CreadorDeReportes(){
		busquedas = new ArrayList<Busqueda>();
	}
	public void agregarBusqueda(Busqueda busqueda){
		busquedas.add(busqueda);
	}
	public List<ReporteParcialPorUsuario> busquedasParcialesPorTerminal(){
		List<ReporteParcialPorUsuario> reportesParcialesPorUsuario = new ArrayList<ReporteParcialPorUsuario>();
		this.terminalesSinRepetir()
		.stream()
		.forEach(usuario->reportesParcialesPorUsuario
		.add(new ReporteParcialPorUsuario(usuario,busquedas.stream()
		.filter(busqueda->busqueda.mismoUsuario(usuario))
		.map(busqueda->busqueda.cantidadResultados()).collect(Collectors.toList()))));
		return reportesParcialesPorUsuario;		
	}
	
	public Set<String> terminalesSinRepetir (){
		Set<String> terminalesSinRepetir = new HashSet<>();
		busquedas.stream().forEach(busqueda->terminalesSinRepetir.add(busqueda.getUsuario()));
		return terminalesSinRepetir;
	}
	
	public List<ReportePorUsuario> busquedasPorTerminal(){
		List<ReportePorUsuario> reportes = new ArrayList<ReportePorUsuario>();
		this.busquedasParcialesPorTerminal().stream().forEach(reporte -> reportes.add(new ReportePorUsuario(reporte.getUsuario(),reporte.totalResultados())));
		return reportes;
	}
	
	public List<ReportePorFecha> busquedasPorFecha(){
		List<ReportePorFecha> reportesPorFecha = new ArrayList<ReportePorFecha>();		
		this.fechasSinRepetir().stream().forEach(fecha->
			reportesPorFecha.add(new ReportePorFecha(fecha, (int) busquedas.stream().filter(busqueda->busqueda.mismaFecha(fecha)).count()))				
		);
		return reportesPorFecha;
	}
	
	public Set<LocalDate> fechasSinRepetir(){
		Set<LocalDate> fechasSinRepetir = new HashSet<>();
		busquedas.stream().forEach(busqueda->fechasSinRepetir.add(busqueda.getFecha()));
		return fechasSinRepetir;
	}
}
