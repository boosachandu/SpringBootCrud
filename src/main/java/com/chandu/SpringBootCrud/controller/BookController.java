package com.chandu.SpringBootCrud.controller;

import com.chandu.SpringBootCrud.exception.BookNotFoundException;
import com.chandu.SpringBootCrud.model.Book;
import com.chandu.SpringBootCrud.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public Book getBook(@PathVariable String id){

        return bookService.getBook(Long.valueOf(id)).orElseThrow(
                () -> new BookNotFoundException("Book Not Found with Id : "+id));
    }

    @GetMapping
    public List<Book> getBooks(){
         return bookService.getBooks();
    }

    @PostMapping
    public Book saveBook(@RequestBody Book book){
       return bookService.saveBook(book);
    }

    @PutMapping
    public Book updateBook(@RequestBody Book book){
       return bookService.updateBook(book);
    }

    @DeleteMapping
    public void deleteBook(@RequestBody Book book){
        bookService.deleteBook(book);
    }

    @DeleteMapping("/{id}")
    public boolean deleteBook(@PathVariable String id){
        bookService.deleteBook(Long.valueOf(id));
        return true;
    }

   /* @GetMapping("/byName/{name}")
    public List<Book> getBookByName(@PathVariable String name){
        return bookService.getBooks(name);
    }*/

    @GetMapping("/byName")
    public List<Book> getBookByName(@RequestParam String name){
        return bookService.getBooks(name);
    }
}
