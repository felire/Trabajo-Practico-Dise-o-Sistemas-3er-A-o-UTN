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
	
	private static BuscadorPOIs instance = null;
	
	public static BuscadorPOIs getInstance(){
		if(instance == null){
			instance = new BuscadorPOIs();
		}
		return instance;
	}
	
	private BuscadorPOIs() {
		buscadoresExternos = new ArrayList<>();	
		//buscadoresExternos.add(new BuscadorCGP());
		//buscadoresExternos.add(new BuscadorBanco());
	}
	
	public List<POI> buscarPOIs(String palabraClave, String servicio){
		return this.getResultado(palabraClave, servicio);
	}

	private List<POI> getResultado(String palabraClave, String servicio) {
		List<POI> resultados=new ArrayList<POI>();
		resultados.addAll(RepoPOIS.getInstance().buscarPOIs(palabraClave)); //locales
		buscadoresExternos.stream().forEach(servicioExterno->resultados.addAll(servicioExterno.filtrar(palabraClave, servicio)));
		return resultados;
	}
	
	public void agregarBuscadorExterno(BuscadorExterno buscador){
		buscadoresExternos.add(buscador);
	}
}
