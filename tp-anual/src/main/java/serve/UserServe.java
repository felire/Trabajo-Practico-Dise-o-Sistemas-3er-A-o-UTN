package serve;

import server.Session;
import spark.Request;

public class UserServe {

	private static UserServe instance;
	
	public static UserServe getInstance(){
		if(instance == null){
			instance = new UserServe();
		}
		return instance;
	}
	
	public ServeResult loguear(String user, String pass, Request req){
		ServeResult result = new ServeResult();
		validateUserInputs(user, pass, result);
		if(result.hasErrors()) return result;
		
		if(!Session.existeUsuario(user, pass, req)){
			result.addError("Usuario no existe");
		}
		
		return result;	
	}
	
	private void validateUserInputs(String user, String pass, ServeResult result){
		if(user == null || user.isEmpty()){
			result.addError("Usuario vacio");
		}
		if(pass == null || pass.isEmpty()){
			result.addError("Contrasena vacia");
		}
	}
	
}
