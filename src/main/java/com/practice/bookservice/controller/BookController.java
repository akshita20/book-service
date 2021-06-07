package com.practice.bookservice.controller;

import com.practice.bookservice.entity.Book;
import com.practice.bookservice.exception.BookNotFoundException;
import com.practice.bookservice.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BookController {

    private final IBookService bookService;

    @Autowired
    public BookController(IBookService BookService) {
        this.bookService = BookService;
    }

    /**
     * Get all Books list.
     *
     * @return the list
     */
    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    /**
     * Get Book by id.
     *
     * @param bookId the Book id
     * @return the Book by id
     */
    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBooksById(@PathVariable(value = "id") int bookId) throws BookNotFoundException {
        Book book = bookService.getBook(bookId)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    /**
     * Create Book Book.
     *
     * @param book the Book
     * @return the book response entity
     */
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        Book createdBook = bookService.createBook(book);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    /**
     * Update Book book.
     *
     * @param bookId the Book Id
     * @param book   the Book details
     * @return the book response entity
     */
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable(value = "id") Integer bookId, @Valid @RequestBody Book book) throws BookNotFoundException {
        Book updatedBook = bookService.updateBook(bookId, book)
                .orElseThrow(() -> new BookNotFoundException(bookId));
        return new ResponseEntity<>(updatedBook, HttpStatus.OK);
    }

    /**
     * Delete book.
     *
     * @param bookId the Book Id
     * @return the response entity with HTTP status
     */
    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable(value = "id") Integer bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
