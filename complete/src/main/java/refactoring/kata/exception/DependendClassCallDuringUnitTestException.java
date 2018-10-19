package refactoring.kata.exception;

public class DependendClassCallDuringUnitTestException extends RuntimeException {

	public DependendClassCallDuringUnitTestException() {
		super();
	}

	public DependendClassCallDuringUnitTestException(String message, Throwable cause) {
		super(message, cause);
	}

	public DependendClassCallDuringUnitTestException(String message) {
		super(message);
	}

	public DependendClassCallDuringUnitTestException(Throwable cause) {
		super(cause);
	}
}
