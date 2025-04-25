package com.example.softwarePatternsCA4.controller;


import com.example.softwarePatternsCA4.entity.Book;
import com.example.softwarePatternsCA4.entity.BookReview;
import com.example.softwarePatternsCA4.entity.Customer;
import com.example.softwarePatternsCA4.service.BookReviewService;
import com.example.softwarePatternsCA4.service.BookService;
import com.example.softwarePatternsCA4.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("/reviews")
public class BookReviewController {

    private final BookReviewService reviewService;
    private final CustomerService customerService;
    private final BookService bookService;

    public BookReviewController(BookReviewService reviewService, CustomerService customerService, BookService bookService) {
        this.reviewService = reviewService;
        this.customerService = customerService;
        this.bookService = bookService;
    }

    
    @GetMapping("/{bookId}")
    public String viewReviews(@PathVariable("bookId") Long bookId, Model model) {
        Book book = bookService.getBookById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        List<BookReview> reviews = reviewService.getReviewsForBook(bookId);
        model.addAttribute("book", book);
        model.addAttribute("reviews", reviews);
        return "bookReview";
    }

    
    @PostMapping("/add")
    public String addReview(@RequestParam Long bookId,
                            @RequestParam int rating,
                            @RequestParam String comment,
                            Principal principal) {
        String username = principal.getName();
        Customer customer = customerService.getCustomerByUsername(username);
        reviewService.addReview(bookId, customer.getId(), rating, comment);
        return "redirect:/books/details/" + bookId;
    }
}
