package ar.utn.frba.disenio.tp_anual;

import java.math.BigDecimal;

public class Rubro {
	private BigDecimal radioCercania;
	private String nombreRubro;
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
