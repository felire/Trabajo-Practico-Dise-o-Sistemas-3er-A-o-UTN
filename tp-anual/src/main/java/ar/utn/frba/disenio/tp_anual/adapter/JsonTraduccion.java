package ar.utn.frba.disenio.tp_anual.adapter;

import java.io.IOException;
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
	
	public List<JsonBaja> traductorBajaPOIs(ProcesoBajaPOIs proceso){
		try {
			
			ArrayList<JsonBaja> pois = objectMapper.readValue(proceso.getJson(), objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, JsonBaja.class));
			return pois;
			
		} catch (IOException e) {
			throw new RuntimeException("Error al leer el Json", e);
		}
	}
	
	public List<JsonBajaFecha> traductorBaja(ProcesoBajaPOIs proceso){
		List<JsonBajaFecha> lista = new ArrayList<JsonBajaFecha>();
		this.traductorBajaPOIs(proceso).stream().forEach(poi -> lista.add(new JsonBajaFecha(poi)));
		return lista;
	}
	
}