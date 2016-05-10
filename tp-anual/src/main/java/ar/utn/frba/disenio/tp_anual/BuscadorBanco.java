package ar.utn.frba.disenio.tp_anual;
import java.util.List;
public abstract class BuscadorBanco {
		public abstract List<POI> filtrarBancos(String palabraClave, String servicio);
}
