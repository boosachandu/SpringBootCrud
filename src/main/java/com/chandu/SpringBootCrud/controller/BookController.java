package com.chandu.SpringBootCrud.controller;

import com.chandu.SpringBootCrud.dao.BookRepository;
import com.chandu.SpringBootCrud.exception.BookNotFoundException;
import com.chandu.SpringBootCrud.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/{id}")
    public Book getBook(@PathVariable String id){

        return bookRepository.findById(Long.valueOf(id)).orElseThrow(
                () -> new BookNotFoundException("Book Not Found with Id : "+id));
    }

    @GetMapping
    public List<Book> getBooks(){
         return bookRepository.findAll();
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book){
       return bookRepository.save(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book){
       return bookRepository.save(book);
    }

    @DeleteMapping
    public void deleteBook(@RequestBody Book book){
        bookRepository.delete(book);
    }

}
