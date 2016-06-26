package ar.utn.frba.disenio.tp_anual.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.utn.frba.disenio.tp_anual.model.POI;

public class RepoPOIS
{
	private List<POI> listaPOIs;
	private Integer id;
	private static RepoPOIS instance;
	private Integer generarID() {
		id++;
		return id;
	}
	
	public List<POI> getListaPOIS(){
		return listaPOIs;
	}
	public static RepoPOIS getInstance(){
		if(instance == null){
			instance= new RepoPOIS();
		}
		return instance;
	}
	
	private RepoPOIS()
	{
		listaPOIs = new ArrayList<POI>();
		id = 0;
	}
	
	//Consulta
	public List<POI> buscarPOIs(String palabraClave){
		return this.listaPOIs.stream().filter(poi -> poi.esBuscado(palabraClave)).collect(Collectors.toList());
	};
	
	//Alta
	public void altaPOI(POI nuevoPOI){
		nuevoPOI.setID(this.generarID());
		listaPOIs.add(nuevoPOI);
	}

	//Baja
	public void bajaPOI(POI poi){
		listaPOIs.remove(poi);
	}
	
	//Buscar POI por ID
	public POI buscarPorID(Integer id){
		return listaPOIs.stream().filter(poi -> poi.getID()== id).findFirst().get();
	}
	
	//Modificación
	public void modificarPOI(POI modificado){
		this.bajaPOI(modificado);
		altaPOI(modificado);
	}

}
