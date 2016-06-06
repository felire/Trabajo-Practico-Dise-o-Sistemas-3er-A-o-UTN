package ar.utn.frba.disenio.tp_anual.gestion;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.externo.BuscadorExterno;
import ar.utn.frba.disenio.tp_anual.poi.POI;
import javax.swing.Timer;

public class BuscadorPOIs {

	private List<BuscadorExterno> buscadoresExternos;
	private RepoPOIS repo;
	
	
	public BuscadorPOIs() {
		buscadoresExternos = new ArrayList<>();
		repo = RepoPOIS.getInstance();	
	}

	//Consulta
	public List<POI> buscarPOIs(String palabraClave){
		return this.getResultado(palabraClave, "");
	};

	public List<POI> buscarPOIs(String palabraClave, String servicio){
		return this.getResultado(palabraClave, servicio);
	};

	private List<POI> getResultado(String palabraClave, String servicio) {
		List<POI> resultados=new ArrayList<POI>();
		resultados.addAll(repo.buscarPOIs(palabraClave)); //locales
		if(servicio!=null){ //buscar tambien externos
			buscadoresExternos.stream().forEach(servicioExterno->resultados.addAll(servicioExterno.filtrar(palabraClave, servicio)));
		}
		return resultados;
	}
	
	public void agregarBuscadorExterno(BuscadorExterno buscador){
		buscadoresExternos.add(buscador);
	}
}
