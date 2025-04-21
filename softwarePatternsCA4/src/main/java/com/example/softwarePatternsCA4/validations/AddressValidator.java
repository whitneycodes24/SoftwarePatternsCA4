package com.example.softwarePatternsCA4.validations;


public class AddressValidator extends Validator {
	@Override
	public void validate(CustomerInput input) {
		if (input.shippingAddress == null || input.shippingAddress.trim().isEmpty()) {
			throw new IllegalArgumentException("Must Have Shipping Address - Can Not Be Empty");
		}
		next(input);
	}
}
