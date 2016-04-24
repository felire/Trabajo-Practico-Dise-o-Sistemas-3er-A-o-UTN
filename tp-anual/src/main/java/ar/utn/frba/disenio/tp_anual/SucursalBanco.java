package ar.utn.frba.disenio.tp_anual;

import java.time.DayOfWeek;
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
		ArrayList<DisponibilidadDelDia>lista=new ArrayList<DisponibilidadDelDia>();
		ArrayList<FranjaHoraria> franjaBancaria=new ArrayList<FranjaHoraria>();
		franjaBancaria.add(new FranjaHoraria(LocalTime.of(10,0),LocalTime.of(15,0)));
		lista.add(new DisponibilidadDelDia(DayOfWeek.MONDAY,franjaBancaria));
		lista.add(new DisponibilidadDelDia(DayOfWeek.TUESDAY,franjaBancaria));
		lista.add(new DisponibilidadDelDia(DayOfWeek.WEDNESDAY,franjaBancaria));
		lista.add(new DisponibilidadDelDia(DayOfWeek.THURSDAY,franjaBancaria));
		lista.add(new DisponibilidadDelDia(DayOfWeek.FRIDAY,franjaBancaria));
		horarioBancario = new DisponibilidadHoraria(lista);
	} 	

	public Boolean estaDisponible(DayOfWeek dia,LocalTime hora, String valorX) 
	{
		if(!enHorarioBancario(dia,hora)) return false;
		return super.estaDisponible(dia,hora, valorX);
	}
	
	public Boolean estaDisponible(DayOfWeek dia,LocalTime hora){
		if(!enHorarioBancario(dia,hora)) return false;
		return super.estaDisponible(dia,hora);
	}
	
	private Boolean enHorarioBancario(DayOfWeek dia,LocalTime hora) {
		return horarioBancario.estaDisponible(dia,hora);
	}	
	
}
