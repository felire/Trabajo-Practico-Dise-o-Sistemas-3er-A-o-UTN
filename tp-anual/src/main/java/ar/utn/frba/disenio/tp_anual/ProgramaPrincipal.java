package ar.utn.frba.disenio.tp_anual;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.uqbar.geodds.Point;
import org.uqbar.geodds.Polygon;

/**
 * Hello world!
 *
 */
public class ProgramaPrincipal 
{
	private List<POI> listaPOIs;
	public ProgramaPrincipal()
	{
		listaPOIs = new ArrayList<POI>();
	}
	public List<POI> filtrarPOIs(String palabraClave)
	{
		return  listaPOIs.stream().filter(poi -> poi.tengoPalabraClave(palabraClave)).collect(Collectors.toList());
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
