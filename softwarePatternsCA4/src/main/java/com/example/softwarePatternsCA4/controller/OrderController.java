package com.example.softwarePatternsCA4.controller;


import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import com.example.softwarePatternsCA4.entity.Customer;
import com.example.softwarePatternsCA4.service.CustomerService;
import com.example.softwarePatternsCA4.service.OrderService;


@Controller
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final CustomerService customerService;

    public OrderController(OrderService orderService, CustomerService customerService) {
        this.orderService = orderService;
        this.customerService = customerService;
    }

    @GetMapping
    public String viewCustomerOrders(Model model, Principal principal) {
        Customer customer = customerService.getCustomerByUsername(principal.getName());
        model.addAttribute("orders", orderService.getOrdersByCustomer(customer.getId()));
        return "customerOrders";
    }

    @GetMapping("/admin")
    public String viewAllOrders(Model model) {
        model.addAttribute("orders", orderService.getAllOrders());
        return "adminOrders";
    }
}
