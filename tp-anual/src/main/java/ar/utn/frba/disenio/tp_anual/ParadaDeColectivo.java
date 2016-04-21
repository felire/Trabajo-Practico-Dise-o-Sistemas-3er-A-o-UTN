package ar.utn.frba.disenio.tp_anual;

import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

public class ParadaDeColectivo extends POI{
	public ParadaDeColectivo(String linea, Point coordenada)
	{
		super(coordenada);
		this.nombre = linea;
		this.radioDeCercania = 100;
	}
	public Boolean esBuscado(String palabraClave){
		return this.nombre.equals(palabraClave) || super.esBuscado(palabraClave);
	}
	public Boolean estaDisponible(LocalDateTime fecha, String valorX) {
		return true;
	}

}
