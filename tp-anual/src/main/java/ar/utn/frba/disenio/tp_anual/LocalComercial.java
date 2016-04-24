package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Set;

import org.uqbar.geodds.Point;

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
	
	public Boolean estaDisponible(DayOfWeek dia, LocalTime hora) {
		return disponibilidades.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(dia,hora));
	}
	
	public Boolean soyBuscado(String palabraClave){
	    return this.rubro.getNombre().equals(palabraClave);
	}

}
