package exception;

public class POINoExisteException extends Exception {


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public POINoExisteException(String msg){
		super(msg);
	}

	@Override
	public String getMessage() {
		return "Error: alguno de los POI no existe";
	}
	
}
