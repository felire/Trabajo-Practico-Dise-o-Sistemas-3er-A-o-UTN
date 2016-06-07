package util;

import java.util.ArrayList;
import java.util.List;

public class ReporteParcialPorUsuario {
	String usuario;
	public List<Integer> resultadosParciales;
	public ReporteParcialPorUsuario(String usuario, List<Integer> resultadosParciales){
		this.usuario = usuario;
		this.resultadosParciales = new ArrayList<Integer>();
		this.resultadosParciales.addAll(resultadosParciales);
	}
	
	public String getUsuario(){
		return this.usuario;
	}
	
	public Integer totalResultados(){
		return this.resultadosParciales.stream().mapToInt(valor -> valor.intValue()).sum();
	}
}
