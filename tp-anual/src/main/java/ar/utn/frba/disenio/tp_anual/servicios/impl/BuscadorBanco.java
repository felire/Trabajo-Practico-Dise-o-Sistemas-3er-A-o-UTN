package ar.utn.frba.disenio.tp_anual.servicios.impl;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.Point;
import util.Polygon;
import ar.utn.frba.disenio.tp_anual.adapter.CreadorDeBancos;
import ar.utn.frba.disenio.tp_anual.adapter.JsonTraduccion;
import ar.utn.frba.disenio.tp_anual.json.JsonBanco;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.Servicio;
import ar.utn.frba.disenio.tp_anual.model.SucursalBanco;
import ar.utn.frba.disenio.tp_anual.servicios.BuscadorExterno;
import ar.utn.frba.disenio.tp_anual.servicios.CachePoisExternos;
import ar.utn.frba.disenio.tp_anual.servicios.ServicioExternoBanco;
import util.DisponibilidadHoraria;
import util.FranjaHoraria;

import javax.persistence.*;

public class BuscadorBanco implements BuscadorExterno{

	private JsonTraduccion jsonTraduccion;
	private ServicioExternoBanco servicioExterno;
	private CreadorDeBancos creadorDeBancos;
	private CachePoisExternos cache;

	
	public BuscadorBanco(){
		this.jsonTraduccion = new JsonTraduccion();
		this.creadorDeBancos = new CreadorDeBancos();
	}
	
	private List<POI> filtrarInterno(String palabraClave, String servicio){
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
	
	public List<POI> filtrar(String palabraClave, String servicio){
		List<POI> POIsCache= cache.encontrarBancos(palabraClave, servicio);
		if(POIsCache==null){
			List<POI> listaExterna = this.filtrar(palabraClave,servicio);
			cache.guardarBancos(listaExterna, palabraClave, servicio);	
			return listaExterna;
		}
		return POIsCache;
	}

}
