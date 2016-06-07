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

import ar.utn.frba.disenio.tp_anual.externo.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.externo.ServicioMail;
import ar.utn.frba.disenio.tp_anual.poi.POI;
import util.Busqueda;
import util.CreadorDeReportes;
import util.ReporteParcialPorUsuario;
import util.ReportePorFecha;
import util.ReportePorUsuario;

public class GestorBusquedas implements ObserverTerminal{
	
	private List<Busqueda> busquedas;
	private CreadorDeReportes creadorDeReportes;
	public GestorBusquedas(CreadorDeReportes creador){
		busquedas = new ArrayList<Busqueda>();
		this.creadorDeReportes = creador;
	}
	public void notificar(Busqueda busqueda, double tiempo){
		busquedas.add(busqueda);
	}
	public List<Busqueda> getBusquedas(){
		return busquedas;
	}
	public List<ReportePorFecha> busquedasPorFecha(){
		return this.creadorDeReportes.busquedasPorFecha(this.busquedas);
	}
	
	public List<ReporteParcialPorUsuario> busquedasParcialesPorTerminal(){ //Retornamos una lista con la cantidad de busquedas por separado de cada terminal o usuario
		return this.creadorDeReportes.busquedasParcialesPorTerminal(busquedas);
	}
	public List<ReportePorUsuario> busquedasPorTerminal(){
		return this.creadorDeReportes.busquedasPorTerminal(busquedas);
	}
	  
}
