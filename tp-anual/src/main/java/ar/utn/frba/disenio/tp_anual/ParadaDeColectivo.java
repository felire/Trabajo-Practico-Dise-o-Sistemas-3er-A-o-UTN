package ar.utn.frba.disenio.tp_anual;

import java.sql.Date;

public class ParadaDeColectivo extends POI{

	public Boolean esCercano(Coordenada coordenada) {
		return (distancia(this.getCoordenada(), coordenada) < 100);		
	}

	public Boolean estaDisponible(Date fecha, String valorX) {
		
	}

}
