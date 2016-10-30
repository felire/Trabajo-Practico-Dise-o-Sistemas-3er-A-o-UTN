package server;

import Controller.TerminalController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RouterTerminal {
	public static void configure(HandlebarsTemplateEngine engine) {	
		TerminalController controller = new TerminalController();
		Spark.get("/terminales", controller::inicio, engine);
	}
}
