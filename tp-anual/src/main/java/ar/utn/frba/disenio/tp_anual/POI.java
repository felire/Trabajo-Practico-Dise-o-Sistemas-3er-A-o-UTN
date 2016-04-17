package ar.utn.frba.disenio.tp_anual;

import java.awt.Point;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class POI { //Fijense que habria que ver que getter y setter dejar y cuales inizializar en el constructor
	
	private String nombre;
	private Coordenada coordenada;
	private Direccion direccion; 
	private List<String> listaTags;
	
	public POI()
	{
		listaTags = new ArrayList<String>();
	}
	
	public void addTag(String tag)
	{
		listaTags.add(tag);
	}
	public void deleteTag(String tag)
	{
		listaTags.remove(tag);
	}
	
	public boolean tengoPalabraClave(String palabraClave)
	{
		return (listaTags.stream().anyMatch(cadena1 -> cadena1.indexOf(palabraClave) != -1));
	}
	public Coordenada getCoordenada()
	{
		return coordenada;
	}
	public Boolean seEncuentraAXDe(Integer metros, POI poi)
	{
		double distancia = distancia(this.getCoordenada(), poi.getCoordenada());
		return (distancia <= metros);
	}
	
	public double distancia(Coordenada coordenada1, Coordenada coordenada2)
	{
		return Math.sqrt(Math.pow((coordenada1.getLatitud() - coordenada1.getLatitud()),2) + Math.pow((coordenada2.getLongitud() - coordenada2.getLongitud()),2));
	}
	
	public Boolean esCercano(Coordenada coordenada)
	{
		return (distancia(this.getCoordenada(), coordenada) < 500);
	}
	
	public abstract Boolean estaDisponible(LocalDateTime fecha, String valorX);
	
	public Boolean esValido(){
		return (this.coordenada != null && this.nombre != null);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
