package ar.utn.frba.disenio.tp_anual.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Convert;

import org.bson.types.ObjectId;
import org.mongodb.morphia.*;
import org.mongodb.morphia.annotations.Converters;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;


@Entity
public class Busqueda {
	
	@Id
	private ObjectId id;
	
	@Reference
	private List<POI> resultados;
	
	@Convert (converter = LocalDateConverter.class)
	private LocalDate fecha;
	
	@Property
	private String fraseBuscada;

	@Property
	private String servicioBuscado;

	@Property
	private double tiempoDemorado;
	
	@Reference
	private Terminal terminal;
	
	public Busqueda() {
		super();
	}
	public Busqueda(List<POI> buscados, String fraseBuscada, String servicioBuscado, Terminal terminal){
		this.resultados = buscados;
		this.fecha = null; //Lo seteamos con la fecha actual
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
		return this.terminal.getNombre() == usuario;
	}
	public String getUsuario(){
		return terminal.getNombre();
	}
	public LocalDate getFecha(){
		return this.fecha;
	}
	
}
