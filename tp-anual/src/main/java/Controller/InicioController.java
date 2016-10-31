package Controller;


import server.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class InicioController {
	public ModelAndView inicio(Request req, Response res){
		if(Session.estaLogeado(req))
		{
			if(req.queryParams("salir") == null)
			{
				return new UserController().darPantalla(Session.getUsuario(req));//Lanzamos la que corresponda la rol
			}
			else{
				return salir(req, res);
			}
		}
		else{
			return new ModelAndView(false, "inicio/inicio.hbs");
		}
	}
	
	public ModelAndView logeo(Request req, Response res){
		String user = req.queryParams("user");
		String pass = req.queryParams("pass");
		if(Session.existeUsuario(user, pass, req)){
			res.redirect("/");
			return new ModelAndView(null, null);//No lanzamos nada, redireccionamos
		}
		else{
			return new ModelAndView(true, "inicio/inicio.hbs");
		}
	}
	
	public ModelAndView salir(Request req, Response res){
		Session.killSession(req);
		res.redirect("/");
		return new ModelAndView(false, "inicio/inicio.hbs");	
	}
}
