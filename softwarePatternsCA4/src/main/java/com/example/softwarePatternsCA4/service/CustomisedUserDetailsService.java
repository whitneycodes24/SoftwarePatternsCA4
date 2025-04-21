package com.example.softwarePatternsCA4.service;


import com.example.softwarePatternsCA4.entity.Customer;
import com.example.softwarePatternsCA4.repository.CustomerRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomisedUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomisedUserDetailsService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Is Not found"));

        return new User(
                customer.getUsername(),
                customer.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(customer.getRole()))
        );
    }
}
