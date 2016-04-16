package ar.utn.frba.disenio.tp_anual;

import java.sql.Date;
import java.time.LocalDateTime;

public class ParadaDeColectivo extends POI{
	public ParadaDeColectivo(String linea)
	{
		this.addTag(linea);
	}
	public Boolean esCercano(Coordenada coordenada) {
		return (distancia(this.getCoordenada(), coordenada) < 100);		
	}

	public Boolean estaDisponible(LocalDateTime fecha, String valorX) {
		return true;
	}

}
