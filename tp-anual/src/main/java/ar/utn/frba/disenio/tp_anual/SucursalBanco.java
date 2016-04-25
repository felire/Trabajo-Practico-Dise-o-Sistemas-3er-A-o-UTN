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
		setHorarioBancario();
	}

	private void setHorarioBancario() {
		ArrayList<DiaLaboral>lista=new ArrayList<DiaLaboral>();
		ArrayList<FranjaHoraria> franjaBancaria=new ArrayList<FranjaHoraria>();
		franjaBancaria.add(new FranjaHoraria(LocalTime.of(10,0),LocalTime.of(15,0)));
		lista.add(new DiaLaboral(DayOfWeek.MONDAY,franjaBancaria));
		lista.add(new DiaLaboral(DayOfWeek.TUESDAY,franjaBancaria));
		lista.add(new DiaLaboral(DayOfWeek.WEDNESDAY,franjaBancaria));
		lista.add(new DiaLaboral(DayOfWeek.THURSDAY,franjaBancaria));
		lista.add(new DiaLaboral(DayOfWeek.FRIDAY,franjaBancaria));
		horarioBancario = new DisponibilidadHoraria(lista);
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
