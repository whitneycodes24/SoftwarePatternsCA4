package com.example.softwarePatternsCA4.composite;

import java.util.Collections;
import java.util.List;

import com.example.softwarePatternsCA4.entity.Book;

public class BookLeaf implements BookComponent {
	
	private final Book book;

    public BookLeaf(Book book) {
        this.book = book;
    }
    
    
    @Override
    public void add(BookComponent component) {
    	throw new UnsupportedOperationException("Cannot add to a book leaf.");
    }

    
    @Override
    public void remove(BookComponent component) {
    	throw new UnsupportedOperationException("Cannot remove from a book leaf.");
    }
    
    
    @Override
    public List<Book> getBooks() {
        return Collections.singletonList(book);
    }

}
