package ar.utn.frba.disenio.tp_anual.gestion;

import java.util.ArrayList;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.AdapterCGP;
import ar.utn.frba.disenio.tp_anual.CentroDTO;
import ar.utn.frba.disenio.tp_anual.externo.BuscadorExterno;
import ar.utn.frba.disenio.tp_anual.externo.ServicioExternoCGP;
import ar.utn.frba.disenio.tp_anual.poi.CGP;
import ar.utn.frba.disenio.tp_anual.poi.POI;

public class BuscadorCGP implements BuscadorExterno{
	
	private ServicioExternoCGP servicioExterno;
	private AdapterCGP adapterCGP;
	
	public List<POI> filtrar(String palabraClave){//Hardcodeado hasta tener correcto funcionamiento
		List<CentroDTO> listaDevolucion = servicioExterno.search(palabraClave);
		if(listaDevolucion != null){
			List<POI> listaCGPS = new ArrayList<POI>();
			listaCGPS.addAll(this.crearCGPs(listaDevolucion));
			return listaCGPS;
		}
		return new ArrayList<POI>();
	}
	public List<CGP> crearCGPs(List<CentroDTO> lista){
		List<CGP> listaCGPS = new ArrayList<CGP>();
		for(int i = 0; i< lista.size(); i++){
			listaCGPS.add(adapterCGP.traducir(lista.get(i)));
		}
		return listaCGPS;
	}
	public void setAdapterCGP(AdapterCGP adapterCGP) {
		this.adapterCGP = adapterCGP;
	}
	
	@Override
	public List<POI> filtrar(String palabraClave, String nada){
		return this.filtrar(palabraClave);
	}
}
