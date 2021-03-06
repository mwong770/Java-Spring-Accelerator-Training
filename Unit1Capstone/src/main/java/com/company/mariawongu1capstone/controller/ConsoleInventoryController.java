package com.company.mariawongu1capstone.controller;

import com.company.mariawongu1capstone.exception.NotFoundException;
import com.company.mariawongu1capstone.service.ConsoleService;
import com.company.mariawongu1capstone.viewmodel.ConsoleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ConsoleInventoryController {

    @Autowired
    ConsoleService consoleService;

    // handles requests to add a console
    @RequestMapping(value="/consoles", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ConsoleViewModel createConsole(@RequestBody @Valid ConsoleViewModel console) {
            return consoleService.saveConsole(console);
    }

    // handles requests to retrieve a console by id
    @RequestMapping(value="/consoles/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public ConsoleViewModel getConsole(@PathVariable("id") int itemId) {
        ConsoleViewModel item = consoleService.findConsoleById(itemId);
        if (item == null)
            throw new NotFoundException("Item could not be retrieved for id " + itemId);
        return item;
    }

    // handles requests to delete a console by id
    @RequestMapping(value="/consoles/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable("id") int itemId) {
        consoleService.removeConsole(itemId);
    }

    // handles requests to update a console with a matching id
    @RequestMapping(value="/consoles/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateConsole(@PathVariable("id") int itemId, @RequestBody @Valid ConsoleViewModel itemViewModel) {
        if (itemViewModel.getConsoleId() == 0)
            itemViewModel.setConsoleId(itemId);
        if (itemId != itemViewModel.getConsoleId()) {
            throw new IllegalArgumentException("Item ID on path must match the ID in the Item object");
        }
        consoleService.updateConsole(itemViewModel);
    }

    // handles requests to retrieve a list of all consoles
    @RequestMapping(value="/consoles", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> getAllConsoles() {
        return consoleService.findAllConsoles();
    }


    // handles requests to retrieve a list of consoles with a matching manufacturer
    @RequestMapping(value="/consoles/manufacturers/{manufacturer}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<ConsoleViewModel> findConsolesByManufacturer(@PathVariable String manufacturer) {
        return consoleService.findConsolesByManufacturer(manufacturer);
    }

}
