package com.practice.bookservice.controller;

import com.practice.bookservice.entity.Book;
import com.practice.bookservice.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class BookControllerTest {

    @Mock
    BookService bookService;

    @InjectMocks
    BookController bookController;

    private static final int id1 = 1;
    private static final int id2 = 2;
    private static final String author1 = "Jane Austin";
    private static final String author2 = "JK Rowling";
    private static final String name1 = "Pride and prejudice";
    private static final String name2 = "Harry Potter";


    Book book1;
    Book book2;

    @BeforeEach
    void setUp() {
        book1 = Book.builder().author(author1).name(name1).id(id1).createdAt(LocalDateTime.now()).build();
        book2 = Book.builder().author(author2).name(name2).id(id2).createdAt(LocalDateTime.now()).build();
    }

    @Test
    void getBooks() {
        List<Book> books = Arrays.asList(book1, book2);
        Mockito.when(bookService.getBooks()).thenReturn(books);
        List<Book> bookResponse = bookController.getBooks();
        Assertions.assertNotNull(bookResponse.get(0));
        Assertions.assertEquals(id1, bookResponse.get(0).getId());
        Assertions.assertEquals(id2, bookResponse.get(1).getId());
        Assertions.assertEquals(author1, bookResponse.get(0).getAuthor());
        Assertions.assertEquals(name2, bookResponse.get(1).getName());
    }

    @Test
    void getBooksById() {
        Mockito.when(bookService.getBook(id1)).thenReturn(Optional.of(book1));
        Book bookResponse = bookController.getBooksById(id1).getBody();
        Assertions.assertEquals(id1, bookResponse.getId());
        Assertions.assertEquals(author1, bookResponse.getAuthor());
        Assertions.assertEquals(name1, bookResponse.getName());
    }

    @Test
    void createBook() {
        Mockito.when(bookService.createBook(book1)).thenReturn(book1);
        Book bookResponse = bookController.createBook(book1).getBody();
        Assertions.assertEquals(book1, bookResponse);
    }

    @Test
    void updateBook() {
        Mockito.when(bookService.updateBook(id1, book1)).thenReturn(Optional.of(book1));
        Book bookResponse = bookController.updateBook(id1, book1).getBody();
        Assertions.assertEquals(book1, bookResponse);
    }

    @Test
    void deleteBook() {
        Mockito.doNothing().when(bookService).deleteBook(id1);
        bookController.deleteBook(id1);
        Mockito.verify(bookService, Mockito.times(1)).deleteBook(id1);
    }
}