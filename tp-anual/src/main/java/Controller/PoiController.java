package Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.uqbarproject.jpa.java8.extras.PerThreadEntityManagers;

import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.model.Usuario;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import ar.utn.frba.disenio.tp_anual.servicios.impl.BuscadorPOIs;
import server.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import util.Point;

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
			return new ModelAndView(null, "admin/inicio.hbs");
		}
		else{
			return new ModelAndView(null, "user/inicio.hbs");
		}
	}
	
	public ModelAndView busqueda(Request req, Response res){
		if(Session.getUsuario(req).getRol() == Rol.ADMINISTRADOR){
			String nombre = req.queryParams("nombre");
			String tipo = req.queryParams("tipo");
			List<POI> lista = FiltradoTipo.filtrar(nombre, tipo);
			return new ModelAndView(lista, "admin/inicio.hbs");
		}
		else{
			String palabraClave = req.queryParams("palabraClave");
			String servicio = req.queryParams("servicio");
			Terminal terminal = obtenerTerminal(req);
			//List<POI> lista = BuscadorPOIs.getInstance().buscarPOIs(palabraClave, servicio);
			List<POI> lista = terminal.buscar(palabraClave, servicio);
			return new ModelAndView(lista, "user/inicio.hbs");
		}
	}
	
	public Terminal obtenerTerminal(Request req){
		Usuario usuario = Session.getUsuario(req);
		List<Terminal> terminales = RepoTerminales.getInstance().getListaTerminales();
		terminales = terminales.stream().filter(term -> term.getUsuario() != null).collect(Collectors.toList());
		Terminal terminal = terminales.stream().filter(term -> term.getUsuario().getUser().equals(usuario.getUser())).collect(Collectors.toList()).get(0);
		return terminal;
	}
	public ModelAndView poiExacto(Request req, Response res){
		if(Session.estaLogeado(req)){
			Usuario usuario = Session.getUsuario(req);
			return darPantallaPoiExacto(usuario, req, res);
		}
		else{
			res.redirect("/");
			return null;
		}
	}
	
	public ModelAndView darPantallaPoiExacto(Usuario user, Request req, Response res){
		if(user.getRol() == Rol.ADMINISTRADOR){
			Integer id = Integer.parseInt(req.params("id"));
			POI poi = RepoPOIS.getInstance().buscarPorID(id);
			return accionar(req, res);
		}
		else{
			Integer id = Integer.parseInt(req.params("id"));
			POI poi = RepoPOIS.getInstance().buscarPorID(id);
			return new ModelAndView(poi, "user/poi.hbs");
		}
	}
	
	public ModelAndView accionar(Request req, Response res){
		Integer id = Integer.parseInt(req.params("id"));
		POI poi = RepoPOIS.getInstance().buscarPorID(id);
		if(req.queryParams("editar") != null){
			return new ModelAndView(poi, "admin/editarPoi.hbs");
		}
		if(req.queryParams("borrar")!=null){
			RepoPOIS.getInstance().bajaPOI(poi);
			res.redirect("/pois");
			return null;
		}
		return new ModelAndView(poi,"admin/poi.hbs");
	}
	
	public ModelAndView actualizar(Request req, Response res){
		Integer id = Integer.parseInt(req.params("id"));
		String nombre = req.queryParams("nombre");
		double latitud = Double.parseDouble(req.queryParams("latitud"));
		double longitud = Double.parseDouble(req.queryParams("longitud"));
		String direccion = req.queryParams("direccion");
		POI poi = RepoPOIS.getInstance().buscarPorID(id);
		poi.setNombre(nombre);
		poi.setCoordenada(new Point(latitud, longitud));
		poi.setDireccion(direccion);
		RepoPOIS.getInstance().actualizarObjeto(poi);
		res.redirect("/pois");
		return null;
	}
	
}
