package server;

import Controller.InicioController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class RouterInicio {
	public static void configure(HandlebarsTemplateEngine engine) {	
		InicioController controller = new InicioController();
		Spark.get("/", controller::inicio, engine);
		Spark.post("/",controller::logeo, engine);
		//Spark.delete("/", controller::salir, engine);
	}
}
