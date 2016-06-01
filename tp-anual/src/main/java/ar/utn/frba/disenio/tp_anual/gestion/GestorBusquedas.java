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
	
	private BuscadorPOIs buscadorPOIS;
	private List<Busqueda> busquedas;	
	private ServicioMail servicioMail;
	private Timer timer;
	private Set<String> usuariosDesactivados; //Agrego lista de usuarios desactivados
	
	ActionListener TardanzaBusqueda = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
			avisarPorMail((float)timer.getDelay()/1000);
		}		
    };
	
	public GestorBusquedas(int tiempoMaximoEspera, BuscadorPOIs buscadorPOIS){
		this.buscadorPOIS=buscadorPOIS;
		this.timer=new Timer(tiempoMaximoEspera*1000,TardanzaBusqueda);//Le doy el tiempo en milisegundos y que hacer si se supera ese tiempo
		this.timer.setRepeats(false);
		this.usuariosDesactivados=new HashSet<>();
	}
	
	public void desactivarAccionesDeUsuario(String usuario){
		if(!usuariosDesactivados.contains(usuario)){
			usuariosDesactivados.add(usuario);
		}
	}
	
	public void reactivarAccionesDeUsuario(String usuario){
		if(usuariosDesactivados.contains(usuario)){
			usuariosDesactivados.remove(usuario);
		}
	}
	
	public void modificarTiempoMaximo(int tiempoMaximo){
		this.timer.setDelay(tiempoMaximo*1000);
	}
	
	public void avisarPorMail(double tardanza){
		servicioMail.reportarTardanza(tardanza);
	}
	
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
	
	//Agrego este metodo porque la linea que genera los reportes por usuario ya era demasiado dificil de leer
	private Set<String> filtrarUsuariosDesactivados(Set<String> usuariosSinRepetir){
		return usuariosSinRepetir.stream().filter(usuario->!usuariosDesactivados.contains(usuario)).collect(Collectors.toSet());
	}
	
	//Las parciales podria ser una lista, con el resultado de parciales de cada usuario
	public List<Integer> busquedasParcialesPorUsuario(String usuario){ //Retornamos una lista con la cantidad de busquedas por separado de cada terminal o usuario
		return busquedas.stream().filter(busqueda -> busqueda.mismoUsuario(usuario)).map(busqueda->busqueda.cantidadResultados()).collect(Collectors.toList());
	}
	
	
	//Esta es otra solucion a busquedasParcialesPorUsuario, hay que ver si quedarse con la de arriba o con esta, yo me inclino por esta de aca abajo.
	public List<ReporteParcialPorUsuario> busquedasParcialesPorUsuario(){
		Set<String> usuariosSinRepetir = new HashSet<>();
		List<ReporteParcialPorUsuario> reportesParcialesPorUsuario = new ArrayList<ReporteParcialPorUsuario>();
		busquedas.stream().forEach(busqueda->usuariosSinRepetir.add(busqueda.getUsuario()));
		usuariosSinRepetir.stream().forEach(usuario->
		
		//Aca generamos los reportesParcialesPorUsuario, por cada usuario, tiene su lista de Integer, con cada integer
		// marcando la cantidad de resultados
		reportesParcialesPorUsuario.add(new ReporteParcialPorUsuario(usuario,  busquedas.stream().filter(busqueda->busqueda.mismoUsuario(usuario)).map(busqueda->busqueda.cantidadResultados()).collect(Collectors.toList())))
		
		
		);
		return reportesParcialesPorUsuario;
		
	}
	public List<ReportePorUsuario> busquedasPorUsuario(){
		Set<String> usuariosSinRepetir = new HashSet<>();
		List<ReportePorUsuario> reportesPorUsuario = new ArrayList<ReportePorUsuario>();
		busquedas.stream().forEach(busqueda->usuariosSinRepetir.add(busqueda.getUsuario()));
		filtrarUsuariosDesactivados(usuariosSinRepetir).stream().forEach(usuario-> 
			//Separamos para que quede mas entendible lo que se hace por cada usuario diferente
			//cantidadBusquedas = busquedas.stream().filter(busqueda->busqueda.mismoUsuario(usuario)).mapToInt(busqueda->busqueda.cantidadResultados()).sum()
		
			// Esta linea quedo muy larga, pero nose como separar varias cosas distintas en un forEach y java me tira errores de sintaxis
			// Esto genera un reporte por usuario, con la cantidad de resultados de busquedas totales por ese usuario.
			reportesPorUsuario.add(new ReportePorUsuario(usuario, busquedas.stream().filter(busqueda->busqueda.mismoUsuario(usuario)).mapToInt(busqueda->busqueda.cantidadResultados()).sum()))
		
		
	    );
		return reportesPorUsuario;
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
}
