package ar.utn.frba.disenio.tp_anual;

import java.sql.Date;
import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

public class ParadaDeColectivo extends POI{
	public ParadaDeColectivo(String linea)
	{
		this.radioDeCercania = 100;
		this.addTag(linea);
	}

	public Boolean estaDisponible(LocalDateTime fecha, String valorX) {
		return true;
	}

}
