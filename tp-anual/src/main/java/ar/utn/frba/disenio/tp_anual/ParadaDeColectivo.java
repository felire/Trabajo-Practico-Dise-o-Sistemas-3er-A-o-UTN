package ar.utn.frba.disenio.tp_anual;

import java.time.LocalDateTime;

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
