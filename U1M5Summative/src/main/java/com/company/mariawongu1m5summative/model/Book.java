package com.company.mariawongu1m5summative.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

// Model object
// Stores data retrieved using BookDao class
public class Book {

    private int bookId;

    private String isbn;

    private LocalDate publishDate;

    private int authorId;

    private String title;

    private int publisherId;

    private BigDecimal price;

    // getters and setters

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    // compares objects for equality
    // overrides default equals() which compares memory location
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return getBookId() == book.getBookId() &&
                getAuthorId() == book.getAuthorId() &&
                getPublisherId() == book.getPublisherId() &&
                getPrice().equals(book.getPrice()) &&
                getIsbn().equals(book.getIsbn()) &&
                getPublishDate().equals(book.getPublishDate()) &&
                getTitle().equals(book.getTitle());
    }

    // generates an integer code corresponding to an object
    // overrides default of assigning unique hashcode value to each object when they are created in memory
    // need to override hashCode b/c if two objects are equal by equals() method then
    // their hashcode returned by hashCode() method must be same.
    @Override
    public int hashCode() {
        return Objects.hash(getBookId(), getIsbn(), getPublishDate(), getAuthorId(), getTitle(), getPublisherId(), getPrice());
    }

}
