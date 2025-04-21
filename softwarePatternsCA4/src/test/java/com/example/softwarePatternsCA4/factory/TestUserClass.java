package com.example.softwarePatternsCA4.factory;


import com.example.softwarePatternsCA4.entity.Customer;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*; 



public class TestUserClass {

    @Test
    void testCreateCustomer() {
        Customer customer = UserFactory.createCustomer(
                "sarahcodes",
                "encodedPass123",
                "sarah@gmail.com",
                "123 Fake Street",
                "Visa",
                false
        );

        assertEquals("sarahcodes", customer.getUsername());
        assertEquals("sarah@gmail.com", customer.getEmail());
        assertEquals("123 Fake Street", customer.getShippingAddress());
        assertEquals("Visa", customer.getPaymentMethod());
        assertEquals("ROLE_CUSTOMER", customer.getRole());
        assertFalse(customer.isAdmin());
    }
}