package ar.utn.frba.disenio.tp_anual.servicios;

import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.POI;

import javax.persistence.*;

public interface BuscadorExterno {
	
	public abstract List<POI> filtrar(String palabraClave, String Servicio);
}