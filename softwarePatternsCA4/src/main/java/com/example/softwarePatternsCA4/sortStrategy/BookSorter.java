package com.example.softwarePatternsCA4.sortStrategy;


import java.util.List;
import com.example.softwarePatternsCA4.entity.Book;


public class BookSorter {
	private SortBookStrategy strategy;
	
	public void setStrategy(SortBookStrategy strategy) {
		this.strategy = strategy;
	}
	
	public List<Book> sortBooks(List<Book> books) {
		return strategy.sort(books);
	}
}
