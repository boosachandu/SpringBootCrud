package com.chandu.SpringBootCrud.controller;

import com.chandu.SpringBootCrud.dao.BookRepository;
import com.chandu.SpringBootCrud.model.Book;
import com.chandu.SpringBootCrud.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
public class BookControllerTestWithJunit4 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
   public void saveBook() throws Exception {
        Book book = new Book("C");
        book.setId(5L);

        Mockito.when(bookService.saveBook(book)).thenReturn(book);

     String result =    mockMvc.perform(MockMvcRequestBuilders
                .post("/books")
                .content(asJsonString(book))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn().getResponse().getContentAsString();
                //.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(5)));
        System.out.println(result);

           mockMvc.perform(MockMvcRequestBuilders
                .post("/books")
                .content(asJsonString(book))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(5)));

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
