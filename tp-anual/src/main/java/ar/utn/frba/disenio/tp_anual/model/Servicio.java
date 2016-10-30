package ar.utn.frba.disenio.tp_anual.model;

import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.*;

import util.DisponibilidadHoraria;

@Entity
public class Servicio {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String nombre;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "servicio_id")
	private Set<DisponibilidadHoraria> diasDisponibles;
	
	public Servicio(String nombre, Set<DisponibilidadHoraria> diasDisponibles) {
		this.nombre = nombre;
		this.diasDisponibles = diasDisponibles;
	}
	
	public boolean estaDisponible(LocalDateTime fecha)
	{
		return diasDisponibles.stream().anyMatch(disponibilidad -> disponibilidad.estaDisponible(fecha));
	}
	public String getNombre() {
		return nombre;
	}
	public String toString()
	{
		return nombre;
	}
}
