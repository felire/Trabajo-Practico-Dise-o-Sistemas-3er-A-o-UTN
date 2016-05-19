package ar.utn.frba.disenio.tp_anual;

import java.util.ArrayList;
import java.util.List;

public class GeneradorDeID {
	private List<Integer> ids;
	
	public GeneradorDeID() {
		super();
		ids = new ArrayList<Integer>();
		ids.add(-1);
	}

	public Integer generarID(){
		Integer nuevoID = generarNuevoID();
		ids.add(nuevoID);
		return nuevoID;
	}

	private Integer generarNuevoID() {
		return (ids.get(ids.size()-1)) +1;
	};
		
}
