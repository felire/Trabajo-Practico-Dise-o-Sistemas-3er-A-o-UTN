package ar.utn.frba.disenio.tp_anual.schedule.process;

public class ProcesoErrorReintentar implements ProcesoError{
	Integer cantidadReintentos;
	public ProcesoErrorReintentar(Integer cantidad){
		this.cantidadReintentos = cantidad;
	}
	@Override
	public void accion(ProcesoGeneral proceso) {
		Integer intentos = 0;
		proceso.setEstado(ProcesoStatus.ERROR);
		while(intentos < cantidadReintentos){
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
