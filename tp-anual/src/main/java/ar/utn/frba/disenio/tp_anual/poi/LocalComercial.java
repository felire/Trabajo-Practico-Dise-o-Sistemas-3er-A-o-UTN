package ar.utn.frba.disenio.tp_anual.poi;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.uqbar.geodds.Point;

import util.DisponibilidadHoraria;

public class LocalComercial extends POI{
	private Rubro rubro;
	private Set<DisponibilidadHoraria> disponibilidades;
	
	public void addDisponibilidad(DisponibilidadHoraria disponibilidad){
		disponibilidades.add(disponibilidad);
	}
	
	public LocalComercial(String nombre,Rubro rubro,Set<DisponibilidadHoraria> disponibilidades, Point coordenada)
	{
		super(nombre,coordenada);
		this.radioDeCercania = rubro.getRadioCercania();
		this.rubro = rubro;
		this.disponibilidades = disponibilidades;
	}
	
	public Boolean estaDisponible(LocalDateTime fecha) {
		return disponibilidades.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(fecha));
	}
	
	public Boolean soyBuscado(String palabraClave){
	    return this.rubro.getNombre().equals(palabraClave);
	}
	public void actualizarTags(List<String> listaTags){
		listaTags.removeAll(listaTags);
	}
}
