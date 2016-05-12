package ar.utn.frba.disenio.tp_anual;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.uqbar.geodds.Point;
public class BuscadorBanco{
	private JsonTraduccion jsonTraduccion;
	private ServicioExternoBanco servicioExterno;
	
	public List<SucursalBanco> filtrarBancos(String palabraClave, String servicio){ //Hardcodeada hasta tener comportamiento real
		String devolucion = servicioExterno.search(palabraClave, servicio);
		if(devolucion!=null){
		List<JsonBanco> listaTraducida = jsonTraduccion.traductor(devolucion);
		return this.crearPOIS(listaTraducida);
		}
		return new ArrayList<SucursalBanco>();
	}
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
