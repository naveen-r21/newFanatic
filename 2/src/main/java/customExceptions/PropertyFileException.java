package customExceptions;

public class PropertyFileException extends MasterException{

	public PropertyFileException(String message) {
		super(message);
	}
	
	public PropertyFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
