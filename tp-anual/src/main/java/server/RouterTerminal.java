package server;

import Controller.TerminalController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RouterTerminal {
	public static void configure(HandlebarsTemplateEngine engine) {	
		TerminalController controller = new TerminalController();
		Spark.get("/terminales", controller::inicio, engine);
		Spark.post("/terminales", controller::busqueda, engine);
		Spark.get("/terminales/nueva", controller::agregarTerminal, engine);
		Spark.post("/terminales/nueva", controller::crearTerminal, engine);
		Spark.get("/terminales/:id", controller::terminalExacta, engine);
		Spark.post("/terminales/:id", controller::actualizar,engine);
	}
}
