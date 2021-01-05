package com.chandu.SpringBootCrud.controller;

import com.chandu.SpringBootCrud.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@WebMvcTest(controllers = BookController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTestWithJUnit5 {

    // bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate testRestTemplate;


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBook() throws Exception{
        //Using TestRestTemplate - getEntity method
       ResponseEntity<Book>  book = testRestTemplate.getForEntity(
                new URL("http://localhost:"+port+"/books/"+1).toString(), Book.class);

        assertEquals("Java", Objects.requireNonNull(book.getBody()).getName());

        //Using TestRestTemplate - getForObject method
        Book  book1 = testRestTemplate.getForObject(
                new URL("http://localhost:"+port+"/books/"+1).toString(), Book.class);

        assertEquals("Java",book1.getName());
    }

    @Test
    void getBooks() {
    }

    @Test
    void saveBook() {
    }

    @Test
    void updateBook() {
    }

    @Test
    void deleteBook() {
    }
}