package com.example.softwarePatternsCA4.service;

import com.example.softwarePatternsCA4.entity.*;
import com.example.softwarePatternsCA4.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,
                       BookRepository bookRepository, CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    public Cart getCartByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Error - Could Not Find Customer"));
        return cartRepository.findByCustomer(customer)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setCustomer(customer);
                    return cartRepository.save(cart);
                });
    }

    public Cart addToCart(Long customerId, Long bookId, int quantity) {
        Cart cart = getCartByCustomerId(customerId);
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Error - Could Not Find Book"));

        CartItem item = new CartItem();
        item.setBook(book);
        item.setQuantity(quantity);
        item.setCart(cart);
        cart.getItems().add(item);

        cartItemRepository.save(item);
        return cartRepository.save(cart);
    }

    public List<CartItem> viewCart(Long customerId) {
        Cart cart = getCartByCustomerId(customerId);
        return cart.getItems();
    }

    public void clearCart(Long customerId) {
        Cart cart = getCartByCustomerId(customerId);
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    public void removeItem(Long itemId) {
        cartItemRepository.deleteById(itemId);
    }

    public List<CartItem> getCartItems(Long customerId) {
        return getCartByCustomerId(customerId).getItems();
    }

    public void checkout(Long customerId) {
        Cart cart = getCartByCustomerId(customerId);
        if (cart.getItems().isEmpty()) {
            throw new RuntimeException("Error - Can Not Check Out Cart That is Empty");
        }
        cart.setCheckOut(true);
        cart.getItems().clear();
        cartRepository.save(cart);
    }

    public Customer getCustomerByUsername(String username) {
        return customerRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Error - Could Not Find Customer By Username"));
    }
}
