package Controller;

import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.Usuario;
import server.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class UserController {
	public ModelAndView inicio(Request req, Response res){
		if(Session.estaLogeado(req)){
			Usuario user = Session.getUsuario(req);
			return darPantalla(user);
		}
		else{
			res.redirect("/");
			return new ModelAndView(false, "inicio/inicio.hbs");
		}
		//return new ModelAndView(null, "terminal/inicio.hbs");
	}
	
	public ModelAndView darPantalla(Usuario user){
		if(user.getRol() == Rol.ADMINISTRADOR){
			return new ModelAndView(null, "admin/inicio.hbs");
		}
		else{
			return new ModelAndView(null, "user/inicio.hbs");
		}
	}
}
