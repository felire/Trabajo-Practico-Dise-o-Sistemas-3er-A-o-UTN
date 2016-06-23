package ar.utn.frba.disenio.tp_anual;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	
	public List<JsonBajaFecha> traductorBaja(String json){
		List<JsonBajaFecha> lista = new ArrayList<JsonBajaFecha>();
		this.traductorBajaPOIs(json).stream().forEach(poi -> lista.add(new JsonBajaFecha(poi)));
		return lista;
	}
	
}