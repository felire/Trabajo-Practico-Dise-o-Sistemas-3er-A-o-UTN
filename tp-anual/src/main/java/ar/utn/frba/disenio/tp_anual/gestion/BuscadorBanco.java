package ar.utn.frba.disenio.tp_anual.gestion;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.uqbar.geodds.Point;

import ar.utn.frba.disenio.tp_anual.JsonBanco;
import ar.utn.frba.disenio.tp_anual.CreadorDeBancos;
import ar.utn.frba.disenio.tp_anual.JsonTraduccion;
import ar.utn.frba.disenio.tp_anual.externo.BuscadorExterno;
import ar.utn.frba.disenio.tp_anual.externo.ServicioExternoBanco;
import ar.utn.frba.disenio.tp_anual.poi.POI;
import ar.utn.frba.disenio.tp_anual.poi.Servicio;
import ar.utn.frba.disenio.tp_anual.poi.SucursalBanco;
import util.DisponibilidadHoraria;
import util.FranjaHoraria;

public class BuscadorBanco implements BuscadorExterno{
	private JsonTraduccion jsonTraduccion;
	private ServicioExternoBanco servicioExterno;
	private CreadorDeBancos creadorDeBancos;
	@Override
	public List<POI> filtrar(String palabraClave, String servicio){
		String devolucion = servicioExterno.search(palabraClave, servicio);
		if(devolucion!=null){
		List<JsonBanco> listaTraducida = jsonTraduccion.traductor(devolucion);
		List<POI> listaPOIS = new ArrayList<POI>();
		listaPOIS.addAll(creadorDeBancos.crearPOIS(listaTraducida));
		return listaPOIS;
		}
		return new ArrayList<POI>();
	}
	
	public void setCreadorDeBancos(CreadorDeBancos creador){
		this.creadorDeBancos = creador;
	}

}
