package com.example.softwarePatternsCA4.validations;


public class CustomerInput {
	public String username;
	public String password;
	public String email;
	public String shippingAddress;
	public String paymentMethod;
	
	public CustomerInput(String username, String password, String email, String shippingAddress, String paymentMethod) {
		this.username = username;
		this.password = password;
		this.email = email;
		this.shippingAddress = shippingAddress;
		this.paymentMethod = paymentMethod;
	}
}
