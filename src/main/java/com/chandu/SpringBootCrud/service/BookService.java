package com.chandu.SpringBootCrud.service;

import com.chandu.SpringBootCrud.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public Optional<Book> getBook(Long id);
    public List<Book> getBooks();
    public Book saveBook(Book book);
    public Book updateBook(Book book);
    public void deleteBook(Book book);
    public void deleteBook(Long id);
    public List<Book> getBooks(String name);
}
