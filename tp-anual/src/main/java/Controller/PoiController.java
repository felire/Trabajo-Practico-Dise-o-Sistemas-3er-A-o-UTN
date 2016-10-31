package Controller;

import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.Usuario;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorPOIs;
import server.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class PoiController {
	public ModelAndView inicio(Request req, Response res){
		if(Session.estaLogeado(req)){
			Usuario usuario = Session.getUsuario(req);
			return darPantalla(usuario);
		}
		else{
			res.redirect("/");
			return null;
		}
	}
	
	public ModelAndView darPantalla(Usuario user){
		if(user.getRol() == Rol.ADMINISTRADOR){
			return new ModelAndView(user, "admin/inicio.hbs");
		}
		else{
			return new ModelAndView(null, "user/inicio.hbs");
		}
	}
	
	public ModelAndView busqueda(Request req, Response res){
		if(Session.getUsuario(req).getRol() == Rol.ADMINISTRADOR){
			return new ModelAndView(null, "admin/inicio.hbs");
		}
		else{
			String palabraClave = req.queryParams("palabraClave");
			String servicio = req.queryParams("servicio");
			List<POI> lista = BuscadorPOIs.getInstance().buscarPOIs(palabraClave, servicio);
			return new ModelAndView(lista, "user/inicio.hbs");
		}
	}
	
	public ModelAndView poiExacto(Request req, Response res){
		if(Session.estaLogeado(req)){
			Usuario usuario = Session.getUsuario(req);
			return darPantallaPoiExacto(usuario, req);
		}
		else{
			res.redirect("/");
			return null;
		}
	}
	
	public ModelAndView darPantallaPoiExacto(Usuario user, Request req){
		if(user.getRol() == Rol.ADMINISTRADOR){
			return new ModelAndView(null, "admin/inicio.hbs");
		}
		else{
			Integer id = Integer.parseInt(req.params("id"));
			POI poi = RepoPOIS.getInstance().buscarPorID(id);
			return new ModelAndView(poi, "user/poi.hbs");
		}
	}
	
}
