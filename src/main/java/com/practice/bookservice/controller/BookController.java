package com.practice.bookservice.controller;

import com.practice.bookservice.config.SwaggerConfig;
import com.practice.bookservice.entity.Book;
import com.practice.bookservice.exception.BookNotFoundException;
import com.practice.bookservice.service.IBookService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Api(tags = { SwaggerConfig.BOOK_TAG})
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
    @ApiOperation(value = "View list of books", response = List.class)
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
    @ApiOperation(value = "Retrieves a book by the given ID.", response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Book not found. ")
    })
    @GetMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBooksById(
            @ApiParam(name="id", value = "The ID of the book.", required = true)
            @PathVariable(value = "id") int bookId) throws BookNotFoundException {
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
    @ApiOperation(value = "Creates a new book.", response = Book.class)
    @PostMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> createBook(
            @ApiParam(name="book", value = "The book.", required = true)
            @Valid @RequestBody Book book) {
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
    @ApiOperation(value = "Updates an existing book.",response = Book.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Book not found. ")
    })
    @PutMapping(value ="/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> updateBook(
            @ApiParam(name="id", value = "The ID of the book.", required = true)
            @PathVariable(value = "id") Integer bookId,
            @ApiParam(name="book", value = "The book.", required = true)
            @Valid @RequestBody Book book) throws BookNotFoundException {
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
    @ApiOperation("Deletes the book.")
    @DeleteMapping("/books/{id}")
    public ResponseEntity<HttpStatus> deleteBook(
            @ApiParam(name="id", value = "The ID of the book.", required = true)
            @PathVariable(value = "id") Integer bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
