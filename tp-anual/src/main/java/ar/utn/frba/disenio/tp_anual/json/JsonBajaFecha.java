package ar.utn.frba.disenio.tp_anual.json;

import java.time.LocalDateTime;

public class JsonBajaFecha {

	private int id;
	private LocalDateTime fecha;
	
	public JsonBajaFecha(int id, LocalDateTime fecha){
		this.id = id;
		this.fecha = fecha;
	}
	
	public Integer getId(){
		return id;
	}
	
	public LocalDateTime getFecha(){
		return fecha;
	}
	
	public void setId(Integer id){
		this.id= id;
	}
	
	public void setFecha(LocalDateTime fecha){
		this.fecha=fecha;
	}
}
