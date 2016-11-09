package ar.utn.frba.disenio.tp_anual.model;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
/*
import javax.persistence.Convert;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;*/

import org.bson.types.ObjectId;
import org.mongodb.morphia.*;
import org.mongodb.morphia.annotations.Converters;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Property;
import org.mongodb.morphia.annotations.Reference;
import org.uqbarproject.jpa.java8.extras.convert.LocalDateConverter;


@Entity
public class Busqueda {
	
	@Id
	private ObjectId id;
	
	@Embedded
	private List<POI> resultados;
	
	private LocalDate fecha;
	
	//@Property
	private String fraseBuscada;

	//@Property
	private String servicioBuscado;

	//@Property
	private double tiempoDemorado;
	
	//@Property
	private String terminal;
	
	public Busqueda() {
		
	}
	public Busqueda(List<POI> buscados, String fraseBuscada, String servicioBuscado, String terminal){
		this.resultados = buscados;
		this.fecha = LocalDate.now(); //Lo seteamos con la fecha actual
		this.fraseBuscada = fraseBuscada;
		this.servicioBuscado = servicioBuscado;
		this.terminal = terminal;
	}
	
	public ObjectId getID(){
		return this.id;
	}
	
	public void setId(ObjectId id){
		this.id=id;
	}
	
	public String getUrl(){
		return "/filtrarConsultas/"+id.toString();
	}
	
	public String getTerminal(){
		return this.terminal;
	}
	public List<POI> getResultados(){
		return resultados;
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
	public String getFraseBuscada() {
		return fraseBuscada;
	}
	public void setFraseBuscada(String fraseBuscada) {
		this.fraseBuscada = fraseBuscada;
	}
	public void setResultados(List<POI> resultados) {
		this.resultados = resultados;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}
	
}
