package ar.utn.frba.disenio.tp_anual.model;

import java.math.BigDecimal;


import javax.persistence.*;

import org.mongodb.morphia.annotations.Property;

import util.BigDecimalConverter;

@Entity
public class Rubro {
	
	@Id
	@GeneratedValue
	private long id;
	
	private BigDecimal radioCercania;
	private String nombreRubro;
	
	
	@SuppressWarnings("unused")
	private Rubro(){};
	
	public Rubro(String nombre, BigDecimal radioCercania){
		this.radioCercania = radioCercania;
		this.nombreRubro = nombre;
	}
	public BigDecimal getRadioCercania(){
		return this.radioCercania;
	}
	public String getNombre(){
		return this.nombreRubro;
	}
}
