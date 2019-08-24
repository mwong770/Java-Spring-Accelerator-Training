package com.company.mariawongu1capstone.controller;

import com.company.mariawongu1capstone.exception.NotFoundException;
import com.company.mariawongu1capstone.service.TShirtService;
import com.company.mariawongu1capstone.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TShirtInventoryController {

    @Autowired
    TShirtService tShirtService;

    // handles requests to add a tshirt
    @RequestMapping(value = "/tshirts", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public TShirtViewModel createTShirt(@RequestBody @Valid TShirtViewModel tShirt) {
        return tShirtService.saveTShirt(tShirt);
    }

    // handles requests to retrieve a tshirt by id
    @RequestMapping(value = "/tshirts/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public TShirtViewModel getTShirt(@PathVariable("id") int itemId) {
        TShirtViewModel item = tShirtService.findTShirtById(itemId);
        if (item == null)
            throw new NotFoundException("Item could not be retrieved for id " + itemId);
        return item;
    }

    // handles requests to delete a tshirt by id
    @RequestMapping(value = "/tshirts/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTShirt(@PathVariable("id") int itemId) {
        tShirtService.removeTShirt(itemId);
    }

    // handles requests to update a tshirt with a matching id
    @RequestMapping(value = "/tshirts/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTShirt(@PathVariable("id") int itemId, @RequestBody @Valid TShirtViewModel itemViewModel) {
        if (itemViewModel.gettShirtId() == 0)
            itemViewModel.settShirtId(itemId);
        if (itemId != itemViewModel.gettShirtId()) {
            throw new IllegalArgumentException("Item ID on path must match the ID in the Item object");
        }
        tShirtService.updateTShirt(itemViewModel);
    }

    // handles requests to retrieve a list of all tshirts
    @RequestMapping(value = "/tshirts", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> getAllTShirts() {
        return tShirtService.findAllTShirts();
    }

    // handles requests to retrieve a list of tshirts with a matching color
    @RequestMapping(value = "/tshirts/colors/{color}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> findTShirtsByColor(@PathVariable String color) {
        return tShirtService.findTShirtsByColor(color);
    }

    // handles requests to retrieve a list of tshrts with a matching size
    @RequestMapping(value = "/tshirts/sizes/{size}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<TShirtViewModel> findTShirtsBySize(@PathVariable String size) {
        return tShirtService.findTShirtsBySize(size);
    }

}

