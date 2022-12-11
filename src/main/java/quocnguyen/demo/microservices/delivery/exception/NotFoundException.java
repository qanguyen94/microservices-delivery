package quocnguyen.demo.microservices.delivery.exception;

public class NotFoundException extends RuntimeException {
	public NotFoundException(String message) {
		super(message);
	}
}
