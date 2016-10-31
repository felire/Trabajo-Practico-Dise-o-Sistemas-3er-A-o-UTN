package Controller;

import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.Usuario;
import server.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ConsultasController {
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
		if(user.getRol() == Rol.ADMINISTRADOR){
			return new ModelAndView(user, "admin/consultas.hbs");
		}
		else{
			res.redirect("/");
			return null;
		}
	}
}
