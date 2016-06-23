package ar.utn.frba.disenio.tp_anual.gestion;

import java.util.ArrayList;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.gestion.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.gestion.Terminal;

public class RepoTerminales {
	
	private static RepoTerminales instance;
	List<Terminal> listaTerminales;
	
	public static RepoTerminales getInstance(){
		if(instance == null){
			instance= new RepoTerminales();
		}
		return instance;
	}
	public RepoTerminales(){
		listaTerminales=new ArrayList<Terminal>();
	}
	public List<Terminal> getListaTerminales(){
		return listaTerminales;
	}
}
