package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.model.TShirt;
import com.company.mariawongu1capstone.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

// USE MOCKS ********************

@Component
public class TShirtService {

    ConsoleDao consoleDao;
    GameDao gameDao;
    InvoiceDao invoiceDao;
    ProcessingFeeDao processingFeeDao;
    SalesTaxRateDao salesTaxRateDao;
    TShirtDao tShirtDao;

    @Autowired
    public TShirtService(ConsoleDao consoleDao, GameDao gameDao, InvoiceDao invoiceDao, ProcessingFeeDao processingFeeDao, SalesTaxRateDao salesTaxRateDao, TShirtDao tShirtDao) {
        this.consoleDao = consoleDao;
        this.gameDao = gameDao;
        this.invoiceDao = invoiceDao;
        this.processingFeeDao = processingFeeDao;
        this.salesTaxRateDao = salesTaxRateDao;
        this.tShirtDao = tShirtDao;
    }

    //
    // TShirt API
    //

    public TShirtViewModel saveTShirt(TShirtViewModel tShirtViewModel) {
        TShirt tShirt = new TShirt();
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());
        tShirt =  tShirtDao.addTShirt(tShirt);

        tShirtViewModel.settShirtId(tShirt.gettShirtId());
        return tShirtViewModel;
    }

    public TShirtViewModel findTShirtById(int id) {
        TShirt tShirt =  tShirtDao.getTShirt(id);
        if(tShirt == null)
            return null;
        else
            return buildTShirtViewModel(tShirt);
    }

    public List<TShirtViewModel> findAllTShirts() {

        List<TShirt> tShirts = tShirtDao.getAllTShirts();

        List<TShirtViewModel> tShirtViewModels = new ArrayList<>();

        for (TShirt t: tShirts) {
            tShirtViewModels.add(buildTShirtViewModel(t));
        }
        return tShirtViewModels;
    }

    public void updateTShirt(TShirtViewModel tShirtViewModel) {
        TShirt tShirt = new TShirt();
        tShirt.settShirtId(tShirtViewModel.gettShirtId());
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());

        tShirtDao.updateTShirt(tShirt);
    }

    public void removeTShirt(int id) {
        tShirtDao.deleteTShirt(id);
    }

    public List<TShirtViewModel> findTShirtsByColor(String color) {

        List<TShirt> tShirts = tShirtDao.findTShirtsByColor(color);

        List<TShirtViewModel> tShirtViewModels = new ArrayList<>();

        for (TShirt t: tShirts) {
            tShirtViewModels.add(buildTShirtViewModel(t));
        }
        return tShirtViewModels;
    }

    public List<TShirtViewModel> findTShirtsBySize(String size) {

        List<TShirt> tShirts = tShirtDao.findTShirtsBySize(size);

        List<TShirtViewModel> tShirtViewModels = new ArrayList<>();

        for (TShirt t: tShirts) {
            tShirtViewModels.add(buildTShirtViewModel(t));
        }
        return tShirtViewModels;
    }



    // Build View Model

    private TShirtViewModel buildTShirtViewModel(TShirt tShirt) {
        TShirtViewModel tShirtViewModel = new TShirtViewModel();
        tShirtViewModel.settShirtId(tShirt.gettShirtId());
        tShirtViewModel.setSize(tShirt.getSize());
        tShirtViewModel.setColor(tShirt.getColor());
        tShirtViewModel.setDescription(tShirt.getDescription());
        tShirtViewModel.setPrice(tShirt.getPrice());
        tShirtViewModel.setQuantity(tShirt.getQuantity());

        return tShirtViewModel;
    }
}
