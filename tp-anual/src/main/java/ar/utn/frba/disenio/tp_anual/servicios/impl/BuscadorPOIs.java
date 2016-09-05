package ar.utn.frba.disenio.tp_anual.servicios.impl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.servicios.BuscadorExterno;

import javax.swing.Timer;

import javax.persistence.*;

public class BuscadorPOIs {

	private List<BuscadorExterno> buscadoresExternos;
	private RepoPOIS repo;
	
	
	public BuscadorPOIs() {
		buscadoresExternos = new ArrayList<>();
		repo = RepoPOIS.getInstance();	
	}

	
	public List<POI> buscarPOIs(String palabraClave, String servicio){
		return this.getResultado(palabraClave, servicio);
	};

	private List<POI> getResultado(String palabraClave, String servicio) {
		List<POI> resultados=new ArrayList<POI>();
		resultados.addAll(repo.buscarPOIs(palabraClave)); //locales
		buscadoresExternos.stream().forEach(servicioExterno->resultados.addAll(servicioExterno.filtrar(palabraClave, servicio)));
		return resultados;
	}
	
	public void agregarBuscadorExterno(BuscadorExterno buscador){
		buscadoresExternos.add(buscador);
	}
}
