package server;

import Controller.ConsultasController;
import Controller.TerminalController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RouterConsultas {
	public static void configure(HandlebarsTemplateEngine engine) {	
		ConsultasController controller = new ConsultasController();
		Spark.get("/consultas", controller::inicio, engine);
		Spark.get("/filtrarConsultas", controller::filtrar, engine);
	}
}
