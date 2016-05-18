package ar.utn.frba.disenio.tp_anual;

import java.util.List;

public interface BuscadorExterno {
	public List<POI> filtrar(String palabraClave);
}
