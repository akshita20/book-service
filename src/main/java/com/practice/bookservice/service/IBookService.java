package com.practice.bookservice.service;

import com.practice.bookservice.entity.Book;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    List<Book> getBooks();

    Optional<Book> getBook(int bookId);

    Book createBook(Book book);

    Optional<Book> updateBook(int bookId, Book book);

    void deleteBook(int bookId);
}
