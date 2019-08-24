package com.company.u1m4summativewongmaria.model;

/**
 * DTO to map author and quote
 */
public class Quote {

    private String author;
    private String quote;

    // constructors

    public Quote() {
    }

    // used to facilitate initialization of objects' instance variables
    // using just setters to create 10 Quote objects would have been a lot of code
    public Quote(String author, String quote) {
        this.author = author;
        this.quote = quote;
    }

    // getters and setters

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        author = author;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        quote = quote;
    }

}
