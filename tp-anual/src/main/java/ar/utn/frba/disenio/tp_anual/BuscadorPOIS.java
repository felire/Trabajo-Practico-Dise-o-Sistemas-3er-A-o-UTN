package ar.utn.frba.disenio.tp_anual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Hello world!
 *
 */
public class BuscadorPOIS
{
	private List<POI> listaPOIs;
	public BuscadorPOIS()
	{
		listaPOIs = new ArrayList<POI>();
	}
	public List<POI> filtrarPOIs(String palabraClave)
	{
		return  listaPOIs.stream().filter(poi -> poi.esBuscado(palabraClave)).collect(Collectors.toList());
	}
	public void addPOI(POI poi)
	{
		listaPOIs.add(poi);
	}
	public void deletePOI(POI poi)
	{
		listaPOIs.remove(poi);
	}
    public static void main( String[] args )
    {
    	
    }
}
