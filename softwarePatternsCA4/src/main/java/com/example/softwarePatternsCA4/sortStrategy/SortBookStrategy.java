package com.example.softwarePatternsCA4.sortStrategy;


import java.util.List;
import com.example.softwarePatternsCA4.entity.Book;


public interface SortBookStrategy {
	List<Book> sort(List<Book> books);
}
