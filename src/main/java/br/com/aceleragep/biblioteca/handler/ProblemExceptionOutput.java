package br.com.aceleragep.biblioteca.handler;

import java.util.Calendar;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProblemExceptionOutput {

	private int statusCode;
	private String message;
	private Calendar dateTime = Calendar.getInstance();
	private List<FieldsExceptionOutput> campos;
	
	public ProblemExceptionOutput(int statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	public ProblemExceptionOutput(int statusCode, String message, List<FieldsExceptionOutput> campos) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.campos = campos;
	}	
	
}
