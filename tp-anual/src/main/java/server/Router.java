package server;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import spark.Filter;
import spark.Spark;
import static spark.Spark.after;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.utils.BooleanHelper;
import spark.utils.HandlebarsTemplateEngineBuilder;

public class Router {
	public static void configure() {
		HandlebarsTemplateEngine engine = HandlebarsTemplateEngineBuilder
				.create()
				.withDefaultHelpers()
				.withHelper("isTrue", BooleanHelper.isTrue)
				.build();

		Spark.staticFiles.location("/public");
		Spark.after((req,res)->{PerThreadEntityManagers.getEntityManager();
		PerThreadEntityManagers.closeEntityManager();});
		
		RouterInicio.configure(engine);
		RouterPoi.configure(engine);
		RouterTerminal.configure(engine);
		RouterUsuario.configure(engine);
		RouterConsultas.configure(engine);
	}
}
