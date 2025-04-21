package com.example.softwarePatternsCA4.sortStrategy;


import java.util.Comparator;
import java.util.List;
import com.example.softwarePatternsCA4.entity.Book;


public class SortByAuthor implements SortBookStrategy {
	@Override
	public List<Book> sort(List<Book> books) {
		books.sort(Comparator.comparing(Book::getAuthor));
		return books;
	}
}
