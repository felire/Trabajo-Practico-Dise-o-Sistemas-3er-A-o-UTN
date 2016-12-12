package server;

import java.util.List;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.model.Usuario;
import spark.Request;

public class Session{
	public static Session instancia = new Session();
	public static final String NOMBREUSER = "user";
	public static Boolean existeUsuario(String id,String pass, Request req){
		EntityManager entity = PerThreadEntityManagers.getEntityManager();
		Usuario usuario = entity.find(Usuario.class, id);
		if(usuario == null){
			System.out.println("No existe");
			return false;
		}
		else{
			if(usuario.getPass().equals(pass))
			{
				List<Terminal> lista = (List<Terminal>)entity.createNativeQuery("SELECT Terminal.id, Terminal.nombre, Terminal.comuna_id FROM Terminal WHERE usuario_id = :usuario")
				.setParameter("usuario", usuario.getUser()).getResultList();				
				if(usuario.getRol() == Rol.TERMINAL && lista.isEmpty())
				{
					return false;
				}
				req.session().attribute(NOMBREUSER, usuario);
				return true;
			}
			else{
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
	
	public static void killSession(Request req){
		Usuario usuario = req.session().attribute(NOMBREUSER);
		req.session().removeAttribute(NOMBREUSER);
	}
	
	public static Usuario getUsuario(Request req){
		return req.session().attribute(NOMBREUSER);
	}
}
