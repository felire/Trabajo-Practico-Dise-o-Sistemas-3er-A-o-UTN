package ar.utn.frba.disenio.tp_anual.servicios;

import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.POI;

public interface BuscadorExterno {
	public List<POI> filtrar(String palabraClave, String Servicio);
}
