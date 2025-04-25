package com.example.softwarePatternsCA4.controller;


import com.example.softwarePatternsCA4.entity.Customer;
import com.example.softwarePatternsCA4.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HomePageController {

    private final CustomerService customerService;

    public HomePageController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerCustomer(@RequestParam String username,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String shippingAddress,
                                   @RequestParam String paymentMethod,
                                   RedirectAttributes redirectAttributes) {
        Customer customer = new Customer();
        customer.setUsername(username);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setShippingAddress(shippingAddress);
        customer.setPaymentMethod(paymentMethod);
        customer.setAdmin(false);
        customerService.registerCustomer(customer);
        redirectAttributes.addFlashAttribute("successMessage", "Registration successful! You can now log in.");
        return "redirect:/login";
    }
 
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
    
    @GetMapping("/customer/dashboard")
    public String customerDashboard() {
        return "customerDashboard";
    }
}
