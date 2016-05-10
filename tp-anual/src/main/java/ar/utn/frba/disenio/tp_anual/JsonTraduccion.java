package ar.utn.frba.disenio.tp_anual;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTraduccion {
	private ObjectMapper objectMapper;
	
	public JsonTraduccion() {
		this.objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public JsonListaBancos traductor(String json){
		try {
			return this.objectMapper.readValue(json, JsonListaBancos.class);
		} catch (IOException e) {
			throw new RuntimeException("Error reading a json", e);
		}
	}
}
