package ar.utn.frba.disenio.tp_anual.adapter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;



import ar.utn.frba.disenio.tp_anual.json.JsonBanco;
import ar.utn.frba.disenio.tp_anual.model.Servicio;
import ar.utn.frba.disenio.tp_anual.model.SucursalBanco;
import util.DisponibilidadHoraria;
import util.FranjaHoraria;
import util.Point;

public class CreadorDeBancos {
	public List<SucursalBanco> crearPOIS(List<JsonBanco> bancos){		
		DisponibilidadHoraria horarioBancario;
		Set<DisponibilidadHoraria> disponibilidades;
		ArrayList<DayOfWeek>listaDias=new ArrayList<DayOfWeek>();
		ArrayList<FranjaHoraria> franjaBancaria=new ArrayList<FranjaHoraria>();
		franjaBancaria.add(new FranjaHoraria(LocalTime.of(10,0),LocalTime.of(15,0)));
		listaDias.add(DayOfWeek.MONDAY);
		listaDias.add(DayOfWeek.TUESDAY);
		listaDias.add(DayOfWeek.WEDNESDAY);
		listaDias.add(DayOfWeek.THURSDAY);
		listaDias.add(DayOfWeek.FRIDAY);
		horarioBancario = new DisponibilidadHoraria(franjaBancaria, listaDias);
		disponibilidades = new HashSet<>();
		disponibilidades.add(horarioBancario);
		int i;
		List<JsonBanco> lista = bancos;
		List<SucursalBanco> listaPOIS = new ArrayList<SucursalBanco>();		
		for(i = 0; i < lista.size(); i++){
			listaPOIS.add(this.crearPOI(lista,i,disponibilidades));	
		}
		return listaPOIS;
	}
	
	public SucursalBanco crearPOI(List<JsonBanco> lista, int i, Set<DisponibilidadHoraria> disponibilidades){
		List<String> servicios = lista.get(i).getServicios();
		SucursalBanco banco = new SucursalBanco(lista.get(i).getBanco(), new Point(lista.get(i).getX(), lista.get(i).getY()));
		for(int j = 0; j < servicios.size(); j++){
		Servicio servicio = new Servicio(servicios.get(j), disponibilidades);
		banco.addServicio(servicio);
		}
		return banco;
	}
}
