package com.example.softwarePatternsCA4.composite;


import java.util.ArrayList;
import java.util.List;
import com.example.softwarePatternsCA4.entity.Book;


public class BookCategoryComposite implements BookComponent {
	
	private final List<BookComponent> components = new ArrayList<>();

    @Override
    public void add(BookComponent component) {
        components.add(component);
    }
    
    @Override
    public void remove(BookComponent component) {
        components.remove(component);
    }

    @Override
    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        for (BookComponent component : components) {
            books.addAll(component.getBooks());
        }
        return books;
    }

}
