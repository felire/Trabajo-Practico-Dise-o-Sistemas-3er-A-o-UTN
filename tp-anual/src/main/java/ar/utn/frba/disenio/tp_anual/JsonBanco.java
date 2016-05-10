package ar.utn.frba.disenio.tp_anual;

import java.util.List;

public class JsonBanco {
	private String banco;
	private long x;
	private long y;
	private String sucursal;
	private String gerente;
	private List<String> servicios;
	public String getBanco() {
		return banco;
	}
	public long getX() {
		return x;
	}
	public long getY() {
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
