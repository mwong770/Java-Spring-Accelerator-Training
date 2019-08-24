package com.company.mariawongu1capstone.dao;

import com.company.mariawongu1capstone.model.TShirt;

import java.util.List;

public interface TShirtDao {

    // standard CRUD

    TShirt addTShirt(TShirt tShirt);

    TShirt getTShirt(int id);

    List<TShirt> getAllTShirts();

    void updateTShirt(TShirt tShirt);

    void deleteTShirt(int id);

    // additional methods

    List<TShirt> findTShirtsByColor(String color);

    List<TShirt> findTShirtsBySize(String size);

}
