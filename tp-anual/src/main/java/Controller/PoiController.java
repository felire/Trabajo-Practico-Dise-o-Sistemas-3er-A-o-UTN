package Controller;

import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.Usuario;
import server.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PoiController {
	public ModelAndView inicio(Request req, Response res){
		if(Session.estaLogeado(req)){
			Usuario usuario = Session.getUsuario(req);
			return darPantalla(usuario);
		}
		else{
			res.redirect("/");
			return null;
		}
	}
	
	public ModelAndView darPantalla(Usuario user){
		if(user.getRol() == Rol.ADMINISTRADOR){
			return new ModelAndView(user, "admin/inicio.hbs");
		}
		else{
			return new ModelAndView(user, "user/inicio.hbs");
		}
	}
	
	public ModelAndView busqueda(Request req, Response res){
		return null;
	}
	
}
