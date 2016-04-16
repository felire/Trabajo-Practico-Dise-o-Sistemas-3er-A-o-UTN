package ar.utn.frba.disenio.tp_anual;

import java.sql.Date;
import java.time.LocalDateTime;

public class LocalComercial extends POI{

	private Integer radioDeCercania;
	public LocalComercial(int radio)
	{
		radioDeCercania = new Integer(radio);
	}
	public Boolean esCercano(Coordenada coordenada) {
		return (distancia(this.getCoordenada(), coordenada) < this.radioDeCercania.intValue());
	}

	public Boolean estaDisponible(LocalDateTime fecha, String valorX) {
		// TODO Auto-generated method stub
		return null;
	}

}
