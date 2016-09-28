package ar.utn.frba.disenio.tp_anual.model;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Property;

import util.BigDecimalConverter;
import util.LocalTimeConverter;
import util.Point;

@Entity
@Table(name = "POI")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TIPO_POI", discriminatorType = DiscriminatorType.STRING)
public abstract class POI { 
	
	@Id
	@org.mongodb.morphia.annotations.Id
	@GeneratedValue
	protected Integer poiID;
	protected String nombre;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "coordenada_id")
	protected Point coordenada;
	
	@ElementCollection
	@CollectionTable(name="Tags", joinColumns=@JoinColumn(name="poi_id"))
	@Column(name="tag")
	protected List<String> listaTags;
	
	protected BigDecimal radioDeCercania;
	
	@SuppressWarnings("unused")
	public POI(){};
	
	public POI(String nombre, Point coordenada)
	{
		this.poiID=0;
		this.nombre=nombre;
		radioDeCercania = new BigDecimal(0.5);
		listaTags = new ArrayList<String>();
		this.coordenada = coordenada;
	}
	
	public Integer getID() {
		return poiID;
	}

	public void setID(Integer iD) {
		poiID = iD;
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
	    return this.nombre.contains(palabraClave) || listaTags.stream().anyMatch(cadena1 -> cadena1.contains(palabraClave)) || this.soyBuscado(palabraClave);
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
	public void actualizarTags(List<String> listaTags){
		listaTags.removeAll(listaTags);
	}
	
	public Boolean esValido(){
		return (this.coordenada != null && this.nombre != null);
	}
	
	public Point getCoordenada() {
		return coordenada;
	}
	
	public List<String> getListaTags(){
		return listaTags;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
