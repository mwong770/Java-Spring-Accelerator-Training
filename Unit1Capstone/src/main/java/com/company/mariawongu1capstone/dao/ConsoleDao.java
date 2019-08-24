package com.company.mariawongu1capstone.dao;

import com.company.mariawongu1capstone.model.Console;

import java.util.List;

public interface ConsoleDao {

    // standard CRUD

    Console addConsole(Console console);

    Console getConsole(int id);

    List<Console> getAllConsoles();

    void updateConsole(Console console);

    void deleteConsole(int id);

    // additional methods

    List<Console> findConsolesByManufacturer(String manufacturer);

}
