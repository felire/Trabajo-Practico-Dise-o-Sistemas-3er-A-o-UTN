package ar.utn.frba.disenio.tp_anual.servicios.impl;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.uqbar.geodds.Point;

import ar.utn.frba.disenio.tp_anual.adapter.CreadorDeBancos;
import ar.utn.frba.disenio.tp_anual.adapter.JsonTraduccion;
import ar.utn.frba.disenio.tp_anual.json.JsonBanco;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.Servicio;
import ar.utn.frba.disenio.tp_anual.model.SucursalBanco;
import ar.utn.frba.disenio.tp_anual.servicios.BuscadorExterno;
import ar.utn.frba.disenio.tp_anual.servicios.ServicioExternoBanco;
import util.DisponibilidadHoraria;
import util.FranjaHoraria;

import javax.persistence.*;

@Entity
@DiscriminatorValue (value= "buscador_banco")
public class BuscadorBanco extends BuscadorExterno{
	
	@Transient
	private JsonTraduccion jsonTraduccion;
	@Transient
	private ServicioExternoBanco servicioExterno;
	@Transient
	private CreadorDeBancos creadorDeBancos;
	
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
