package ar.utn.frba.disenio.tp_anual;

import java.util.ArrayList;
import java.util.List;

public class ServicioDTO {
	private String nombre;
	private List<RangoServicioDTO> rango;
	
	public ServicioDTO(){
		rango=new ArrayList<RangoServicioDTO>();
	}
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
	public void agregarRango(RangoServicioDTO rango){
		this.rango.add(rango);
	}
}
