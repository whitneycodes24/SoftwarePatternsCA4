package com.example.softwarePatternsCA4.composite;


import java.util.List;
import com.example.softwarePatternsCA4.entity.Book;


public interface BookComponent {
	
	void add(BookComponent  component);
	void remove(BookComponent component);
	List<Book> getBooks();

}
