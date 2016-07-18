package ar.utn.frba.disenio.tp_anual.adapter;

import java.util.List;
import java.util.Map;

public interface TraductorGeneral {
	public Map<String, List<String>> traducirArchivo(String texto);
}
