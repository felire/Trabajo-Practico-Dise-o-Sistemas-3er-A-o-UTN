package util;

public class Timer {
	Double inicio;
	public void iniciar(){
		this.inicio = (double) System.currentTimeMillis();
	}
	public Double finalizar(){
		return (System.currentTimeMillis() - this.inicio)/1000;
	}
}
