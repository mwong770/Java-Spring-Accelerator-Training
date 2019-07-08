package com.company.u1m4summativewongmaria.controller;

import com.company.u1m4summativewongmaria.model.Definition;
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
public class WordOfDayService {

    // list to store Definition objects
    private static List<Definition> definitions = new ArrayList<>();

    // constructor
    public WordOfDayService() {
    }

    /**
     *  stores Definition objects into a list after initialization
     */
    @PostConstruct
    public void loadData() {

        definitions.add(new Definition("secret", "something you tell everybody to tell nobody"));
        definitions.add(new Definition("internet", "the reason you are failing your classes"));
        definitions.add(new Definition("mother", "a person who does the work of twenty for free"));
        definitions.add(new Definition("nurse", "a person who wakes you up to give you sleeping pills"));
        definitions.add(new Definition("study", "the act of texting, eating and watching tv with an open textbook nearby"));
        definitions.add(new Definition("doctor", "a person who kills your ills with pills then kills you with bills"));
        definitions.add(new Definition("irony", "drawing trees on paper"));
        definitions.add(new Definition("laziness", "risking to drop everything you carry rather than walking twice"));
        definitions.add(new Definition("calories", "tiny creatures that live in your closet and sew your clothes a little bit tighter every nights"));
        definitions.add(new Definition("karma", "when you use the last of the toilet paper without replacing it and are the next person to use the bathroom"));

    }

    /**
     * selects a random Definition object
     * @return Definition object containing a word and its definition
     */
    @RequestMapping(value = "/word", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Definition getDefinition() {

        Random randomGenerator = new Random();

        return definitions.get(randomGenerator.nextInt(10));

    }

}
