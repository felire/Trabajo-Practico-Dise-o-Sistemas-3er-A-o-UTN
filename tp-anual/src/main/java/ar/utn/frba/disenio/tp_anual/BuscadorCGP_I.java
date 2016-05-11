package ar.utn.frba.disenio.tp_anual;

import java.util.List;

public interface BuscadorCGP_I {
	public abstract List<POI> filtrarCGPs(String palabraClave);
}
