package com.example.softwarePatternsCA4.facade;


import org.springframework.stereotype.Component;
import com.example.softwarePatternsCA4.entity.Cart;
import com.example.softwarePatternsCA4.service.CartService;
import com.example.softwarePatternsCA4.service.CustomerService;
import com.example.softwarePatternsCA4.service.EmailService;


@Component
public class CheckoutFacade {
	
	private final CartService cartService;
    private final CustomerService customerService;
    private final EmailService emailService;

    public CheckoutFacade(CartService cartService, CustomerService customerService, EmailService emailService) {
        this.cartService = cartService;
        this.customerService = customerService;
        this.emailService = emailService;
    }

    public String completeCheckout(Long customerId) {
        Cart cart = cartService.getCartByCustomerId(customerId);

        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Error - Can Not Check Out Cart That is Empty");
        }

        cartService.checkout(customerId);

        String email = customerService.getCustomerEmail(customerId);
        emailService.sendConfirmation(email, "Checkout Complete", "Thanks For Your Purchase!");

        return "Checkout successful for Customer ID: " + customerId;
    }

}
