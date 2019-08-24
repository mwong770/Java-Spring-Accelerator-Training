package com.company.mariawongu1m5summative.dao;

import com.company.mariawongu1m5summative.model.Publisher;

import java.util.List;

// Data Access Object Interface
// Defines the standard operations to be performed on the Publisher model object
public interface PublisherDao {

    // method declarations

    Publisher getPublisher(int id);

    List<Publisher> getAllPublishers();

    Publisher addPublisher(Publisher publisher);

    void updatePublisher(Publisher publisher);

    void deletePublisher(int id);

}
