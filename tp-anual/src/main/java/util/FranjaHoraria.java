package util;

import java.time.LocalTime;

import javax.persistence.*;

@Entity
public class FranjaHoraria {
	
	@Id
	@GeneratedValue
	private Integer id;
	@Convert(converter = LocalTimeConverterMorphia.class)
	private LocalTime desdeHorario;
	@Convert(converter = LocalTimeConverterMorphia.class)
	private LocalTime hastaHorario;
	
	public Boolean contiene(LocalTime hora){
		return (desdeHorario.isBefore(hora) && hora.isBefore(hastaHorario));
	}
	
	public FranjaHoraria(LocalTime desde, LocalTime hasta){
		this.desdeHorario = desde;
		this.hastaHorario = hasta;
	}
	
	public void mostrarFranja(){
		System.out.println(this.desdeHorario.toString() + "\n" + this.hastaHorario.toString());
	}
}
