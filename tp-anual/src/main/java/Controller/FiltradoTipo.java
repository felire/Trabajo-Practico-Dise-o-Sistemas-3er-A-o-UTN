package Controller;

import java.util.List;
import java.util.stream.Collectors;

import ar.utn.frba.disenio.tp_anual.model.CGP;
import ar.utn.frba.disenio.tp_anual.model.LocalComercial;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.ParadaDeColectivo;
import ar.utn.frba.disenio.tp_anual.model.SucursalBanco;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorPOIs;

public class FiltradoTipo {
	public static List<POI> filtrar(String nombre, String tipo){
		List<POI> pois = BuscadorPOIs.getInstance().buscarPOIs(nombre, null);
		if(tipo.equals("CGP")){
			pois = pois.stream().filter(poi->poi.getClass().equals(CGP.class)).collect(Collectors.toList());
		}
		if(tipo.equals("Local Comercial")){
			pois = pois.stream().filter(poi->poi.getClass().equals(LocalComercial.class)).collect(Collectors.toList());
		}
		if(tipo.equals("Banco")){
			pois = pois.stream().filter(poi->poi.getClass().equals(SucursalBanco.class)).collect(Collectors.toList());
		}
		if(tipo.equals("Parada de Colectivo")){
			pois = pois.stream().filter(poi->poi.getClass().equals(ParadaDeColectivo.class)).collect(Collectors.toList());
		}
		return pois;
	}
}
