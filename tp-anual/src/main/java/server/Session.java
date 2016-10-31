package server;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import ar.utn.frba.disenio.tp_anual.model.Usuario;
import spark.Request;

public class Session{
	public static Session instancia = new Session();
	public static final String NOMBREUSER = "user";
	public static Boolean existeUsuario(String id,String pass, Request req){
		EntityManager entity = PerThreadEntityManagers.getEntityManager();
		Usuario usuario = entity.find(Usuario.class, id);
		//System.out.println(entity.find(Usuario.class, "felire"));
		if(usuario == null){
			System.out.println("No existe");
			return false;
		}
		else{
			if(usuario.getPass() == pass)
			{
				req.session().attribute(NOMBREUSER, usuario);
				return true;
			}
			else{
				System.out.println("No pass");
				return false;
			}
			
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
