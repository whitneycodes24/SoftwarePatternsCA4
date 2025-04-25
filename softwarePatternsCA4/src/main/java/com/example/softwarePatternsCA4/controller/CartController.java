package com.example.softwarePatternsCA4.controller;

import com.example.softwarePatternsCA4.entity.CartItem;
import com.example.softwarePatternsCA4.entity.Customer;
import com.example.softwarePatternsCA4.facade.CheckoutFacade;
import com.example.softwarePatternsCA4.service.CartService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;
    private final CheckoutFacade checkoutFacade;

    public CartController(CartService cartService, CheckoutFacade checkoutFacade) {
        this.cartService = cartService;
        this.checkoutFacade = checkoutFacade;
    }

    @PostMapping("/add")
    public String addToCart(@RequestParam Long bookId,
                            @RequestParam(defaultValue = "1") int quantity,
                            Principal principal) {
        String username = principal.getName();
        Customer customer = cartService.getCustomerByUsername(username);
        cartService.addToCart(customer.getId(), bookId, quantity);
        return "redirect:/customers/dashboard";
    }


    @GetMapping("/view")
    public List<CartItem> viewCart(@RequestParam Long customerId) {
        return cartService.viewCart(customerId);
    }

    @PostMapping("/clear")
    public void clearCart(@RequestParam Long customerId) {
        cartService.clearCart(customerId);
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestParam Long customerId) {
        cartService.checkout(customerId);
        return ResponseEntity.ok("Checkout has been completed for customer ID: " + customerId);
    }

    @PostMapping("/checkout/facade")
    public ResponseEntity<String> checkoutWithFacade(@RequestParam Long customerId) {
        String result = checkoutFacade.completeCheckout(customerId);
        return ResponseEntity.ok(result);
    }
}
