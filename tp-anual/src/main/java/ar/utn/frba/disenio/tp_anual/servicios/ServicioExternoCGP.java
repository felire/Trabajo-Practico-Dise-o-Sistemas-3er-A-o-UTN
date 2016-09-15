package ar.utn.frba.disenio.tp_anual.servicios;

import java.util.List;

import ar.utn.frba.disenio.tp_anual.dto.CentroDTO;

public interface ServicioExternoCGP {
	public abstract List<CentroDTO> search(String palabraClave);
}
