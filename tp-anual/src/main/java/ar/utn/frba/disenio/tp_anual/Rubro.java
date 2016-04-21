package ar.utn.frba.disenio.tp_anual;

public class Rubro {
	private Integer radioCercania;
	private String nombreRubro;
	public Rubro(String nombre, Integer radioCercania){
		this.radioCercania = radioCercania;
		this.nombreRubro = nombre;
	}
	public Integer getRadioCercania(){
		return this.radioCercania;
	}
	public String getNombre(){
		return this.nombreRubro;
	}
}
