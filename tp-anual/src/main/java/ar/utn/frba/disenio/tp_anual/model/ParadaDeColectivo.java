package ar.utn.frba.disenio.tp_anual.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import util.Point;



public class ParadaDeColectivo extends POI{
	public ParadaDeColectivo(String lineaColectivo, Point coordenada)
	{
		super(lineaColectivo,coordenada);
		this.radioDeCercania = new BigDecimal(0.1);
	}
	
	public Boolean soyBuscado(String palabraClave){
	    return this.nombre.equals(palabraClave);
	}
	
	public Boolean estaDisponible(LocalDateTime fecha) {
		return true;
	}

}
