package ar.utn.frba.disenio.tp_anual;

import java.util.List;

public class ServicioDTO {
	String nombre;
	List<RangoServicioDTO> rango;
	
	public String getNombre() {
		return nombre;
	}
	public List<RangoServicioDTO> getRango() {
		return rango;
	}	
	
}
