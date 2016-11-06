package Controller;

import java.util.*;

import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.model.Usuario;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import server.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import util.Polygon;

public class TerminalController {
	
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
	
	public ModelAndView darPantalla(Usuario user, Response res){
		List<Polygon> comunas = RepoTerminales.getInstance().getComunas();
		model.put("comunas", comunas);
		if(user.getRol() == Rol.ADMINISTRADOR){
			return new ModelAndView(model, "admin/terminales.hbs");
		}
		else{
			res.redirect("/");
			return null;
		}
	}
	
	public ModelAndView busqueda(Request req, Response res){
		String comuna = req.queryParams("comuna");
		model.put("busquedas", RepoTerminales.getInstance().buscarPorComuna(comuna));
		return new ModelAndView(model, "admin/terminales.hbs");
	}
	
	public ModelAndView terminalExacta(Request req, Response res){
		return null;
	}
}
