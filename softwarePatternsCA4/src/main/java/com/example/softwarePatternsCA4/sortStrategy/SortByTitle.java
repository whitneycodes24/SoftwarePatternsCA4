package com.example.softwarePatternsCA4.sortStrategy;


import java.util.Comparator;
import java.util.List;
import com.example.softwarePatternsCA4.entity.Book;


public class SortByTitle implements SortBookStrategy {
	@Override
	public List<Book> sort(List<Book> books) {
		books.sort(Comparator.comparing(Book::getTitle));
		return books;
	}
}
