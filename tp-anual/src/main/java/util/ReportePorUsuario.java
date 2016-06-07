package util;

public class ReportePorUsuario {
	private String usuario;
	private Integer busquedas;
	public ReportePorUsuario(String usuario, Integer busquedas){
		this.usuario = usuario;
		this.busquedas = busquedas;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public Integer getBusquedas() {
		return busquedas;
	}
	public void setBusquedas(Integer busquedas) {
		this.busquedas = busquedas;
	}
}
