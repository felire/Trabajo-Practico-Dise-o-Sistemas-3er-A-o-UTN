package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.uqbar.geodds.Point;

import java.util.ArrayList;

public class SucursalBanco extends PrestadorDeServicios{

	private static DisponibilidadHoraria horarioBancario;
	
	public SucursalBanco(String nombre,Point coordenada){
		super(nombre,coordenada);
	}

	static void setHorarioBancario() {
		ArrayList<DayOfWeek>lista=new ArrayList<DayOfWeek>();
		ArrayList<FranjaHoraria> franjaBancaria=new ArrayList<FranjaHoraria>();
		franjaBancaria.add(new FranjaHoraria(LocalTime.of(10,0),LocalTime.of(15,0)));
		lista.add(DayOfWeek.MONDAY);
		lista.add(DayOfWeek.TUESDAY);
		lista.add(DayOfWeek.WEDNESDAY);
		lista.add(DayOfWeek.THURSDAY);
		lista.add(DayOfWeek.FRIDAY);
		horarioBancario = new DisponibilidadHoraria(franjaBancaria, lista);
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
