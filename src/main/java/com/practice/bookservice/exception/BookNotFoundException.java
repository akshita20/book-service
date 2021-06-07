package com.practice.bookservice.exception;

public class BookNotFoundException extends Exception{

    public BookNotFoundException(int id) {
        super("Book not found with id : "+id);
    }
}
