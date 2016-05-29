package util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.poi.POI;

public class Busqueda {
	private List<POI> resultados;
	private LocalDateTime fecha;
	private String fraseBuscada;
	private String servicioBuscado;
	private BigDecimal tiempoDemorado;
	private String usuario; //El reporte o Busqueda (habria que cambiar el nombre) conoce el nombre de la terminal en la que fue buscado
	//La terminal la tratamos como usuario
	public Busqueda(List<POI> buscados, String fraseBuscada, String servicioBuscado, BigDecimal demora){
		this.resultados = buscados;
		this.fecha = LocalDateTime.now(); //Lo seteamos con la fecha actual
		this.fraseBuscada = fraseBuscada;
		this.servicioBuscado = servicioBuscado;
		this.tiempoDemorado = demora;
	}
	public Integer cantidadResultados(){
		return resultados.size();
	}
	public Boolean mismaFecha(LocalDateTime otraFecha){
		return this.fecha.equals(otraFecha);
	}
	public Boolean mismoUsuario(String usuario){
		return this.usuario == usuario;
	}
	public String getUsuario(){
		return usuario;
	}
	public LocalDateTime getFecha(){
		return this.fecha;
	}
}
