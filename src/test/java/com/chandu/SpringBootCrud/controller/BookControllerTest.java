package com.chandu.SpringBootCrud.controller;

import com.chandu.SpringBootCrud.dao.BookRepository;
import com.chandu.SpringBootCrud.model.Book;
import com.chandu.SpringBootCrud.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

//RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(value = BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    BookService bookService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @DisplayName("Test for book controller get request")
    @Test
   public void getBook() throws Exception{
         Book book = new Book("Java");
         book.setId(1L);
         //1st way
        Mockito.when(bookService.getBook(1L)).thenReturn(Optional.of(book));
       mockMvc.perform(MockMvcRequestBuilders.get("/books/{id}",1))
              .andExpect(MockMvcResultMatchers.status().is(200))
              .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
              .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
              .andExpect(MockMvcResultMatchers.jsonPath("$.name",Matchers.is("Java")));

       // 2nd way
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/books/1"))
                .andExpect(MockMvcResultMatchers.status().is(200)).andReturn();

      String expected ="{id: 1,name: Java}";
      Book book1 = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Book.class);
      JSONAssert.assertEquals(expected, mvcResult.getResponse().getContentAsString(), false);
      Assertions.assertEquals(book.getName(),book1.getName());
    }

    @DisplayName("Test For Fetch all Books")
    @Test
    void getBooks() throws Exception {
        Book book1 = new Book("Java");
        book1.setId(1L);

        Book book2 = new Book("Test");
        book2.setId(2L);

        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        Mockito.when(bookService.getBooks()).thenReturn(bookList);

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id",Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",Matchers.is("Java")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id",Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name",Matchers.is("Test")));

    }

    @DisplayName("Test For save Book details")
    @Test
    void saveBook() throws Exception {
        Book book = new Book("Python");
        book.setId(2L);
        Mockito.when(bookService.saveBook(ArgumentMatchers.any())).thenReturn(book);
        String json = objectMapper.writeValueAsString(book);
        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo("Python")));
    }

    @DisplayName("Test for update the book details")
    @Test
    void updateBook() throws Exception {
        Book book = new Book("Python");
        book.setId(2L);
        Mockito.when(bookService.updateBook(ArgumentMatchers.any())).thenReturn(book);
        String json = objectMapper.writeValueAsString(book);
        mockMvc.perform(MockMvcRequestBuilders.put("/books")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.equalTo(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.equalTo("Python")));
    }

    @DisplayName("Delete Book by id")
    @Test
    void deleteBook() throws Exception {
        doNothing().when(bookService).deleteBook(1L);
        mockMvc.perform( MockMvcRequestBuilders.delete("/books/{id}", 1) )
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(bookService, times(1)).deleteBook(1L);
    }

    @DisplayName("Delete Test For passing book instance")
    @Test
    void testDeleteBook() throws Exception {
        Book book = new Book("Python");
        book.setId(2L);
        doNothing().when(bookService).deleteBook(book);
        String json = objectMapper.writeValueAsString(book);
        mockMvc.perform(MockMvcRequestBuilders.delete("/books")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk());
        verify(bookService, times(1)).deleteBook(book);
    }

    @DisplayName("Delete book by name")
    @Test
    void getBookByName() throws Exception {
        Book book = new Book("Java");
        book.setId(1L);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        Mockito.when(bookService.getBooks("Java")).thenReturn(bookList);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/byName").param("name","Java"))
                .andExpect(MockMvcResultMatchers.status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id",Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name",Matchers.is("Java")));
    }
}