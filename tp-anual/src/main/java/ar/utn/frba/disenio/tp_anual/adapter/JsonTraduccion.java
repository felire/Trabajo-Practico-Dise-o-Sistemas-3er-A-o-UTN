package ar.utn.frba.disenio.tp_anual.adapter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import ar.utn.frba.disenio.tp_anual.json.JsonBaja;
import ar.utn.frba.disenio.tp_anual.json.JsonBajaFecha;
import ar.utn.frba.disenio.tp_anual.json.JsonBanco;
import ar.utn.frba.disenio.tp_anual.schedule.process.ProcesoBajaPOIs;

public class JsonTraduccion {
	private ObjectMapper objectMapper;
	
	public JsonTraduccion() {
		this.objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public List<JsonBanco> traductor(String json){
		try {
			
			ArrayList<JsonBanco> bancos = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, JsonBanco.class));
			return bancos;
			
		} catch (IOException e) {
			throw new RuntimeException("Error al leer el Json", e);
		}
	}
	
	public List<JsonBaja> traductorBajaPOIs(String json){
		try {
			
			ArrayList<JsonBaja> pois = objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, JsonBaja.class));
			return pois;
			
		} catch (IOException e) {
			throw new RuntimeException("Error al leer el Json", e);
		}
	}
	
	public LocalDateTime sacarFecha(String fechaParametro){//asumimos que fecha viene como anio-mes-dia-hora-minutos
		String[] listaCosas = fechaParametro.split("-");
		LocalDateTime fecha = LocalDateTime.of(Integer.parseInt(listaCosas[0]),Integer.parseInt(listaCosas[1]),Integer.parseInt(listaCosas[2]),Integer.parseInt(listaCosas[3]),Integer.parseInt(listaCosas[4]));
		return fecha;
	}
	
	public List<JsonBajaFecha> traductorBaja(String json){
		List<JsonBajaFecha> lista = new ArrayList<JsonBajaFecha>();
		this.traductorBajaPOIs(json).stream().forEach(poi -> lista.add(new JsonBajaFecha(poi.getId(),this.sacarFecha(poi.getFecha()))));
		return lista;
	}
	
}