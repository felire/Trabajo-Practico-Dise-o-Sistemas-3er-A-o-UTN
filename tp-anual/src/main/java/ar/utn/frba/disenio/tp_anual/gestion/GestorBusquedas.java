package ar.utn.frba.disenio.tp_anual.gestion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import ar.utn.frba.disenio.tp_anual.poi.POI;
import util.Busqueda;
import util.ReportePorFecha;
import util.ReportePorUsuario;

public class GestorBusquedas {
	private BigDecimal tiempoInicio;
	private BigDecimal tiempoFin;
	private BigDecimal tiempoMaximoEspera;
	private BigDecimal demorado;
	private BuscadorPOIs buscadorPOIS;
	private List<Busqueda> busquedas;
	public GestorBusquedas(BigDecimal tiempoMaximoEspera, BuscadorPOIs buscadorPOIS){
		this.tiempoMaximoEspera = tiempoMaximoEspera;
	}
	public void modificarTiempoMaximo(BigDecimal tiempoMaximo){
		this.tiempoMaximoEspera = tiempoMaximo;
	}
	public void medirTiempoInicioTarea(){
		tiempoInicio = new BigDecimal(System.currentTimeMillis());
	}
	public void medirTiempoFinTarea(){
		tiempoFin = new BigDecimal(System.currentTimeMillis());
		demorado = (tiempoFin.add(tiempoInicio.negate())).divide(new BigDecimal(1000));
		
		//Ver que conviene aca, si dejar la variable demorado o pasar todo por las funciones
		this.avisarPorMail(demorado);//Dividimos por 1000 para tener el resultado en segundos
		//Aca solo hacemos (tiempoFin - tiempoInicio)/1000 se ve mas raro xq usamos BigDecimal
	}
	public void avisarPorMail(BigDecimal tiempoDemorado){
		if(tiempoDemorado.doubleValue() > tiempoMaximoEspera.doubleValue()){
			//Aca mandamos el mail, me suena a mockito.
		}
	}
	
	public void buscarPOIs(String palabraClave){
		this.aniadirBusqueda(palabraClave, null);
		
	}

	public void buscarPOIs(String palabraClave, String servicio){
		this.aniadirBusqueda(palabraClave, servicio);
	}
	
	public void aniadirBusqueda(String palabraClave, String servicio){
		this.medirTiempoInicioTarea();
		List<POI> listaPOIBusqueda = buscadorPOIS.buscarPOIs(palabraClave);
		this.medirTiempoFinTarea();
		Busqueda busqueda = new Busqueda(listaPOIBusqueda, palabraClave, servicio, demorado);
		busquedas.add(busqueda);
	}
	public List<Integer> busquedasParcialesPorUsuario(String usuario){ //Retornamos una lista con la cantidad de busquedas por separado de cada terminal o usuario
		return busquedas.stream().filter(busqueda -> busqueda.mismoUsuario(usuario)).map(busqueda->busqueda.cantidadResultados()).collect(Collectors.toList());
	}
	public List<ReportePorUsuario> busquedasPorUsuario(){
		Set<String> usuariosSinRepetir = new HashSet<>();
		List<ReportePorUsuario> reportesPorUsuario = new ArrayList<ReportePorUsuario>();
		busquedas.stream().forEach(busqueda->usuariosSinRepetir.add(busqueda.getUsuario()));
		usuariosSinRepetir.stream().forEach(usuario->   //Separamos para que quede mas entendible lo que se hace por cada usuario diferente
			//cantidadBusquedas = busquedas.stream().filter(busqueda->busqueda.mismoUsuario(usuario)).mapToInt(busqueda->busqueda.cantidadResultados()).sum()
		
			// Esta linea quedo muy larga, pero nose como separar varias cosas distintas en un forEach y java me tira errores de sintaxis
			// Esto genera un reporte por usuario, con la cantidad d eresultados de busquedas totales por ese usuario.
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
