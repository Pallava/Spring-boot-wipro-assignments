package b3.pa832670.foundation.bankAccount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerExceptions extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomerExceptions(String message) {
		super(message);
	}
}
