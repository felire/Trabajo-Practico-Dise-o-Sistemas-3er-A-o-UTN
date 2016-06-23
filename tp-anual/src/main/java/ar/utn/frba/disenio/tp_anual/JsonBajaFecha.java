package ar.utn.frba.disenio.tp_anual;

import java.time.LocalDateTime;

public class JsonBajaFecha {

	private int id;
	private LocalDateTime fecha;
	
	public JsonBajaFecha(JsonBaja poi){
		id = poi.getId();
		fecha = this.sacarFecha(poi.getFecha());
	}
	
	public LocalDateTime sacarFecha(String fechaParametro){//asumimos que fecha viene como anio-mes-dia-hora-minutos
		String[] listaCosas = fechaParametro.split("-");
		LocalDateTime fecha = LocalDateTime.of(Integer.parseInt(listaCosas[0]),Integer.parseInt(listaCosas[1]),Integer.parseInt(listaCosas[2]),Integer.parseInt(listaCosas[3]),Integer.parseInt(listaCosas[4]));
		return fecha;
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
