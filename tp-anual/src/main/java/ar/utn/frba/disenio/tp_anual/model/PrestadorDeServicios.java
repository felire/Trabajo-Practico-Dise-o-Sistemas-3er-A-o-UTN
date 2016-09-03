package ar.utn.frba.disenio.tp_anual.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import util.Point;



@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public abstract class PrestadorDeServicios extends POI{
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "poi_id")
	public List<Servicio> servicios;
	
	public List<Servicio> getServicios() {
		return servicios;
	}
	
	
	public PrestadorDeServicios(String nombre,Point coordenada)
	{
		super(nombre,coordenada);
		servicios = new ArrayList<Servicio>();
	}

	public void addServicio(Servicio servicio)
	{
		servicios.add(servicio);
	}
	
	public void deleteServicio(Servicio servicio)
	{
		servicios.remove(servicio);
	}
	
	public Boolean estaDisponible(LocalDateTime fecha, String valorX)
	{
		return servicios.stream().anyMatch(servicio -> servicio.toString().equals(valorX) && servicio.estaDisponible(fecha));
	}
	public Boolean estaDisponible(LocalDateTime fecha)
	{
		return servicios.stream().anyMatch(servicio -> servicio.estaDisponible(fecha));
	}
	
	public Boolean soyBuscado(String palabraClave){
		return this.servicios.stream().anyMatch(servicio -> servicio.toString().contains(palabraClave));
	}
}
