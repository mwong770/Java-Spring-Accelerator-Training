package com.company.mariawongu1m5summative.dao;

import com.company.mariawongu1m5summative.model.Book;

import java.util.List;

// Data Access Object Interface
// Defines the standard operations to be performed on the Book model object
public interface BookDao {

    // method declarations

    Book getBook(int id);

    List<Book> getAllBooks();

    Book addBook(Book book);

    void updateBook(Book book);

    void deleteBook(int id);

    List<Book> getBooksByAuthor(int authorId);

}
