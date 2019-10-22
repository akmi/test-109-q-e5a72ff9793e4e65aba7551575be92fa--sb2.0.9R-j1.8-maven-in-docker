package org.codejudge.sb.exception;

import org.springframework.util.StringUtils;

public class RangeException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String message = "Internal Server Error!";
	
	public RangeException() {
        super("Internal Server Error!");
    }
	
	public RangeException(String message) {
		super(transformedMessage(message));
		this.message = transformedMessage(message);
    }
	
	public RangeException(String message, Throwable cause) {
        super(transformedMessage(message), cause);
    }
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private static String transformedMessage(String message2) {
		return StringUtils.isEmpty(message2) ? "Internal Server Error!" : message2;
	}
}
