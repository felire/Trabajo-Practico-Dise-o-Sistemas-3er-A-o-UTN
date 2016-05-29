package util;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.poi.POI;

public class Busqueda {
	List<POI> buscados;
	LocalDateTime fecha;
	String fraseBuscada;
	String servicioBuscado;
	BigDecimal tiempoDemorado;
	String usuario; //El reporte o Busqueda (habria que cambiar el nombre) conoce el nombre de la terminal en la que fue buscado
	//La terminal la tratamos como usuario
	public Busqueda(List<POI> buscados, String fraseBuscada, String servicioBuscado, BigDecimal demora){
		this.buscados = buscados;
		this.fecha = LocalDateTime.now(); //Lo seteamos con la fecha actual
		this.fraseBuscada = fraseBuscada;
		this.servicioBuscado = servicioBuscado;
		this.tiempoDemorado = demora;
	}
	public Integer cantidadBusquedas(){
		return buscados.size();
	}
	public Boolean mismaFecha(LocalDateTime otraFecha){
		return this.fecha.equals(otraFecha);
	}
	public Boolean mismoUsuario(String usuario){
		return this.usuario == usuario;
	}
}
