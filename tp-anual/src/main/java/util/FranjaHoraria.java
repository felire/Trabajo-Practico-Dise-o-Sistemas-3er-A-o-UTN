package util;

import java.time.LocalTime;
import java.util.ArrayList;

import javax.persistence.*;

@Entity
public class FranjaHoraria {
	
	@Id
	@GeneratedValue
	private Integer id;

	private LocalTime desdeHorario;

	private LocalTime hastaHorario;
	
	public FranjaHoraria(){

	}
	
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
