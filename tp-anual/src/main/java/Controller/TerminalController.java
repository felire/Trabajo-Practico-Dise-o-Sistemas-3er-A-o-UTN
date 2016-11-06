package Controller;

import java.util.*;

import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.model.Usuario;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
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
		try{
		    String comuna = req.queryParams("comuna");
			model.put("busquedas", RepoTerminales.getInstance().buscarPorComuna(comuna));
			model.put("error", false);
		}
		catch (Exception e){
			model.put("error", true);
		}
		return new ModelAndView(model, "admin/terminales.hbs");
	}
	
	public ModelAndView terminalExacta(Request req, Response res){
		if(Session.estaLogeado(req)){
			Usuario usuario = Session.getUsuario(req);
			if(usuario.getRol() == Rol.ADMINISTRADOR){
				return accionar(req, res);
			}
			else{
				res.redirect("/");
				return null;
			}
		}
		else{
			res.redirect("/");
			return null;
		}
	}
	
	public ModelAndView accionar(Request req, Response res){
		Long id = Long.parseLong(req.params("id"));
		Terminal terminal = RepoTerminales.getInstance().buscarPorID(id);
		if(req.queryParams("editar") != null){
			return new ModelAndView(terminal, "admin/editarTerminal.hbs");
		}
		if(req.queryParams("borrar")!=null){
			RepoTerminales.getInstance().borrarTerminal(terminal);
			res.redirect("/terminales");
			return null;
		}
		return new ModelAndView(terminal,"admin/terminal.hbs");
	}
}
