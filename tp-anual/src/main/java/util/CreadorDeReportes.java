package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CreadorDeReportes {
	
	public List<ReporteParcialPorUsuario> busquedasParcialesPorTerminal(List<Busqueda> busquedas){
		List<ReporteParcialPorUsuario> reportesParcialesPorUsuario = new ArrayList<ReporteParcialPorUsuario>();
		this.terminalesSinRepetir(busquedas)
		.stream()
		.forEach(usuario->reportesParcialesPorUsuario
		.add(new ReporteParcialPorUsuario(usuario,busquedas.stream()
		.filter(busqueda->busqueda.mismoUsuario(usuario))
		.map(busqueda->busqueda.cantidadResultados()).collect(Collectors.toList()))));
		return reportesParcialesPorUsuario;		
	}
	
	public Set<String> terminalesSinRepetir (List<Busqueda> busquedas){
		Set<String> terminalesSinRepetir = new HashSet<>();
		busquedas.stream().forEach(busqueda->terminalesSinRepetir.add(busqueda.getUsuario()));
		return terminalesSinRepetir;
	}
	
	public List<ReportePorUsuario> busquedasPorTerminal(List<Busqueda> busquedas){
		List<ReportePorUsuario> reportes = new ArrayList<ReportePorUsuario>();
		this.busquedasParcialesPorTerminal(busquedas).stream().forEach(reporte -> reportes.add(new ReportePorUsuario(reporte.getUsuario(),reporte.totalResultados())));
		return reportes;
	}
	
	public List<ReportePorFecha> busquedasPorFecha(List<Busqueda> busquedas){
		List<ReportePorFecha> reportesPorFecha = new ArrayList<ReportePorFecha>();		
		this.fechasSinRepetir(busquedas).stream().forEach(fecha->
			reportesPorFecha.add(new ReportePorFecha(fecha, (int) busquedas.stream().filter(busqueda->busqueda.mismaFecha(fecha)).count()))				
		);
		return reportesPorFecha;
	}
	
	public Set<LocalDate> fechasSinRepetir(List<Busqueda> busquedas){
		Set<LocalDate> fechasSinRepetir = new HashSet<>();
		busquedas.stream().forEach(busqueda->fechasSinRepetir.add(busqueda.getFecha()));
		return fechasSinRepetir;
	}
}
