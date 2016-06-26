package ar.utn.frba.disenio.tp_anual.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Busqueda {
	private List<POI> resultados;
	private LocalDate fecha;
	private String fraseBuscada;
	private String servicioBuscado;
	private double tiempoDemorado;
	private String terminal; //El reporte o Busqueda (habria que cambiar el nombre) conoce el nombre de la terminal en la que fue buscado
	//La terminal la tratamos como usuario
	public Busqueda(List<POI> buscados, String fraseBuscada, String servicioBuscado, String terminal){
		this.resultados = buscados;
		this.fecha = LocalDate.now(); //Lo seteamos con la fecha actual
		this.fraseBuscada = fraseBuscada;
		this.servicioBuscado = servicioBuscado;
		this.terminal = terminal;
	}
	public void setDemora(Double demora){
		this.tiempoDemorado = demora;
	}
	public double getTiempoDemorado(){
		return this.tiempoDemorado;
	}
	public Integer cantidadResultados(){
		return resultados.size();
	}
	public Boolean mismaFecha(LocalDate otraFecha){
		return this.fecha.equals(otraFecha);
	}
	public Boolean mismoUsuario(String usuario){
		return this.terminal == usuario;
	}
	public String getUsuario(){
		return terminal;
	}
	public LocalDate getFecha(){
		return this.fecha;
	}
}
