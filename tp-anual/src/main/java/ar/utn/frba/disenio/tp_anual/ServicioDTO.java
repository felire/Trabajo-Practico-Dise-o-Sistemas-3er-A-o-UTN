package ar.utn.frba.disenio.tp_anual;

import java.util.List;

public class ServicioDTO {
	private String nombre;
	private List<RangoServicioDTO> rango;
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setRango(List<RangoServicioDTO> rango) {
		this.rango = rango;
	}
	public List<RangoServicioDTO> getRango() {
		return rango;
	}	
	
}
