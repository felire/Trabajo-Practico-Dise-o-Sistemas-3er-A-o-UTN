package server;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.utn.frba.disenio.tp_anual.model.Usuario;
import spark.Request;

public class Session {
	public static final String NOMBREUSER = "user";
	public static Boolean existeUsuario(String id, Request req){
		EntityManager entity = PerThreadEntityManagers.getEntityManager();
		Usuario usuario = entity.find(Usuario.class, id);
		if(usuario == null){
			return false;
		}
		else{
			req.session().attribute(NOMBREUSER, usuario);
			return true;
		}
	}
	public static Boolean estaLogeado(Request req){
		Usuario usuario = req.session().attribute(NOMBREUSER);
		if(usuario == null){
			return false; //no esta logeado
		}
		else{
			return true;
		}
	}
}
