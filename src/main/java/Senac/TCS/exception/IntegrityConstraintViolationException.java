package Senac.TCS.exception;

public class IntegrityConstraintViolationException extends RuntimeException {
	public IntegrityConstraintViolationException (String message) {
		super(message);
	}
}
