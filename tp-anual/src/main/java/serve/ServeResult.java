package serve;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServeResult {

	private List<String> errors = new ArrayList<>();

	private Map<String, Object> entities = new HashMap<>();
	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
	
	public void addError(String error){
		this.errors.add(error);
	}
	
	public Boolean hasErrors(){
		return this.errors.size() > 0;
	}
	
	public void addEntity(String name, Object entity){
		entities.put(name, entity);
	}
	
	public Object getEntity(String name){
		return entities.get(name);
	}
	
}
