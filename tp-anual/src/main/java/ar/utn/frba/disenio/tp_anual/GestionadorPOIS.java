package ar.utn.frba.disenio.tp_anual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class GestionadorPOIS
{
	private List<POI> listaPOIs;
	private BuscadorCGP buscadorCGP;
	private BuscadorBanco buscadorBanco;
	private int contadorID=1;
	
	//Consulta
	public List<POI> buscarPOIs(String palabraClave){
		return this.filtrarPOIs(palabraClave);
	};
	
	//Alta
	public void altaPOI(POI nuevoPOI){
		listaPOIs.add(nuevoPOI);
	}

	
	//Baja
	public void bajaPOI(String nombre){
		POI poi = this.filtrarPOIs(nombre).get(0);
		listaPOIs.remove(poi);
	}
	
	private POI buscarPOIporNombre() {
		// TODO Auto-generated method stub
		return null;
	}

	//Modificación
	
	
	public void setBuscadorCGP(BuscadorCGP buscadorCGP) {
		this.buscadorCGP = buscadorCGP;
	}
	public void setBuscadorBanco(BuscadorBanco buscadorBanco) {
		this.buscadorBanco = buscadorBanco;
	}
	public GestionadorPOIS()
	{
		listaPOIs = new ArrayList<POI>();
	}
	public List<POI> filtrarPOIs(String palabraClave)
	{
		return  listaPOIs.stream().filter(poi -> poi.esBuscado(palabraClave)).collect(Collectors.toList());
	}
	public void addPOI(POI poi)
	{
		poi.setID(contadorID);
		listaPOIs.add(poi);
		contadorID++;
	}
	
	public void deletePOI(POI poi)
	{
		listaPOIs.remove(poi);
	}

	public List<POI> filtrarCGPs(String palabraClave){
		return buscadorCGP.filtrarCGPs(palabraClave);
	}
	public List<SucursalBanco> filtrarBancos(String palabraClave, String servicio){
		return buscadorBanco.filtrarBancos(palabraClave, servicio);
	}
}
