package ar.utn.frba.disenio.tp_anual.externo;

import java.util.List;

import ar.utn.frba.disenio.tp_anual.poi.POI;

public interface BuscadorExterno {
	public List<POI> filtrar(String palabraClave, String Servicio);
}
