package com.example.softwarePatternsCA4.validations;


public class PasswordValidator extends Validator {
	@Override
	public void validate(CustomerInput input) {
		if (input.password == null || input.password.length() < 6) {
			throw new IllegalArgumentException("Password Must Be At LEAST 6 Characters");
		}
		next(input);
	}

}
