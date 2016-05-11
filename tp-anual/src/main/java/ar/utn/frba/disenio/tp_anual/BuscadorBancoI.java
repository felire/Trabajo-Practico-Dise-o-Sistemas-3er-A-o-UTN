package ar.utn.frba.disenio.tp_anual;

import java.util.List;

public interface BuscadorBancoI {
	public abstract List<POI> filtrarBancos(String palabraClave, String servicio);
	public abstract List<SucursalBanco> crearPOIS(List<JsonBanco> bancos);
}
