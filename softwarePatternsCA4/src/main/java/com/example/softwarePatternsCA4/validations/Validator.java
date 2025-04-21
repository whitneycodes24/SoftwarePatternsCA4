package com.example.softwarePatternsCA4.validations;

public abstract class Validator {
	protected Validator next;
	
	public Validator linkWith(Validator next) {
		this.next = next;
		return next;
	}
	
	public abstract void validate(CustomerInput info);
	
	protected void next(CustomerInput info) {
		if (next != null) next.validate(info);
	}
}
