package com.example.softwarePatternsCA4.factory;


import com.example.softwarePatternsCA4.entity.Customer;


public class UserFactory {
	
	public static Customer createCustomer(String username, String password, String email, String shippingAddress, String paymentMethod, boolean isAdmin) {
		Customer customer = new Customer();
		customer.setUsername(username);
		customer.setPassword(password);
		customer.setEmail(email);
		customer.setShippingAddress(shippingAddress);
		customer.setPaymentMethod(paymentMethod);
		customer.setRole(isAdmin ? "ROLE_ADMIN" : "ROLE_CUSTOMER");
		return customer;
	}

}
