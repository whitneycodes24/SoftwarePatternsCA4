package com.example.softwarePatternsCA4.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.softwarePatternsCA4.entity.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	List<Book> findByTitleContainingIgnoreCase(String title);
	List<Book> findByAuthorContainingIgnoreCase(String author);
	List<Book> findByPublisherContainingIgnoreCase(String publisher);
	List<Book> findByCategoryContainingIgnoreCase(String category);
}
