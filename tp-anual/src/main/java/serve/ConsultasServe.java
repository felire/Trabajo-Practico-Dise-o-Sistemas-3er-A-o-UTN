package serve;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;
import ar.utn.frba.disenio.tp_anual.repo.RepoBusquedas;

public class ConsultasServe {

	private static ConsultasServe instance;
	
	public static ConsultasServe getInstance(){
		if(instance == null){
			instance = new ConsultasServe();
		}
		return instance;
	}
	
	public ServeResult filtrar(String fechaDesde, String fechaHasta, String textoCantidad, String terminal){
		ServeResult result = new ServeResult();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate desde = null;
		LocalDate hasta = null;
		Integer cantidad = null;
		if(notEmpty(fechaDesde)){
			try{					
				desde = LocalDate.parse(fechaDesde, formatter);
			}
			catch(Exception e){
				result.addError("Fecha desde invalida");
			}
		}
		
		if(notEmpty(fechaHasta)){
			try{					
				hasta = LocalDate.parse(fechaHasta, formatter);	
			}
			catch(Exception e){
				result.addError("Fecha hasta invalida");
			}
		}
		
		if(notEmpty(textoCantidad)){
			try{					
				cantidad = Integer.parseInt(textoCantidad);
			}
			catch(Exception e){
				result.addError("Cantidad invalida");
			}
		}
		
		if(result.hasErrors()){
			return result;
		}
		
		List<Busqueda> busquedas = RepoBusquedas.getInstance().filtrar(desde, hasta, cantidad, terminal);
		result.addEntity("busquedas", busquedas);
		return result;
	}
	
	private Boolean notEmpty(String s){
		return s != null && !s.equals("");
	}
	
}
