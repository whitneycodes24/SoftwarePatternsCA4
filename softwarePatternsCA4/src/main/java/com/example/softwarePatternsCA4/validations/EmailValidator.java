package com.example.softwarePatternsCA4.validations;


public class EmailValidator extends Validator {
	@Override
	public void validate(CustomerInput input) {
		if (input.email == null || !input.email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new IllegalArgumentException("Email is Invalid - Try Again");
		}
		next(input);
	}
}
