package Controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PoiController {
	public ModelAndView inicio(Request req, Response res){
		return new ModelAndView(null, "poi/inicio.hbs");
	}
	
	public ModelAndView inicioTerminal(Request req, Response res){
		return new ModelAndView(null, "poi/inicioPoiTerminal.hbs");
	}
}
