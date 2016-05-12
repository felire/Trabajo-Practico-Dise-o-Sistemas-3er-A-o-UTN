package ar.utn.frba.disenio.tp_anual;

import java.util.List;

public class JsonBanco {
	private String banco;
	private Double x;
	private Double y;
	private String sucursal;
	private String gerente;
	private List<String> servicios;
	public String getBanco() {
		return banco;
	}
	public Double getX() {
		return x;
	}
	public Double getY() {
		return y;
	}
	public String getSucursal() {
		return sucursal;
	}
	public String getGerente() {
		return gerente;
	}
	public List<String> getServicios() {
		return servicios;
	}
	
}
