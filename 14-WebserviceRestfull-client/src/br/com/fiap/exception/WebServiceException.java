package br.com.fiap.exception;

@SuppressWarnings("serial")
public class WebServiceException extends Exception {

	public WebServiceException() {
		super();
	}

	public WebServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public WebServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public WebServiceException(String message) {
		super(message);
	}

	public WebServiceException(Throwable cause) {
		super(cause);
	}
	
}
