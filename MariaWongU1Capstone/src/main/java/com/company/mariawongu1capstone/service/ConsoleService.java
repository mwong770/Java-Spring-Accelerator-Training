package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.model.Console;
import com.company.mariawongu1capstone.viewmodel.ConsoleViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// USE MOCKS ********************

@Component
public class ConsoleService {

    ConsoleDao consoleDao;
    GameDao gameDao;
    InvoiceDao invoiceDao;
    ProcessingFeeDao processingFeeDao;
    SalesTaxRateDao salesTaxRateDao;
    TShirtDao tShirtDao;

    @Autowired
    public ConsoleService(ConsoleDao consoleDao, GameDao gameDao, InvoiceDao invoiceDao, ProcessingFeeDao processingFeeDao, SalesTaxRateDao salesTaxRateDao, TShirtDao tShirtDao) {
        this.consoleDao = consoleDao;
        this.gameDao = gameDao;
        this.invoiceDao = invoiceDao;
        this.processingFeeDao = processingFeeDao;
        this.salesTaxRateDao = salesTaxRateDao;
        this.tShirtDao = tShirtDao;
    }

    //
    // Console API
    //

    public ConsoleViewModel saveConsole(ConsoleViewModel consoleViewModel) {
        Console console = new Console();
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());
        console =  consoleDao.addConsole(console);

        consoleViewModel.setConsoleId(console.getConsoleId());
        return consoleViewModel;
    }

    public ConsoleViewModel findConsoleById(int id) {
        Console console = consoleDao.getConsole(id);
        if(console == null)
            return null;
        else
            return buildConsoleViewModel(console);
    }

    public List<ConsoleViewModel> findAllConsoles() {

        List<Console> consoles = consoleDao.getAllConsoles();

        List<ConsoleViewModel> consoleViewModels = new ArrayList<>();

        for (Console c: consoles) {
            consoleViewModels.add(buildConsoleViewModel(c));
        }
        return consoleViewModels;
    }

    public void updateConsole(ConsoleViewModel consoleViewModel) {
        Console console = new Console();
        console.setConsoleId(consoleViewModel.getConsoleId());
        console.setModel(consoleViewModel.getModel());
        console.setManufacturer(consoleViewModel.getManufacturer());
        console.setMemoryAmount(consoleViewModel.getMemoryAmount());
        console.setProcessor(consoleViewModel.getProcessor());
        console.setPrice(consoleViewModel.getPrice());
        console.setQuantity(consoleViewModel.getQuantity());

        consoleDao.updateConsole(console);
    }

    public void removeConsole(int id) {
        consoleDao.deleteConsole(id);
    }

    public List<ConsoleViewModel> findConsolesByManufacturer(String manufacturer) {

        List<Console> consoles = consoleDao.findConsolesByManufacturer(manufacturer);

        List<ConsoleViewModel> consoleViewModels = new ArrayList<>();

        for (Console c: consoles) {
            consoleViewModels.add(buildConsoleViewModel(c));
        }
        return consoleViewModels;
    }

    // Build View Model

    private ConsoleViewModel buildConsoleViewModel(Console console) {
        ConsoleViewModel consoleViewModel = new ConsoleViewModel();
        consoleViewModel.setConsoleId(console.getConsoleId());
        consoleViewModel.setModel(console.getModel());
        consoleViewModel.setManufacturer(console.getManufacturer());
        consoleViewModel.setMemoryAmount(console.getMemoryAmount());
        consoleViewModel.setProcessor(console.getProcessor());
        consoleViewModel.setPrice(console.getPrice());
        consoleViewModel.setQuantity(console.getQuantity());

        return consoleViewModel;
    }

}
