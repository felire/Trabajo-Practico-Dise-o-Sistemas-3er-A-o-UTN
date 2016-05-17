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
