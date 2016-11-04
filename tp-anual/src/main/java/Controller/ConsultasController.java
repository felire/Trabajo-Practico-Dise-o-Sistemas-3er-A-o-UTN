package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.utn.frba.disenio.tp_anual.model.Busqueda;
import ar.utn.frba.disenio.tp_anual.model.Rol;
import ar.utn.frba.disenio.tp_anual.model.Terminal;
import ar.utn.frba.disenio.tp_anual.model.Usuario;
import ar.utn.frba.disenio.tp_anual.repo.RepoBusquedas;
import ar.utn.frba.disenio.tp_anual.repo.RepoTerminales;
import server.Session;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ConsultasController {
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
	
	public ModelAndView filtrar(Request req, Response res){
		String desde = req.queryParams("desde");
		String hasta = req.queryParams("hasta");
		Integer cantidad = Integer.parseInt(req.queryParams("cantidad"));
		String terminal = req.queryParams("terminal");
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate fechaDesde = LocalDate.parse(desde, formatter);
		LocalDate fechaHasta = LocalDate.parse(desde, formatter);
		List<Terminal> terminales = RepoTerminales.getInstance().getListaTerminales();
		List<Busqueda> busquedas = RepoBusquedas.getInstance().filtrar(fechaDesde, fechaHasta, cantidad, terminal);
		
		Map<String, List<Terminal>> model = new HashMap<>();
		model.put("terminales", terminales);
		
		return new ModelAndView(model,"admin/consultas.hbs");
	}
	
	public ModelAndView darPantalla(Usuario user, Response res){
		List<Terminal> terminales = RepoTerminales.getInstance().getListaTerminales();
		
		Map<String, Object> model = new HashMap<>();
		model.put("terminales", terminales);
		model.put("user", user);
		
		if(user.getRol() == Rol.ADMINISTRADOR){
			return new ModelAndView(model, "admin/consultas.hbs");
		}
		else{
			res.redirect("/");
			return null;
		}
	}
	
}
