package ar.utn.frba.disenio.tp_anual;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public class LocalComercial extends POI{

	private Integer radioDeCercania;
	private int horarioArranque;
	private int horarioCierre;
	private List<DayOfWeek> diasDeAtencion;
	public void addDiaLaboral(DayOfWeek dia)
	{
		diasDeAtencion.add(dia);
	}
	public void deleteDiaLaboral(DayOfWeek dia)
	{
		diasDeAtencion.remove(dia);
	}
	public LocalComercial(int radio)
	{
		radioDeCercania = new Integer(radio);
	}
	public Boolean esCercano(Coordenada coordenada) {
		return (distancia(this.getCoordenada(), coordenada) < this.radioDeCercania.intValue());
	}

	public Boolean estaDisponible(LocalDateTime fecha, String valorX) {
		return (diasDeAtencion.contains(fecha.getDayOfWeek()) && fecha.getHour() > horarioArranque && fecha.getHour()< horarioCierre);
	}

}
