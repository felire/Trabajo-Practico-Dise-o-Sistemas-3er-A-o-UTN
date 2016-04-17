package ar.utn.frba.disenio.tp_anual;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LocalComercial extends POI{

	private Integer radioDeCercania;
	private Set<Disponibilidad> disponibilidades;
	
	public void addDisponibilidad(Disponibilidad disponibilidad){
		disponibilidades.add(disponibilidad);
	}
	
	//TODO removeDisponibilidad
	
	public LocalComercial(Integer radio, Set<Disponibilidad> disponibilidades)
	{
		this.radioDeCercania = radio;
		this.disponibilidades = disponibilidades;
	}
	
	public LocalComercial(Integer radio, Disponibilidad disponibilidad)
	{
		this.radioDeCercania = radio;
		this.disponibilidades = new HashSet<>();
		disponibilidades.add(disponibilidad);
	}
	
	public Boolean esCercano(Coordenada coordenada) {
		return (distancia(this.getCoordenada(), coordenada) < this.radioDeCercania.intValue());
	}

	public Boolean estaDisponible(LocalDateTime fecha) {
		return disponibilidades.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(fecha));
	}
	
	@Override
	public Boolean estaDisponible(LocalDateTime fecha, String valorX) {
		return estaDisponible(fecha);
	}

}
