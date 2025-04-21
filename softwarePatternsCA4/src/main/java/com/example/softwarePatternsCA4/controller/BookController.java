package com.example.softwarePatternsCA4.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.softwarePatternsCA4.entity.Book;
import com.example.softwarePatternsCA4.service.BookService;
import com.example.softwarePatternsCA4.sortStrategy.BookSorter;
import com.example.softwarePatternsCA4.sortStrategy.SortByAuthor;
import com.example.softwarePatternsCA4.sortStrategy.SortByPrice;
import com.example.softwarePatternsCA4.sortStrategy.SortByTitle;


@RestController
@RequestMapping("/api/books")
public class BookController {
	
	private final BookService bookService;
	
	
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	
	@GetMapping
	public List<Book> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	
	@GetMapping("/{id}")
	public Optional<Book> getBookById(@PathVariable Long id) {
		return bookService.getBookById(id);
	}
	
	
	@PostMapping
	public Book createBook(@RequestBody Book book) {
		return bookService.saveBook(book);
	}
	
	
	@PutMapping("/{id}")
	public Book updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
		Optional<Book> optionalBook = bookService.getBookById(id);
		
		if (optionalBook.isPresent()) {
			Book book = optionalBook.get();
			book.setPublisher(bookDetails.getPublisher());
			book.setAuthor(bookDetails.getAuthor());
			book.setCategory(bookDetails.getCategory());
			book.setIsbn(bookDetails.getIsbn());
			book.setTitle(bookDetails.getTitle());
			book.setPrice(bookDetails.getPrice());
			book.setImageUrl(bookDetails.getImageUrl());
			
			return bookService.saveBook(book);
		} else {
			return null;
		}
	}
	
	@DeleteMapping ("/{id}")
	public void deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
	}
	
	
	@GetMapping("/search/title")
	public List<Book> searchByTitle(@RequestParam String title) {
	    return bookService.searchByTitle(title);
	}

	@GetMapping("/search/author")
	public List<Book> searchByAuthor(@RequestParam String author) {
	    return bookService.searchByAuthor(author);
	}

	@GetMapping("/search/publisher")
	public List<Book> searchByPublisher(@RequestParam String publisher) {
	    return bookService.searchByPublisher(publisher);
	}

	@GetMapping("/search/category")
	public List<Book> searchByCategory(@RequestParam String category) {
	    return bookService.searchByCategory(category);
	}
	
	@GetMapping("/sort")
	public List<Book> sortBooks(@RequestParam String by) {
		BookSorter sorter = new BookSorter();
		
		switch (by.toLowerCase()) {
		case "title" -> sorter.setStrategy(new SortByTitle());
		case "price" -> sorter.setStrategy(new SortByPrice());
		case "author" -> sorter.setStrategy(new SortByAuthor());
		default -> throw new IllegalArgumentException("Sort Type is Invalid");
		}
		
		List<Book> books = bookService.getAllBooks();
		return sorter.sortBooks(books);
	}

}
