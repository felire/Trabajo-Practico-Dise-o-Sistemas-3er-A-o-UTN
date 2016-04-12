package ar.edu.utn.frba.dds.tpa;

public class Direccion {
	private String callePrincipal;
	private String[] entreQueCalles;
	private int numero;
	public Direccion(String callePrincipal, String calleEncierra1, String calleEncierra2, int numero)
	{
		this.callePrincipal = callePrincipal;
		this.entreQueCalles = new String[2];
		this.entreQueCalles[0] = calleEncierra1;
		this.entreQueCalles[1] = calleEncierra2;
		this.numero = numero;
	}
}
