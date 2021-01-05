package com.chandu.SpringBootCrud.controller;

import com.chandu.SpringBootCrud.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(controllers = BookController.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerTest {

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
        String response = testRestTemplate.getForObject(
                new URL("http://localhost:"+port+"/"+1).toString(), String.class);

        JSONAssert.assertEquals("{\"id\":1,\"name\":\"Java\"}",response,false);
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