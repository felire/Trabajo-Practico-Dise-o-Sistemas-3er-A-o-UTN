package ar.utn.frba.disenio.tp_anual.gestion;

import java.util.ArrayList;
import java.util.List;


import ar.utn.frba.disenio.tp_anual.CentroDTO;
import ar.utn.frba.disenio.tp_anual.CreadorDeCGP;
import ar.utn.frba.disenio.tp_anual.externo.BuscadorExterno;
import ar.utn.frba.disenio.tp_anual.externo.ServicioExternoCGP;
import ar.utn.frba.disenio.tp_anual.poi.CGP;
import ar.utn.frba.disenio.tp_anual.poi.POI;

public class BuscadorCGP implements BuscadorExterno{
	
	private ServicioExternoCGP servicioExterno;
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
	
	@Override
	public List<POI> filtrar(String palabraClave, String nada){
		return this.filtrar(palabraClave);
	}
}
