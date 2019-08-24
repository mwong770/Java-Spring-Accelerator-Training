package com.company.u1m4summativewongmaria.controller;

import com.company.u1m4summativewongmaria.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// handles requests and returns values from methods as JSON and
// sends those values back to the client
@RestController
public class Magic8BallService {

    // list to store possible answers
    private static List<String> answers = new ArrayList<>();

    // constructor
    public Magic8BallService() {
    }

    /**
     *  stores answer options into a list after initialization
     */
    @PostConstruct
    public void loadData() {

        answers.add("Very doubtful.");
        answers.add("Most likely.");
        answers.add("Concentrate and ask again.");
        answers.add("You should be so lucky!");
        answers.add("Better you shouldn't ask.");
        answers.add("Think bigger.");

    }

    /**
     *
     * randomly selects an answer to a user provided question
     * @param answer
     * @return Answer object containing user provided question and answer
     */
    @RequestMapping(value = "/magic", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public Answer getAnswer(@RequestBody Answer answer) {

        Random randomGenerator = new Random();

        String response = answers.get(randomGenerator.nextInt(6));

        answer.setAnswer(response);

        return answer;

    }

}
