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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

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
       Book book1 = new Book("Java");
       book1.setId(1L);

       Book book2 = new Book("Oracle");
       book2.setId(2L);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

       Mockito.when(bookService.getBooks()).thenReturn(bookList);
       List<Book> bookList1 = bookService.getBooks();

        verify(bookRepository, times(1)).findAll();

       Assertions.assertEquals(2,bookList1.size());
       Book expectedBook1 = bookList1.get(0);
       Assertions.assertEquals(expectedBook1.getId(), 1L);
       Assertions.assertEquals(expectedBook1.getName(), "Java");

        Book expectedBook2 = bookList1.get(1);
        Assertions.assertEquals(expectedBook2.getId(), 2L);
        Assertions.assertEquals(expectedBook2.getName(), "Oracle");
    }

    @Test
    void saveBook() {
        Book book1 = new Book("Java");
        book1.setId(1L);

        Mockito.when(bookService.saveBook(book1)).thenReturn(book1);
        Book book2 = bookService.saveBook(book1);

        verify(bookRepository, times(1)).save(book1);

        Assertions.assertEquals(book2.getId(), 1L);
        Assertions.assertEquals(book2.getName(), "Java");
    }

    @Test
    void updateBook() {

        Book book1 = new Book("Java");
        book1.setId(1L);

        Mockito.when(bookService.updateBook(book1)).thenReturn(book1);
        Book book2 = bookService.updateBook(book1);

        verify(bookRepository, times(1)).save(book1);

        Assertions.assertEquals(book2.getId(), 1L);
        Assertions.assertEquals(book2.getName(), "Java");
    }

    @Test
    void deleteBook() {
        doNothing().when(bookRepository).deleteById(1L);
         bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteBook() {
        Book book1 = new Book("Java");
        book1.setId(1L);
        doNothing().when(bookRepository).delete(book1);
        bookService.deleteBook(book1);

        verify(bookRepository, times(1)).delete(book1);
    }

    @Test
    void testGetBooks() {
        Book book1 = new Book("Java");
        book1.setId(1L);

        Book book2 = new Book("Java");
        book2.setId(2L);
        List<Book> bookList = new ArrayList<>();
        bookList.add(book1);
        bookList.add(book2);

        Mockito.when(bookService.getBooks("Java")).thenReturn(bookList);
        List<Book> bookList1 = bookService.getBooks("Java");

        verify(bookRepository, times(1)).findByName("Java");

        Assertions.assertEquals(2,bookList1.size());
        Book expectedBook1 = bookList1.get(0);
        Assertions.assertEquals(expectedBook1.getId(), 1L);
        Assertions.assertEquals(expectedBook1.getName(), "Java");

        Book expectedBook2 = bookList1.get(1);
        Assertions.assertEquals(expectedBook2.getId(), 2L);
        Assertions.assertEquals(expectedBook2.getName(), "Java");
    }
}