package Controller;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class TerminalController {
	public ModelAndView inicio(Request req, Response res){
		return new ModelAndView(null, "terminal/inicio.hbs");
	}
}
