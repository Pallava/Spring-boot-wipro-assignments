package b3.pa832670.foundation.bankAccount.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccountExceptions extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccountExceptions (String message){
		super(message);
	}

}
