package util;

public class ErrorHandler {

	private static Boolean enviarMail = false;
	private static Integer cantidadReintentos = 0;
	
	private static ErrorHandler instance;
	
	public static ErrorHandler getInstance(){
		if(instance == null){
			return new ErrorHandler();
		}
		return instance;
	}
	
	public void handle(ProcesoGeneral proceso){
		proceso.estado = ProcesoStatus.ERROR;
		reintentar(proceso);
		if(proceso.estado == ProcesoStatus.ERROR){
			notificar();
		}
	}

	private void reintentar(ProcesoGeneral proceso) {
		Integer reintentosHechos = 0;
		while(reintentosHechos < cantidadReintentos){
			try {
				proceso.accion();
				proceso.estado = ProcesoStatus.SUCCESS;
				break;
			} catch (Exception e) {
				reintentosHechos ++;
			}
		}
	}

	private static void notificar() {
		if(enviarMail){
			//enviar mail;	
		}
	}

	public void enviarMailOnError(Boolean enviarMail) {
		ErrorHandler.enviarMail = enviarMail;
	}

	public void setCantidadReintentos(Integer cantidadReintentos) {
		ErrorHandler.cantidadReintentos = cantidadReintentos;
	};
	
	
}
