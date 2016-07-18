package ar.utn.frba.disenio.tp_anual.schedule.process;

import java.time.LocalDateTime;

public abstract class ProcesoDecorado extends ProcesoGeneral{
	ProcesoGeneral proceso;
	public ProcesoDecorado(ProcesoGeneral proceso){
		this.proceso = proceso;
	}
	@Override
	public Integer elementosAfectados() {
		return proceso.elementosAfectados();
	}
}
