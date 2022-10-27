package customExceptions;

public class DriverException extends MasterException{
	
	public DriverException(String message) {
		super(message);
	}

	public DriverException(String message, Throwable cause) {
		super(message, cause);
	}

}
