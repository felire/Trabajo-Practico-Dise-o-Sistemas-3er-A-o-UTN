package server;

import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorPOIs;
import spark.Spark;
import spark.debug.DebugScreen;

public class Server {
	public static void main(String[] args) {
		new Bootstrap().init();
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configure();
	}
}
