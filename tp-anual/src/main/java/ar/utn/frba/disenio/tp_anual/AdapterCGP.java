package ar.utn.frba.disenio.tp_anual;

import org.uqbar.geodds.Point;

import ar.utn.frba.disenio.tp_anual.poi.CGP;
import ar.utn.frba.disenio.tp_anual.poi.Servicio;
import util.DisponibilidadHoraria;
import util.FranjaHoraria;

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
		
	public DisponibilidadHoraria crearDisponibilidad(RangoServicioDTO rango){
		ArrayList<FranjaHoraria> franja= new ArrayList<FranjaHoraria>();
		franja.add(this.traducirHoras(rango));
		ArrayList<DayOfWeek> dia= new ArrayList<DayOfWeek>();
		dia.add(this.traducirDia(rango));
		return new DisponibilidadHoraria(franja,dia);
	}
	
	
	public Set<DisponibilidadHoraria> crearDisponibilidades(List<RangoServicioDTO> rango){
		Set<DisponibilidadHoraria> lista = new HashSet<DisponibilidadHoraria>();
		rango.stream().forEach(r->lista.add(this.crearDisponibilidad(r)));
		return lista;
	}
	
	public Servicio crearServicio (ServicioDTO servicio){
		return new Servicio(servicio.getNombre(),this.crearDisponibilidades(servicio.getRango()));
	}
	
	public List<Servicio> crearServicios (List<ServicioDTO> servicios){
		List<Servicio> lista = new ArrayList<Servicio>();
		servicios.stream().forEach(s->lista.add(this.crearServicio(s)));
		return lista;
	}
}
