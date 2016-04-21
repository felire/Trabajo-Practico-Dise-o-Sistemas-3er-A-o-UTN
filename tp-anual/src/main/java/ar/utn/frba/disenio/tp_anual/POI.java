package ar.utn.frba.disenio.tp_anual;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.uqbar.geodds.Point;

public abstract class POI { //Fijense que habria que ver que getter y setter dejar y cuales inizializar en el constructor
	
	protected String nombre;
	protected Point coordenada;
	protected String calle;
	protected Integer altura;
	protected List<String> listaTags;
	protected Integer radioDeCercania;
	
	public POI(Point coordenada)
	{
		radioDeCercania = 500;
		listaTags = new ArrayList<String>();
		this.coordenada = coordenada;
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
		if(nombre.indexOf(palabraClave) != -1)return true;
		return (listaTags.stream().anyMatch(cadena1 -> cadena1.indexOf(palabraClave) != -1));
	}
	
	public Boolean seEncuentraAXDe(Integer metros, POI poi)
	{
		return this.coordenada.distance(poi.getCoordenada()) <= metros;
	}
	
	public Boolean esCercano(Point coordenada)
	{
		return this.coordenada.distance(coordenada) <= this.radioDeCercania;
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

	public Point getCoordenada() {
		return coordenada;
	}

	public void setCoordenada(Point coordenada) {
		this.coordenada = coordenada;
	}

	public List<String> getListaTags() {
		return listaTags;
	}

	public void setListaTags(List<String> listaTags) {
		this.listaTags = listaTags;
	}
	
	
	
}
