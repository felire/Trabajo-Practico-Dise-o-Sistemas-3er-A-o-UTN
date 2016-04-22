package ar.utn.frba.disenio.tp_anual;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.uqbar.geodds.Point;

public class LocalComercial extends POI{
	private Rubro rubro;
	private Set<DisponibilidadHoraria> disponibilidades;
	
	public void addDisponibilidad(DisponibilidadHoraria disponibilidad){
		disponibilidades.add(disponibilidad);
	}
	
	//TODO removeDisponibilidad
	
	public LocalComercial(String nombre,Rubro rubro,Set<DisponibilidadHoraria> disponibilidades, Point coordenada)
	{
		super(coordenada);
		this.nombre = nombre;
		this.radioDeCercania = rubro.getRadioCercania();
		this.rubro = rubro;
		this.disponibilidades = disponibilidades;
	}
	

	public Boolean estaDisponible(LocalDateTime fecha) {
		return disponibilidades.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(fecha));
	}
	public Boolean esBuscado(String palabraClave){
		return this.rubro.getNombre().equals(palabraClave) || super.esBuscado(palabraClave);
	}

}
