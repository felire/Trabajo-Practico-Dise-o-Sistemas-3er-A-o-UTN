package Controller;


import server.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class InicioController {
	public ModelAndView inicio(Request req, Response res){
		if(Session.estaLogeado(req))
		{
			return new ModelAndView(null, "terminal/inicio.hbs");//Lanzamos la que corresponda la rol
		}
		else{
			return new ModelAndView(false, "inicio/inicio.hbs");
		}
	}
	
	public ModelAndView logeo(Request req, Response res){
		String user = req.queryParams("user");
		String pass = req.queryParams("pass");
		if(Session.existeUsuario(user, pass, req)){
			return new ModelAndView(null, "terminal/inicio.hbs");//Lanzamos la que corresponda la rol
		}
		else{
			return new ModelAndView(true, "inicio/inicio.hbs");
		}
	}
}
