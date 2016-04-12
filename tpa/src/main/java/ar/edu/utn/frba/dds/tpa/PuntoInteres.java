package ar.edu.utn.frba.dds.tpa;

public class PuntoInteres {
	private String nombre;
	private Direccion direccion;
	private int piso;
	private String departamento;
	private int unidad;
	private int codigoPostal;
	private String localidad;
	private String barrio;
	private String provincia;
	private String pais;
	private Double latitud;
	private Double longitud;
	private tipoPuntoInteres tipo; //Despues podemos ver si nos conviene usar herencia o composicion. Pero como un punto de inteeres puede cambiar y ser otra cosa me parecio mejor composicion.
	
	
	public boolean menosDeXMetros(int metros , PuntoInteres otroPunto)
	{
		double distancia = Math.sqrt(Math.pow((this.latitud - otroPunto.getLatitud()),2) + Math.pow((this.longitud - otroPunto.getLongitud()),2));
		return (distancia < metros);
	}
	public boolean esValido()
	{
		return (this.nombre != null && this.latitud != null && this.longitud != null);
	}

	public Double getLatitud() {
		return latitud;
	}


	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}


	public Double getLongitud() {
		return longitud;
	}


	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
}
