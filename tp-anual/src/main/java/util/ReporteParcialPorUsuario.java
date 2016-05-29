package util;

import java.util.ArrayList;
import java.util.List;

public class ReporteParcialPorUsuario {
	String usuario;
	List<Integer> resultadosParciales;
	public ReporteParcialPorUsuario(String usuario, List<Integer> resultadosParciales){
		this.usuario = usuario;
		this.resultadosParciales = new ArrayList<Integer>();
		this.resultadosParciales.addAll(resultadosParciales);
	}
}
