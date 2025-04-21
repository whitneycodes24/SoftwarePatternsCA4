package com.example.softwarePatternsCA4.validations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ValidatorChainTest {

    @Test
    void testInvalidEmailThrowsException() {
        CustomerInput input = new CustomerInput(
            "sarahcodes",
            "password123",
            "sarahgmail.com", 
            "42 Test Street",
            "Visa 1234"
        );

        Validator validator = new EmailValidator();
        validator.linkWith(new PasswordValidator())
                 .linkWith(new AddressValidator());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(input);
        });

        System.out.println(exception.getMessage());

    }

    @Test
    void testValidCustomerInput() {
        CustomerInput input = new CustomerInput(
            "sarahcodes",
            "password123",
            "sarah@gmail.com",
            "42 Test Street",
            "Visa 1234"
        );

        Validator validator = new EmailValidator();
        validator.linkWith(new PasswordValidator())
                 .linkWith(new AddressValidator());

        assertDoesNotThrow(() -> {
            validator.validate(input);
        });
    }

    @Test
    void testShortPasswordThrowsException() {
        CustomerInput input = new CustomerInput(
            "sarahcodes",
            "123", 
            "sarah@gmail.com",
            "42 Test Street",
            "Visa 1234"
        );

        Validator validator = new EmailValidator();
        validator.linkWith(new PasswordValidator())
                 .linkWith(new AddressValidator());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            validator.validate(input);
        });

        assertEquals("Password must be at least 6 characters", exception.getMessage());
    }
}
