package com.company.mariawongu1capstone.service;

import com.company.mariawongu1capstone.dao.*;
import com.company.mariawongu1capstone.model.TShirt;
import com.company.mariawongu1capstone.viewmodel.TShirtViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TShirtService {

    TShirtDao tShirtDao;

    @Autowired
    public TShirtService(TShirtDao tShirtDao) {
        this.tShirtDao = tShirtDao;
    }

    // adds tshirt to database
    public TShirtViewModel saveTShirt(TShirtViewModel tShirtViewModel) {
        TShirt tShirt = new TShirt();
        tShirt.setSize(tShirtViewModel.getSize());
        tShirt.setColor(tShirtViewModel.getColor());
        tShirt.setDescription(tShirtViewModel.getDescription());
        tShirt.setPrice(tShirtViewModel.getPrice());
        tShirt.setQuantity(tShirtViewModel.getQuantity());
        tShirt = tShirtDao.addTShirt(tShirt);

        tShirtViewModel.settShirtId(tShirt.gettShirtId());
        return tShirtViewModel;
    }

    // retrieve a tshirt with a matching id
    public TShirtViewModel findTShirtById(int id) {
        TShirt tShirt = tShirtDao.getTShirt(id);
        if (tShirt == null)
            return null;
        else
            return buildTShirtViewModel(tShirt);
    }

    // retrieves a list of all tshirts
    public List<TShirtViewModel> findAllTShirts() {

        List<TShirt> tShirts = tShirtDao.getAllTShirts();

        List<TShirtViewModel> tShirtViewModels = new ArrayList<>();

        for (TShirt t : tShirts) {
            tShirtViewModels.add(buildTShirtViewModel(t));
        }
        return tShirtViewModels;
    }

    // updates a tshirt with a matching id
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

    // deletes a tshirt with a matching id
    public void removeTShirt(int id) {
        tShirtDao.deleteTShirt(id);
    }

    // retrieves a list of tshirts with a matching color
    public List<TShirtViewModel> findTShirtsByColor(String color) {

        List<TShirt> tShirts = tShirtDao.findTShirtsByColor(color);

        List<TShirtViewModel> tShirtViewModels = new ArrayList<>();

        for (TShirt t : tShirts) {
            tShirtViewModels.add(buildTShirtViewModel(t));
        }
        return tShirtViewModels;
    }

    // retrieves a list of tshirts with a matching size
    public List<TShirtViewModel> findTShirtsBySize(String size) {

        List<TShirt> tShirts = tShirtDao.findTShirtsBySize(size);

        List<TShirtViewModel> tShirtViewModels = new ArrayList<>();

        for (TShirt t : tShirts) {
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
