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
		
	public DisponibilidadHoraria crearDisponibilidad(RangoServicioDTO rango){
		ArrayList<FranjaHoraria> franja= new ArrayList<FranjaHoraria>();
		franja.add(this.traducirHoras(rango));
		ArrayList<DayOfWeek> dia= new ArrayList<DayOfWeek>();
		dia.add(this.traducirDia(rango));
		return new DisponibilidadHoraria(franja,dia);
	}
	
	
	public Set<DisponibilidadHoraria> crearDisponibilidades(List<RangoServicioDTO> rango){
		Set<DisponibilidadHoraria> lista = new HashSet<DisponibilidadHoraria>();
		int i=0;
		while (i<rango.size()){
			lista.add(this.crearDisponibilidad(rango.get(i)));
			i++;
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
