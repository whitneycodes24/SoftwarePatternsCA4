package com.example.softwarePatternsCA4.controller;


import com.example.softwarePatternsCA4.entity.CartItem;
import com.example.softwarePatternsCA4.entity.Customer;
import com.example.softwarePatternsCA4.service.CartService;
import com.example.softwarePatternsCA4.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/cart")
public class CartPageController {

    private final CartService cartService;
    private final CustomerService customerService;

    public CartPageController(CartService cartService, CustomerService customerService) {
        this.cartService = cartService;
        this.customerService = customerService;
    }

    @GetMapping("/view")
    public String viewCart(Model model, Principal principal) {
        String username = principal.getName();
        Customer customer = customerService.getCustomerByUsername(username);
        List<CartItem> items = cartService.viewCart(customer.getId());
        model.addAttribute("items", items);
        return "viewCart";
    }

    @PostMapping("/checkout")
    public String checkoutCart(Principal principal) {
        String username = principal.getName();
        Customer customer = customerService.getCustomerByUsername(username);
        cartService.checkout(customer.getId());
        return "checkout"; 
    }
}
