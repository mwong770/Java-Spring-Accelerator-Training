package com.company.u1m4summativewongmaria.controller;

import com.company.u1m4summativewongmaria.model.Quote;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// handles requests and returns values from methods as JSON and
// sends those values back to the client
@RestController
public class QuoteOfDayService {

    // list to store Quote objects
    private static List<Quote> quotes = new ArrayList<>();

    // constructor
    public QuoteOfDayService() {
    }

    /**
     *  stores Quote objects into a list after initialization
     */
    @PostConstruct
    public void loadData() {

        quotes.add(new Quote("I intend to live forever. So far so good.", "Steven Wright"));
        quotes.add(new Quote("When I was a kid my parents moved a lot, but I always found them.", "Rodney Dangerfield"));
        quotes.add(new Quote("I am writing a book. I've got the page numbers done.", "Steven Wright"));
        quotes.add(new Quote("I like long walks, especially when they are taken by people who annoy me.", "Fred Allen"));
        quotes.add(new Quote("It is impossible for a man to learn what he thinks he already knows.", "Epictetus"));
        quotes.add(new Quote("Get your facts first, then you can distort them as you please.", "Mark Twain"));
        quotes.add(new Quote("A boss on vacation is the most cost effective measure. Everybody in the office has a vacation at the cost of one.", "Thibaut"));
        quotes.add(new Quote("The best way to appreciate your job is to imagine yourself without one.", "Oscar Wilde"));
        quotes.add(new Quote("If I had asked people what they wanted, they would have said faster horses.", "Henry Ford"));
        quotes.add(new Quote("If you cannot get rid fo the family skeleton, you may as well make it dance.", "George Bernard Shaw"));

    }

    /**
     * selects a random Quote object
     * @return Quote object containing a quote and the author of the quote
     */
    @RequestMapping(value = "/quote", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Quote getQuote() {

        Random randomGenerator = new Random();

        return quotes.get(randomGenerator.nextInt(10));

    }

}
