package ar.utn.frba.disenio.tp_anual.gestion;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import util.ProcesoGeneral;
import util.ResultadoProceso;

public class GestionadorProcesos{
	
	private List<ResultadoProceso> listaResultados;
	private ScheduledExecutorService executor; 
	public GestionadorProcesos(){
		listaResultados = new ArrayList<ResultadoProceso>();
		executor = Executors.newSingleThreadScheduledExecutor();
	}
	
	public void addResultado(ResultadoProceso resultado){
		listaResultados.add(resultado);
	}
	
	public void agregarTarea(ProcesoGeneral proceso){
		proceso.setGestionadorProcesos(this);
		executor.schedule(proceso, ChronoUnit.SECONDS.between(proceso.getFecha(), LocalDateTime.now()), TimeUnit.SECONDS);
	}
}
