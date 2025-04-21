package com.example.softwarePatternsCA4.sortStrategy;

import java.util.Comparator;
import java.util.List;

import com.example.softwarePatternsCA4.entity.Book;

public class SortByPrice implements SortBookStrategy {
	@Override
	public List<Book> sort(List<Book> books) {
		books.sort(Comparator.comparingDouble(Book::getPrice));
		return books;
	}

}
