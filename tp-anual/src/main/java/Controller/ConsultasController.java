package Controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.types.ObjectId;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;
import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.model.Usuario;
import ar.utn.frba.disenio.tp_anual.repo.RepoBusquedas;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import server.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ConsultasController {
	
	private Map<String, Object> model=new HashMap<>();
	
	public ModelAndView inicio(Request req, Response res){
		if(Session.estaLogeado(req)){
			Usuario usuario = Session.getUsuario(req);			
			return darPantalla(usuario, res);
		}
		else{
			res.redirect("/");
			return null;
		}
	}
	
	public ModelAndView filtrar(Request req, Response res){
		try{
			String fechaDesde=req.queryParams("desde");
			String fechaHasta=req.queryParams("hasta");
			String textoCantidad=req.queryParams("cantidad");
			String terminal=req.queryParams("terminal");	
					
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate desde = LocalDate.parse(fechaDesde, formatter);
			LocalDate hasta = LocalDate.parse(fechaHasta, formatter);	
			Integer cantidad = Integer.parseInt(textoCantidad);
			model.put("busquedas", RepoBusquedas.getInstance().filtrar(desde, hasta, cantidad, terminal));//reemplazar por filtrar posta		
			model.put("error", false);
		}
		catch(Exception e){
			model.put("error", true);
		}
		
		return new ModelAndView(model, "admin/consultas.hbs");
	}
	
	public ModelAndView busquedaConcreta(Request req, Response res){				
		ObjectId idBusqueda=new ObjectId(req.params("id"));		
		Busqueda busqueda= RepoBusquedas.getInstance().buscarPorIDTrucho(idBusqueda);//	remplazar por el posta	
		return new ModelAndView(busqueda, "admin/resultado.hbs");
	}	
	
	
	public ModelAndView darPantalla(Usuario user, Response res){
		List<Terminal> terminales = RepoTerminales.getInstance().getListaTerminales();		
		model.put("terminales", terminales);
				
		if(user.getRol() == Rol.ADMINISTRADOR){
			return new ModelAndView(model, "admin/consultas.hbs");
		}
		else{
			res.redirect("/");
			return null;
		}
	}
	
}
