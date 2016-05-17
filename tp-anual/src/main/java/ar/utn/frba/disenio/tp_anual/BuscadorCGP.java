package ar.utn.frba.disenio.tp_anual;

import java.util.ArrayList;
import java.util.List;

public class BuscadorCGP{
	
	private ServicioExternoCGP servicioExterno;
	private AdapterCGP adapterCGP;
	public List<POI> filtrarCGPs(String palabraClave){//Hardcodeado hasta tener correcto funcionamiento
		List<CentroDTO> listaDevolucion = servicioExterno.search(palabraClave);
		if(listaDevolucion != null){
			return this.crearCGPs(listaDevolucion);
		}
		return new ArrayList<POI>();
	}
	public List<POI> crearCGPs(List<CentroDTO> lista){
		List<POI> listaCGPS = new ArrayList<POI>();
		for(int i = 0; i< lista.size(); i++){
			listaCGPS.add(adapterCGP.traducir(lista.get(i)));
		}
		return listaCGPS;
	}
	public void setAdapterCGP(AdapterCGP adapterCGP) {
		this.adapterCGP = adapterCGP;
	}
}
