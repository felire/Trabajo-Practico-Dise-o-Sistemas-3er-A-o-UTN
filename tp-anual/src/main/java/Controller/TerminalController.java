package Controller;

import java.util.*;

import javax.persistence.Query;

import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.model.Usuario;
import ar.utn.frba.disenio.tp_anual.observer.ObserverMail;
import ar.utn.frba.disenio.tp_anual.observer.ObserverTerminal;
import ar.utn.frba.disenio.tp_anual.repo.RepoPOIS;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import ar.utn.frba.disenio.tp_anual.servicios.impl.GestorBusquedas;
import server.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import util.Point;
import util.Polygon;
import util.reportes.CreadorDeReportes;

public class TerminalController {
	
	private Map<String, Object> model=new HashMap<>();
	
	public ModelAndView inicio(Request req, Response res){
		if(Session.estaLogeado(req)){
			Usuario usuario = Session.getUsuario(req);
			return darPantalla(usuario, res);
		}
		else{
			res.redirect("/");
			return null;
		}
	}
	
	public ModelAndView darPantalla(Usuario user, Response res){
		HashSet<String> comunas = RepoTerminales.getInstance().getNombreComunas();
		model.put("comunas", comunas);
		if(user.getRol() == Rol.ADMINISTRADOR){
			return new ModelAndView(model, "admin/terminales.hbs");
		}
		else{
			res.redirect("/");
			return null;
		}
	}
	
	public ModelAndView busqueda(Request req, Response res){
	    String comuna = req.queryParams("comuna");
		model.put("busquedas", RepoTerminales.getInstance().buscarPorComuna(comuna));
		model.put("error", false);
		return new ModelAndView(model, "admin/terminales.hbs");
	}
	
	public ModelAndView terminalExacta(Request req, Response res){
		if(Session.estaLogeado(req)){
			Usuario usuario = Session.getUsuario(req);
			if(usuario.getRol() == Rol.ADMINISTRADOR){
				return accionar(req, res);
			}
			else{
				res.redirect("/");
				return null;
			}
		}
		else{
			res.redirect("/");
			return null;
		}
	}
	
	public ModelAndView accionar(Request req, Response res){
		Long id = Long.parseLong(req.params("id"));
		if (req.params("id")== "nueva"){
		return new ModelAndView(model,"admin/nuevaTerminal.hbs");
		}
		else{
			Terminal terminal = RepoTerminales.getInstance().buscarPorID(id);
			model.put("terminal", terminal);
			if(req.queryParams("editar") != null){
				HashSet<String> comunas = RepoTerminales.getInstance().getNombreComunas();
				model.put("comunas", comunas);
				return new ModelAndView(model, "admin/editarTerminal.hbs");
			}
			if(req.queryParams("borrar")!=null){
				RepoTerminales.getInstance().borrarTerminal(terminal);
				res.redirect("/terminales");
				return null;
			}
			return new ModelAndView(terminal,"admin/terminal.hbs");
		}
	}
	
	public ModelAndView agregarTerminal(Request req, Response res){
		if(Session.estaLogeado(req)){
			Usuario usuario = Session.getUsuario(req);
			if(usuario.getRol() == Rol.ADMINISTRADOR){
				return new ModelAndView(model,"admin/nuevaTerminal.hbs");
				}
			else{
				res.redirect("/");
				return null;
			}
		}
		else{
			res.redirect("/");
			return null;
		}
	}
	
	public ModelAndView crearTerminal(Request req, Response res){
		String nombre = req.queryParams("nombre");
		String comuna = req.queryParams("comuna");
		String checkMail = req.queryParams("obsMail");
		String checkBusqueda = req.queryParams("obsBusqueda");
		String user = req.queryParams("user");
		String pass = req.queryParams("pass");
		Terminal terminal = new Terminal(nombre, 10);
		terminal.setUsuario(new Usuario(user,pass, Rol.TERMINAL));
		terminal.setearComuna(comuna);
		if(checkMail != null) terminal.addObserver(new ObserverMail());
		if (checkBusqueda != null) terminal.addObserver(new GestorBusquedas(new CreadorDeReportes()));
		RepoTerminales.getInstance().registrarTerminal(terminal);
		res.redirect("/terminales");
		return null;
	}
	
	public ModelAndView actualizar(Request req, Response res){
		Long id = Long.parseLong(req.params("id"));
		String nombre = req.queryParams("nombre");
		String comuna = req.queryParams("comuna");
		String checkMail = req.queryParams("obsMail");
		String checkBusqueda = req.queryParams("obsBusqueda");
		String user = req.queryParams("user");
		String pass = req.queryParams("pass");
		Terminal terminal = RepoTerminales.getInstance().buscarPorID(id);
		terminal.setNombre(nombre);
		terminal.setearComuna(comuna);
		terminal.setUsuario(new Usuario(user,pass,Rol.TERMINAL));
		List<ObserverTerminal> acciones = new ArrayList<ObserverTerminal>();
		if(checkMail != null) acciones.add(new ObserverMail());
		if(checkBusqueda != null){
			if (terminal.tieneGestor())	acciones.add(terminal.getGestor());
			else acciones.add(new GestorBusquedas(new CreadorDeReportes()));
		}
		terminal.actualizarObservers(acciones);		
		RepoTerminales.getInstance().actualizarObjeto(terminal);
		res.redirect("/terminales");
		return null;
	}
}
