package com.company.mariawongu1m5summative.dao;

import com.company.mariawongu1m5summative.model.Publisher;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PublisherDaoJdbcTemplateImplTest {

    @Autowired
    protected PublisherDao PublisherDao;

    // removes publishers from the db
    @Before
    public void setUp() throws Exception {

        // gets a list of all publishers
        List<Publisher> publisherList = PublisherDao.getAllPublishers();

        // uses the publisher IDs to delete them from the db
        publisherList.stream()
                .forEach(publisher -> PublisherDao.deletePublisher(publisher.getPublisherId()));

    }

    @After
    public void tearDown() throws Exception {
    }



    // tests addPublisher(), getPublisher() and deletePublisher()
    @Test
    public void addGetDeletePublisher() {

        // creates and adds a publisher to the db
        Publisher publisher = new Publisher();
        publisher.setName("Mathews");
        publisher.setStreet("Read Blvd.");
        publisher.setCity("New Orleans");
        publisher.setState("LA");
        publisher.setPostalCode("70114");
        publisher.setPhone("1-504-382-5729");
        publisher.setEmail("mathews@yahoo.com");

        publisher = PublisherDao.addPublisher(publisher);

        // uses the id of the added publisher to retrieve it from the db
        Publisher publisher2 = PublisherDao.getPublisher(publisher.getPublisherId());

        // checks if the publisher was added to the db and retrieved successfully
        assertEquals(publisher, publisher2);

        // uses the id of the added publisher to delete it from the db
        PublisherDao.deletePublisher(publisher.getPublisherId());

        // uses the id of the added publisher to retrieve it from the db
        publisher2 = PublisherDao.getPublisher(publisher.getPublisherId());

        // checks if the publisher was deleted from the db
        assertNull(publisher2);

    }

    // tests getAllPublishers()
    @Test
    public void getAllPublishers() {

        // adds two publishers to the db
        Publisher publisher = new Publisher();
        publisher.setName("McKenzie");
        publisher.setStreet("Adams St.");
        publisher.setCity("New Orleans");
        publisher.setState("LA");
        publisher.setPostalCode("70043");
        publisher.setPhone("1-504-635-2847");
        publisher.setEmail("mckenzie@yahoo.com");

        PublisherDao.addPublisher(publisher);

        publisher = new Publisher();
        publisher.setName("Parker");
        publisher.setStreet("Reese Blvd.");
        publisher.setCity("Huntersville");
        publisher.setState("NC");
        publisher.setPostalCode("28078");
        publisher.setPhone("1-919-604-8438");
        publisher.setEmail("parker@yahoo.com");

        PublisherDao.addPublisher(publisher);

        // retrieves a list of all publishers from the db
        List<Publisher> publisherList = PublisherDao.getAllPublishers();

        // determines if the two added publishers were retrieved
        assertEquals(2, publisherList.size());

    }

    // tests updatePublisher()
    @Test
    public void updatePublisher() {

        // adds a publisher to the db
        Publisher publisher = new Publisher();
        publisher.setName("Rodriguez");
        publisher.setStreet("Sturdivant St.");
        publisher.setCity("Cary");
        publisher.setState("NC");
        publisher.setPostalCode("27511");
        publisher.setPhone("1-919-725-1749");
        publisher.setEmail("rodriguez@yahoo.com");

        PublisherDao.addPublisher(publisher);

        // updates the above publisher info
        publisher.setStreet("Dominian Village Dr.");
        publisher.setCity("Charlotte");
        publisher.setState("NC");

        // updates the publisher in the db with the new info
        PublisherDao.updatePublisher(publisher);

        // retrieves the updated publisher from the db
        Publisher publisher2 = PublisherDao.getPublisher(publisher.getPublisherId());

        // checks if the publisher in the db was updated with the new info
        assertEquals(publisher, publisher2);

    }


}
