package ar.utn.frba.disenio.tp_anual.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.adapter.CreadorDeCGP;
import ar.utn.frba.disenio.tp_anual.dto.CentroDTO;
import ar.utn.frba.disenio.tp_anual.model.CGP;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.servicios.BuscadorExterno;
import ar.utn.frba.disenio.tp_anual.servicios.ServicioExternoCGP;

import javax.persistence.*;

@Entity
@DiscriminatorValue (value = "buscador_cgp")
public class BuscadorCGP extends BuscadorExterno{
	
	@Transient
	private ServicioExternoCGP servicioExterno;
	@Transient
	private CreadorDeCGP creadorDeCGP;
	
	public List<POI> filtrar(String palabraClave){
		List<CentroDTO> listaDevolucion = servicioExterno.search(palabraClave);
		if(listaDevolucion != null){
			List<POI> listaCGPS = new ArrayList<POI>();
			listaCGPS.addAll(creadorDeCGP.crearCGPs(listaDevolucion));
			return listaCGPS;
		}
		return new ArrayList<POI>();
	}
	
	public void setCreadorDeCGP(CreadorDeCGP creadorDeCGP) {
		this.creadorDeCGP = creadorDeCGP;
	}
	
	
	public List<POI> filtrar(String palabraClave, String nada){
		return this.filtrar(palabraClave);
	}
}
