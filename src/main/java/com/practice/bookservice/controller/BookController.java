package com.practice.bookservice.controller;

import com.practice.bookservice.entity.Book;
import com.practice.bookservice.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<Book> getBooksById(@PathVariable(value = "id") int bookId) {
        Optional<Book> book = bookService.getBook(bookId);
        ResponseEntity responseEntity;
        if (book.isPresent()){
            responseEntity = new ResponseEntity<>(book.get(), HttpStatus.OK);
        }
        else{
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
            return responseEntity;
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
        return ResponseEntity.ok(createdBook);
    }

    /**
     * Update Book book.
     *
     * @param bookId the Book Id
     * @param book the Book details
     * @return the book response entity
     */
    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(
            @PathVariable(value = "id") Integer bookId, @Valid @RequestBody Book book) {
        Optional<Book> updatedBook = bookService.updateBook(bookId, book);
        ResponseEntity responseEntity;
        if (updatedBook.isPresent()){
            responseEntity = new ResponseEntity<>(updatedBook.get(), HttpStatus.OK);
        }
        else{
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * Delete book.
     *
     * @param bookId the Book Id
     * @return the response entity with HTTP status
     */
    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable(value = "id") Integer bookId) {
        try {
            bookService.deleteBook(bookId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
