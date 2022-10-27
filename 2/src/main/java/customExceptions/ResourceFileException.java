package customExceptions;

public class ResourceFileException extends MasterException{

	public ResourceFileException(String message) {
		super(message);
	}
	
	public ResourceFileException(String message, Throwable cause) {
		super(message, cause);
	}

}
