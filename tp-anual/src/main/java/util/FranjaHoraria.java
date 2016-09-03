package util;

import java.time.LocalTime;

import javax.persistence.*;

@Entity
public class FranjaHoraria {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime desdeHorario;
	@Convert(converter = LocalTimeConverter.class)
	private LocalTime hastaHorario;
	
	public Boolean contiene(LocalTime hora){
		return (desdeHorario.isBefore(hora) && hora.isBefore(hastaHorario));
	}
	
	public FranjaHoraria(LocalTime desde, LocalTime hasta){
		this.desdeHorario = desde;
		this.hastaHorario = hasta;
	}
}
