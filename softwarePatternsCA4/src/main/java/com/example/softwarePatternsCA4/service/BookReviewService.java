package com.example.softwarePatternsCA4.service;


import java.util.List;
import org.springframework.stereotype.Service;
import com.example.softwarePatternsCA4.entity.Book;
import com.example.softwarePatternsCA4.entity.BookReview;
import com.example.softwarePatternsCA4.entity.Customer;
import com.example.softwarePatternsCA4.repository.BookRepository;
import com.example.softwarePatternsCA4.repository.BookReviewRepository;
import com.example.softwarePatternsCA4.repository.CustomerRepository;

@Service
public class BookReviewService {

    private final BookReviewRepository bookReviewRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    public BookReviewService(BookReviewRepository bookReviewRepository, BookRepository bookRepository, CustomerRepository customerRepository) {
        this.bookReviewRepository = bookReviewRepository;
        this.bookRepository = bookRepository;
        this.customerRepository = customerRepository;
    }

    public BookReview addReview(Long bookId, Long customerId, int rating, String comment) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Error - Can Not Find Book"));

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Error - Can Not Find Customer"));

        BookReview review = new BookReview();
        review.setBook(book);
        review.setCustomer(customer);
        review.setRating(rating);
        review.setComment(comment);

        return bookReviewRepository.save(review);
    }

    public List<BookReview> getReviewsForBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Error - Can Not Find Book"));
        return bookReviewRepository.findByBook(book);
    }
}
