package ar.utn.frba.disenio.tp_anual;

import java.util.List;

public interface ServicioExternoCGP {
	public abstract List<CentroDTO> search(String palabraClave);
}
