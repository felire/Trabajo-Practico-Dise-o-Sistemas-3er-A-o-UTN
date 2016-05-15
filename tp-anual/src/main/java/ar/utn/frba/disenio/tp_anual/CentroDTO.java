package ar.utn.frba.disenio.tp_anual;

import java.util.List;
import org.uqbar.geodds.Polygon;

public class CentroDTO {
	Polygon comuna;
	String zonasIncluidas;
	String nombreDrirector;
	String domicilio;
	String telefono;
	List<ServicioDTO> listaServicios;
	
	public Polygon getComuna() {
		return comuna;
	}
	public String getZonasIncluidas() {
		return zonasIncluidas;
	}
	public String getNombreDrirector() {
		return nombreDrirector;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public String getTelefono() {
		return telefono;
	}
	public List<ServicioDTO> getListaServicios() {
		return listaServicios;
	}
	
	
}
