package ar.utn.frba.disenio.tp_anual.servicios;

import java.util.List;

import ar.utn.frba.disenio.tp_anual.model.CGP;
import ar.utn.frba.disenio.tp_anual.model.POI;
import ar.utn.frba.disenio.tp_anual.model.SucursalBanco;
import redis.clients.jedis.Jedis;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CachePoisExternos {
	
	private Jedis jedis;
	private Gson gson;
	
	public CachePoisExternos(){
		this.jedis = new Jedis("localhost");
		this.gson = new Gson();
	}

	public List<POI> encontrarBancos(String palabraClave, String servicio){
		Type type = new TypeToken<List<SucursalBanco>>(){}.getType();
		String busqueda = this.stringBusqueda(palabraClave, servicio);
		busqueda = "Banco"+busqueda;
		return gson.fromJson(this.jedis.get(busqueda), type);
	}
	
	public List<POI> encontrarCGP(String palabraClave){
		Type type = new TypeToken<List<CGP>>(){}.getType();
		String busqueda = this.stringBusqueda(palabraClave, null);
		busqueda = "CGP"+busqueda;
		return gson.fromJson(this.jedis.get(busqueda), type);
	}
	
	public void guardarBancos(List<POI> bancos, String palabraClave, String servicio){
		jedis.set("Banco"+palabraClave+"#"+servicio,gson.toJson(bancos));
	}
	
	public void guardarCGPs(List<POI> cgps, String palabraClave){
		jedis.set("CGP"+palabraClave,gson.toJson(cgps));
	}
	
	public String stringBusqueda(String palabraClave, String servicio){
		if(servicio==null){
			return palabraClave;
		}
		else{
			return palabraClave+"#"+servicio;
		}
	}
	
	public void vaciar(){
		jedis.flushDB();
	}
	
	public void borrarClave (String key){
		jedis.del(key);
	}
}
