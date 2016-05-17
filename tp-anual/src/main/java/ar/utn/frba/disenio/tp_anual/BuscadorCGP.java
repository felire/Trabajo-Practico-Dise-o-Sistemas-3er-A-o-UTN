package ar.utn.frba.disenio.tp_anual;

import java.util.ArrayList;
import java.util.List;

public class BuscadorCGP{
	
	private ServicioExternoCGP servicioExterno;

	public List<POI> filtrarCGPs(String palabraClave){//Hardcodeado hasta tener correcto funcionamiento
		servicioExterno.search(palabraClave);
		return new ArrayList<POI>();
	}
}
