package util;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
public class DisponibilidadHoraria {
	@Id
	@GeneratedValue
	private Integer id;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "disponibilidad_id")
	private List<FranjaHoraria> franjas;
	
	@ElementCollection
	@CollectionTable(name="DiasSemana", joinColumns=@JoinColumn(name="disponibilidad_id"))
	@Column(name="dias")
	private List<DayOfWeek> dias;
	
	public DisponibilidadHoraria(ArrayList<FranjaHoraria> franjas, ArrayList<DayOfWeek>  dias){
		this.franjas = franjas;
		this.dias = dias;		
	}
	public Boolean diaDisponible(LocalDateTime fecha){
		return this.dias.contains(fecha.getDayOfWeek());
	}
	public Boolean horaDisponible(LocalDateTime fecha){
		return this.franjas.stream().anyMatch(franja->franja.contiene(fecha.toLocalTime()));
	}
	public Boolean estaDisponible(LocalDateTime fecha){
		return diaDisponible(fecha) && horaDisponible(fecha);
	}
	
	public List<FranjaHoraria> getFranjas(){
		return this.franjas;
	}
}
