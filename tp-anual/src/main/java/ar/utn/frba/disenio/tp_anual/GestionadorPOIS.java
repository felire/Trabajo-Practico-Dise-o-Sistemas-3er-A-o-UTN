package ar.utn.frba.disenio.tp_anual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class GestionadorPOIS
{
	private List<POI> listaPOIs;
	private List<BuscadorExterno> buscadoresExternos;
	private BuscadorCGP buscadorCGP;
	private BuscadorBanco buscadorBanco;
	private int contadorID=1;
	
	//Consulta
	public List<POI> buscarPOIs(String palabraClave){
		return this.filtrarPOIs(palabraClave);
	};
	
	//Alta
	public void altaPOI(POI nuevoPOI){
		nuevoPOI.setID(contadorID);
		listaPOIs.add(nuevoPOI);
		contadorID++;
	}

	
	//Baja
	public void bajaPOI(int iD){
		listaPOIs.remove(buscarPorID(iD));
	}
	
	//Buscar POI por ID
	public POI buscarPorID(int iD){
		return listaPOIs.stream().filter(poi -> poi.getID()== iD).findFirst().get();
	}
	
	private POI buscarPOIporNombre() {
		return null;
	}

	//Modificación
	
	public void agregarBuscadorExterno(BuscadorExterno buscador){
		buscadoresExternos.add(buscador);
	}
	
	public GestionadorPOIS()
	{
		listaPOIs = new ArrayList<POI>();
		buscadoresExternos = new ArrayList<BuscadorExterno>();
	}
	public List<POI> filtrarPOIs(String palabraClave)
	{
		List<POI> resultados=new ArrayList<POI>();
		resultados.addAll(this.filtrarPOIsInterno(palabraClave));
		buscadoresExternos.stream().forEach(servicioExterno->resultados.addAll(servicioExterno.filtrar(palabraClave, null)));
		return resultados;
	}
	
	public List<POI> filtrarPOIs(String palabraClave, String servicio)
	{
		List<POI> resultados=new ArrayList<POI>();
		resultados.addAll(this.filtrarPOIsInterno(palabraClave));
		buscadoresExternos.stream().forEach(servicioExterno->resultados.addAll(servicioExterno.filtrar(palabraClave, servicio)));
		return resultados;
	}
	
	public List<POI> filtrarPOIsInterno(String palabraClave)
	{
		List<POI> resultados=new ArrayList<POI>();
		resultados.addAll(listaPOIs.stream().filter(poi -> poi.esBuscado(palabraClave)).collect(Collectors.toList()));
		return resultados;
	}
	public void deletePOI(POI poi)
	{
		listaPOIs.remove(poi);
	}

}
