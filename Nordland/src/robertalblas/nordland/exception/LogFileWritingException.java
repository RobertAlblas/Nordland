package robertalblas.nordland.exception;

public class LogFileWritingException extends RuntimeException {

	private static final long serialVersionUID = -8260460131978690600L;
	
	public LogFileWritingException(String message){
		super(message);
	}

}
