package ar.utn.frba.disenio.tp_anual;

import org.uqbar.geodds.Point;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AdapterCGP {

	public CGP traducir(CentroDTO centro){
		CGP cgp = new CGP("Nuevo CGP", centro.getComuna(),new Point(1,0)); //hardcodeo todo lo que el centroDTO no me proporciona
		cgp.setServicios(this.crearServicios(centro.getListaServicios()));
		return cgp;		
	}
	
	public FranjaHoraria traducirHoras (RangoServicioDTO rango){	
		return new FranjaHoraria(LocalTime.of(rango.getHorarioDesde(),rango.getMinutoDesde()),LocalTime.of(rango.getHorarioHasta(),rango.getMinutoHasta()));
	}
	
	public DayOfWeek traducirDia (RangoServicioDTO rango){
		return DayOfWeek.of(rango.getDia());
	}
		
	public Boolean rangosDeIgualHora (RangoServicioDTO rango1, RangoServicioDTO rango2){
		return this.traducirHoras(rango1) == this.traducirHoras(rango2);
	}
	
	public DisponibilidadHoraria crearDisponibilidad (List<RangoServicioDTO> rango){
		ArrayList<DayOfWeek> diasSimilares = new ArrayList<DayOfWeek>();
		RangoServicioDTO primerRango = rango.get(0);
		List<RangoServicioDTO> lista = new ArrayList<RangoServicioDTO>();
		lista.addAll(rango.stream().filter(rango1 -> this.rangosDeIgualHora(primerRango,rango1)).collect(Collectors.toList()));
		int i=0;
		while (lista.size()!= 0){
			diasSimilares.add(this.traducirDia(lista.get(i)));
			i++;
		}
		rango.removeAll(lista);
		ArrayList<FranjaHoraria> franja = new ArrayList<FranjaHoraria>();
		franja.add(this.traducirHoras(primerRango));
		return new DisponibilidadHoraria(franja,diasSimilares);
	}
	
	public Set<DisponibilidadHoraria> crearDisponibilidades(List<RangoServicioDTO> rango){
		Set<DisponibilidadHoraria> lista = new HashSet<DisponibilidadHoraria>();
		int i=0;
		while (i<lista.size()){
			lista.add(this.crearDisponibilidad(rango));
		}
		return lista;
	}
	
	public Servicio crearServicio (ServicioDTO servicio){
		return new Servicio(servicio.getNombre(),this.crearDisponibilidades(servicio.getRango()));
	}
	
	public List<Servicio> crearServicios (List<ServicioDTO> servicios){
		List<Servicio> lista = new ArrayList<Servicio>();
		int i=0;
		while (i<servicios.size()){
			lista.add(this.crearServicio(servicios.get(i)));
			i++;
		}
		return lista;
	}
}
