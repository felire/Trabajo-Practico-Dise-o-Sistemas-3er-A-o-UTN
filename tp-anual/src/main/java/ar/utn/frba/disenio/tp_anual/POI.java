package ar.utn.frba.disenio.tp_anual;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.uqbar.geodds.Point;

public abstract class POI { //Fijense que habria que ver que getter y setter dejar y cuales inizializar en el constructor
	
	protected String nombre;
	protected Point coordenada;
	protected List<String> listaTags;
	protected BigDecimal radioDeCercania;
	
	public POI(String nombre, Point coordenada)
	{
		this.nombre=nombre;
		radioDeCercania = new BigDecimal(0.5);
		listaTags = new ArrayList<String>();
		this.coordenada = coordenada;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void addTag(String tag)
	{
		listaTags.add(tag);
	}
	public void deleteTag(String tag)
	{
		listaTags.remove(tag);
	}
	
	public Boolean esBuscado(String palabraClave)
	{
	    return listaTags.stream().anyMatch(cadena1 -> cadena1.contains(palabraClave)) || this.soyBuscado(palabraClave);
	}
	
	public abstract Boolean soyBuscado(String palabraClave);
	
	public Boolean seEncuentraAXDe(Integer kilometros, POI poi)
	{
		return this.coordenada.distance(poi.getCoordenada()) <= kilometros;
	}
	
	public Boolean esCercano(Point coordenada)
	{
		return this.coordenada.distance(coordenada) <= this.radioDeCercania.doubleValue();
	}
	
	
	public Boolean esValido(){
		return (this.coordenada != null && this.nombre != null);
	}
	
	public Point getCoordenada() {
		return coordenada;
	}
	
	
	
}
