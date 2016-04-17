package ar.utn.frba.disenio.tp_anual;

public class FranjaHoraria {

	private Integer desdeHorario;
	private Integer hastaHorario;
	
	public Boolean contiene(Integer hora){
		return (desdeHorario <= hora && hora <= hastaHorario);
	}
	
	public FranjaHoraria(Integer desde, Integer hasta){
		this.desdeHorario = desde;
		this.hastaHorario = hasta;
	}
}
