package com.example.softwarePatternsCA4.repository;


import com.example.softwarePatternsCA4.entity.Book;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.softwarePatternsCA4.entity.BookReview;


public interface BookReviewRepository extends JpaRepository<BookReview, Long> {
	
	List<BookReview> findByBook(Book book);

}
