package com.chandu.SpringBootCrud.service.impl;

import com.chandu.SpringBootCrud.dao.BookRepository;
import com.chandu.SpringBootCrud.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

//@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getBook() {
        Book book = new Book("C++");
        book.setId(1L);

        Mockito.when(bookService.getBook(1L)).thenReturn(Optional.of(book));

        Optional<Book> book1 = bookService.getBook(1L);
        Book book2 = null;
        if (book1.isPresent())
         book2 = book1.get();

        assert book2 != null;
        Assertions.assertEquals(1L, book2.getId());
        Assertions.assertEquals("C++", book2.getName());

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

    @Test
    void testDeleteBook() {
    }

    @Test
    void testGetBooks() {
    }
}