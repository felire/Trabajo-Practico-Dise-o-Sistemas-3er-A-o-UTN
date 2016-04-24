package ar.utn.frba.disenio.tp_anual;

import java.time.LocalDateTime;

import org.uqbar.geodds.Point;

public class ParadaDeColectivo extends POI{
	public ParadaDeColectivo(String lineaColectivo, Point coordenada)
	{
		super(lineaColectivo,coordenada);
		this.radioDeCercania = 100;
	}
	
	public Boolean soyBuscado(String palabraClave){
	    return this.nombre.equals(palabraClave);
	}
	
	public Boolean estaDisponible(LocalDateTime fecha) {
		return true;
	}

}
