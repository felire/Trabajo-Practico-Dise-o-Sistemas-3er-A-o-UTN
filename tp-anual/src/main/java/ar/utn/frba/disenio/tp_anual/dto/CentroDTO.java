package ar.utn.frba.disenio.tp_anual.dto;

import java.util.ArrayList;
import java.util.List;
import util.Point;
import util.Polygon;

public class CentroDTO {
	private Polygon comuna;
	private String zonasIncluidas;
	private String nombreDirector;
	private String domicilio;
	private String telefono;
	private List<ServicioDTO> listaServicios;
	
	public CentroDTO(){
		listaServicios=new ArrayList<ServicioDTO>();
	}
	public void agregarServicioDTO(ServicioDTO servicio){
		this.listaServicios.add(servicio);
	}
	public void setComuna(Polygon comuna) {
		this.comuna = comuna;
	}
	public void setZonasIncluidas(String zonasIncluidas) {
		this.zonasIncluidas = zonasIncluidas;
	}
	public void setNombreDirector(String nombreDirector) {
		this.nombreDirector = nombreDirector;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public void setListaServicios(List<ServicioDTO> listaServicios) {
		this.listaServicios = listaServicios;
	}
	public Polygon getComuna() {
		return comuna;
	}
	public String getZonasIncluidas() {
		return zonasIncluidas;
	}
	public String getNombreDirector() {
		return nombreDirector;
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
