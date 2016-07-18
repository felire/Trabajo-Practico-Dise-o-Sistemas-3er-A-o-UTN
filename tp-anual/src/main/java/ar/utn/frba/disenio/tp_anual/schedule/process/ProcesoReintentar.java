package ar.utn.frba.disenio.tp_anual.schedule.process;

public class ProcesoReintentar extends ProcesoDecorado{
	private Integer cantidadReintentos;
	public ProcesoReintentar(ProcesoGeneral proceso) {
		super(proceso);
	}
	public void setCantidadIntentos(Integer cantidad){
		this.cantidadReintentos = cantidad;
	}
	@Override
	public void accion() throws Exception {
		Integer intentos = 0;
		proceso.setEstado(ProcesoStatus.ERROR);
		while(intentos <= cantidadReintentos){
			try{
				proceso.accion();
				proceso.setEstado(ProcesoStatus.SUCCESS);
				break;
			}
			catch(Exception e){
				intentos++;
			}
		}
		
	}
	

}
