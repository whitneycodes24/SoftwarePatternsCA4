package com.example.softwarePatternsCA4.service;


import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.softwarePatternsCA4.entity.Customer;
import com.example.softwarePatternsCA4.repository.CustomerRepository;
import com.example.softwarePatternsCA4.factory.UserFactory;
import com.example.softwarePatternsCA4.validations.*;


@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public Customer getCustomerByUsername(String username) {
        return customerRepository.findByUsername(username)
            .orElseThrow(() -> new RuntimeException("Error - Customer not found by username"));
    }


    public Customer registerCustomer(Customer customerData) {
        CustomerInput input = new CustomerInput(
            customerData.getUsername(),
            customerData.getPassword(), 
            customerData.getEmail(),
            customerData.getShippingAddress(),
            customerData.getPaymentMethod()
        );

        Validator validator = new EmailValidator();
        validator.linkWith(new PasswordValidator())
                 .linkWith(new AddressValidator());

        validator.validate(input);

        Customer customer = UserFactory.createCustomer(
            input.username,
            passwordEncoder.encode(input.password),
            input.email,
            input.shippingAddress,
            input.paymentMethod,
            customerData.isAdmin()
        );

        return customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }

    public String getCustomerEmail(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"))
                .getEmail();
    }

}
