package server;

import Controller.TerminalController;
import Controller.UserController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RouterUsuario {
	public static void configure(HandlebarsTemplateEngine engine) {	
		UserController controller = new UserController();
		//Spark.get("/:user", controller::inicio, engine);
	}
}
