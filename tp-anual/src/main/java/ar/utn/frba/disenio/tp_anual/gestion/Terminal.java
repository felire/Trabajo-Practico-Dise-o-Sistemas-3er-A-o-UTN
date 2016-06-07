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

import ar.utn.frba.disenio.tp_anual.externo.ServicioMail;
import ar.utn.frba.disenio.tp_anual.poi.POI;
import util.Busqueda;
import util.ReportePorFecha;

public class Terminal {
	private String nombre;
	private BuscadorPOIs buscadorPOIS;
	private List<Busqueda> busquedas;
	private ServicioMail servicioMail;
	private Timer timer;
	
	public Terminal(int tiempoMaximoEspera, BuscadorPOIs buscadorPOIS, String nombre){
		this.buscadorPOIS=buscadorPOIS;
		this.timer=new Timer(tiempoMaximoEspera*1000,TardanzaBusqueda);//Le doy el tiempo en milisegundos y que hacer si se supera ese tiempo
		this.timer.setRepeats(false);
		this.nombre = nombre;
		//this.usuariosDesactivados=new HashSet<>();
	}	
	
	public List<Busqueda> getBusquedas(){
		return busquedas;
	}
	public String getNombre(){
		return nombre;
	}
	ActionListener TardanzaBusqueda = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
			avisarPorMail((float)timer.getDelay()/1000);
		}		
    };
    
    public void buscarPOIs(String palabraClave){
		this.aniadirBusqueda(palabraClave, null);
	}
    
	public void buscarPOIs(String palabraClave, String servicio){
		this.aniadirBusqueda(palabraClave, servicio);
	}
	public void aniadirBusqueda(String palabraClave, String servicio){
		timer.start();
		List<POI> listaPOIBusqueda = buscadorPOIS.buscarPOIs(palabraClave);
		timer.stop();
		Busqueda busqueda = new Busqueda(listaPOIBusqueda, palabraClave, servicio, (double)timer.getDelay()/1000);//Divido para devolver segundos
		busquedas.add(busqueda);
		timer.restart();
	}
	
	public void avisarPorMail(double tardanza){
		servicioMail.reportarTardanza(tardanza);
	}
	public Integer cantidadBusquedas(){
		return busquedas.size();
	}
	
	public List<ReportePorFecha> busquedasPorFecha(){
		Set<LocalDateTime> fechasSinRepetir = new HashSet<>(); // Guardamos las fechas en las que se busco algo
		List<ReportePorFecha> reportesPorFecha = new ArrayList<ReportePorFecha>();
		busquedas.stream().forEach(busqueda->fechasSinRepetir.add(busqueda.getFecha()));
		fechasSinRepetir.stream().forEach(fecha->
			//Aca generamos cada reporte, filtrando por fecha y viendo la cantidad que hay despues de filtrar
			// ya que pide solo la cantidad de busquedas y no la cantidad de resultados por busqueda
			reportesPorFecha.add(new ReportePorFecha(fecha, (int) busquedas.stream().filter(busqueda->busqueda.mismaFecha(fecha)).count()))
				
		);
		return reportesPorFecha;
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
