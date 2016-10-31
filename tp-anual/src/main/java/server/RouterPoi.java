package server;

import Controller.PoiController;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class RouterPoi {
	public static void configure(HandlebarsTemplateEngine engine) {
		PoiController controller = new PoiController();
		Spark.get("/pois", controller::inicio,engine);
		Spark.get("pois/:id",controller::inicioTerminal, engine);
	}
}
