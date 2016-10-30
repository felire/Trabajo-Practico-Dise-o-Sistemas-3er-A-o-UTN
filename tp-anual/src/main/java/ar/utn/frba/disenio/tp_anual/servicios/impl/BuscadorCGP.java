package ar.utn.frba.disenio.tp_anual.servicios.impl;

import java.util.ArrayList;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.adapter.CreadorDeCGP;
import ar.utn.frba.disenio.tp_anual.dto.CentroDTO;
import ar.utn.frba.disenio.tp_anual.model.CGP;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.servicios.BuscadorExterno;
import ar.utn.frba.disenio.tp_anual.servicios.CachePoisExternos;
import ar.utn.frba.disenio.tp_anual.servicios.ServicioExternoCGP;

import javax.persistence.*;

public class BuscadorCGP implements BuscadorExterno{
	
	private ServicioExternoCGP servicioExterno;
	private CreadorDeCGP creadorDeCGP;
	private CachePoisExternos cache;
	
	public BuscadorCGP(){
		this.creadorDeCGP = new CreadorDeCGP();
	}
	
	private List<POI> filtrar(String palabraClave){
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
		List<POI> POIsCache= cache.encontrarCGP(palabraClave);
		if(POIsCache==null){
			List<POI> listaExterna=this.filtrar(palabraClave);
			cache.guardarCGPs(listaExterna, palabraClave);	
			return listaExterna;
		}
		return POIsCache;
		
	}
}
