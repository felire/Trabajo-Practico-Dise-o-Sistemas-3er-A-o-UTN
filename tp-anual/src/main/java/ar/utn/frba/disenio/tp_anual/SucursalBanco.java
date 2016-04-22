package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.uqbar.geodds.Point;

import java.util.ArrayList;

public class SucursalBanco extends PrestadorDeServicios{

	private static DisponibilidadHoraria horarioBancario;
	
	public SucursalBanco(String nombre,Point coordenada){
		super(coordenada);
		this.nombre = nombre;
		setHorarioBancario();
	}

	private void setHorarioBancario() {
		FranjaHoraria franjaHoraria = new FranjaHoraria(LocalTime.of(10, 0),LocalTime.of(15, 0));
		horarioBancario = new DisponibilidadHoraria(DayOfWeek.MONDAY, 
				DayOfWeek.FRIDAY, franjaHoraria);
	} 	

	public Boolean estaDisponible(LocalDateTime fecha, String valorX) 
	{
		if(!enHorarioBancario(fecha)) return false;
		return super.estaDisponible(fecha, valorX);
	}
	
	public Boolean estaDisponible(LocalDateTime fecha){
		if(!enHorarioBancario(fecha)) return false;
		return super.estaDisponible(fecha);
	}
	
	private Boolean enHorarioBancario(LocalDateTime fecha) {
		return horarioBancario.estaDisponible(fecha);
	}	
	
}
