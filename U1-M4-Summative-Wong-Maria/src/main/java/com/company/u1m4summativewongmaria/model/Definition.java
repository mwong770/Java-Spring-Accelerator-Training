package com.company.u1m4summativewongmaria.model;

/**
 * DTO to map word and definition to
 */
public class Definition {

    private String word;
    private String definition;

    // constructors

    public Definition() {
    }

    // used to facilitate initialization of objects to store in memory
    // using just setters to create 10 Definition objects would have been a lot of code
    public Definition(String word, String definition) {
        this.word = word;
        this.definition = definition;
    }

    // getters and setters

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

}
