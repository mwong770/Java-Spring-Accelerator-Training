package com.company.mariawongu1m5summative.dao;

import com.company.mariawongu1m5summative.model.Author;

import java.util.List;

// Data Access Object Interface
// Defines the standard operations to be performed on the Author model object
public interface AuthorDao {

    // method declarations

    Author getAuthor(int id);

    List<Author> getAllAuthors();

    Author addAuthor(Author author);

    void updateAuthor(Author author);

    void deleteAuthor(int id);

}
