package ar.utn.frba.disenio.tp_anual.gestion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.poi.POI;
import util.Reporte;

public class GestorBusquedas {
	BigDecimal tiempoInicio;
	BigDecimal tiempoFin;
	BigDecimal tiempoMaximoEspera;
	BigDecimal demorado;
	BuscadorPOIs buscadorPOIS;
	List<Reporte> reportes;
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
		this.aniadirReporte(palabraClave, null);
		
	}

	public void buscarPOIs(String palabraClave, String servicio){
		this.aniadirReporte(palabraClave, servicio);
	}
	
	public void aniadirReporte(String palabraClave, String servicio){
		this.medirTiempoInicioTarea();
		List<POI> listaPOIBusqueda = buscadorPOIS.buscarPOIs(palabraClave);
		this.medirTiempoFinTarea();
		Reporte reporte = new Reporte(buscadorPOIS.buscarPOIs(palabraClave), palabraClave, servicio, demorado);
		reportes.add(reporte);
	}
	
	public BigDecimal busquedasPorFecha(LocalDateTime fecha){
		return new BigDecimal(reportes.stream().filter(reporte->reporte.mismaFecha(fecha)).count());
	}
}
