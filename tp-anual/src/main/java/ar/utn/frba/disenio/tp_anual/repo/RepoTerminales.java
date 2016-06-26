package ar.utn.frba.disenio.tp_anual.repo;

import java.util.ArrayList;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;

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
