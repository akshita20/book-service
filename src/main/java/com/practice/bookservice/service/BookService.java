package com.practice.bookservice.service;

import com.practice.bookservice.entity.Book;
import com.practice.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService{

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository BookRepository) {
        this.bookRepository = BookRepository;
    }


    @Override
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> getBook(int bookId) {
        return bookRepository.findById(bookId);
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> updateBook(int bookId, Book book) {
        Optional<Book> existingBook = bookRepository.findById(bookId);
        if (!existingBook.isPresent())
            return existingBook;
        Book updateBook = existingBook.get();
        updateBook.setName(book.getName());
        updateBook.setAuthor(book.getAuthor());
        updateBook.setUpdatedAt(LocalDateTime.now());
        bookRepository.save(updateBook);
        return Optional.of(updateBook);
    }

    @Override
    public void deleteBook(int bookId) {
        bookRepository.deleteById(bookId);
    }
}
