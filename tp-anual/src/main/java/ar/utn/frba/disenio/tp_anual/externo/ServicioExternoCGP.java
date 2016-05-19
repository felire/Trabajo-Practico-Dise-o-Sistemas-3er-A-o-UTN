package ar.utn.frba.disenio.tp_anual.externo;

import java.util.List;

import ar.utn.frba.disenio.tp_anual.CentroDTO;

public interface ServicioExternoCGP {
	public abstract List<CentroDTO> search(String palabraClave);
}
