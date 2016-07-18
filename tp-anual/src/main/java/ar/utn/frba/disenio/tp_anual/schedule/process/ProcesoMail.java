package ar.utn.frba.disenio.tp_anual.schedule.process;

public class ProcesoMail extends ProcesoDecorado{

	public ProcesoMail(ProcesoGeneral proceso) {
		super(proceso);
	}

	@Override
	public void accion(){
		try{
			proceso.accion();
			proceso.setEstado(ProcesoStatus.SUCCESS);
		}
		catch(Exception e){
			proceso.setEstado(ProcesoStatus.ERROR);
		}
		this.mandarMail();
			
	}
	public void mandarMail(){
		//Aca mockeamos
	}

}
