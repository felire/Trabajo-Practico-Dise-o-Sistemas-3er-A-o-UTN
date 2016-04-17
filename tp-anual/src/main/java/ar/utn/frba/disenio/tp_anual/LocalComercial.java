package ar.utn.frba.disenio.tp_anual;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class LocalComercial extends POI{

	private Set<DisponibilidadHoraria> disponibilidades;
	
	public void addDisponibilidad(DisponibilidadHoraria disponibilidad){
		disponibilidades.add(disponibilidad);
	}
	
	//TODO removeDisponibilidad
	
	public LocalComercial(Integer radio, Set<DisponibilidadHoraria> disponibilidades)
	{
		this.radioDeCercania = radio;
		this.disponibilidades = disponibilidades;
	}
	
	public LocalComercial(Integer radio, DisponibilidadHoraria disponibilidad)
	{
		this.radioDeCercania = radio;
		this.disponibilidades = new HashSet<>();
		disponibilidades.add(disponibilidad);
	}

	public Boolean estaDisponible(LocalDateTime fecha) {
		return disponibilidades.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(fecha));
	}
	
	@Override
	public Boolean estaDisponible(LocalDateTime fecha, String valorX) {
		return estaDisponible(fecha);
	}

}
