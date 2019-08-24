package com.company.mariawongu1m5summative.dao;

import com.company.mariawongu1m5summative.model.Author;
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
public class AuthorDaoJdbcTemplateImplTest {

    @Autowired
    protected AuthorDao AuthorDao;

    // removes authors from the db
    @Before
    public void setUp() throws Exception {

        // gets a list of all authors
        List<Author> authorList = AuthorDao.getAllAuthors();

        // uses the author IDs to delete them from the db
        authorList.stream()
                .forEach(author -> AuthorDao.deleteAuthor(author.getAuthorId()));
    }

    @After
    public void tearDown() throws Exception {
    }

    // tests addAuthor(), getAuthor() and deleteAuthor()
    @Test
    public void addGetDeleteAuthor() {

        // creates and adds an author to the db
        Author author = new Author();
        author.setFirstName("Daniel");
        author.setLastName("Mathews");
        author.setStreet("Read Blvd.");
        author.setCity("New Orleans");
        author.setState("LA");
        author.setPostalCode("70114");
        author.setPhone("1-504-382-5729");
        author.setEmail("mathews@yahoo.com");

        author = AuthorDao.addAuthor(author);

        // uses the id of the added author to retrieve it from the db
        Author author2 = AuthorDao.getAuthor(author.getAuthorId());

        // checks if the author was added to the db and retrieved successfully
        assertEquals(author, author2);

        // uses the id of the added author to delete it from the db
        AuthorDao.deleteAuthor(author.getAuthorId());

        // uses the id of the added author to retrieve it from the db
        author2 = AuthorDao.getAuthor(author.getAuthorId());

        // checks if the author was deleted from the db
        assertNull(author2);

    }

    // tests getAllAuthors()
    @Test
    public void getAllAuthors() {

        // adds two authors to the db
        Author author = new Author();
        author.setFirstName("Thomas");
        author.setLastName("McKenzie");
        author.setStreet("Adams St.");
        author.setCity("New Orleans");
        author.setState("LA");
        author.setPostalCode("70043");
        author.setPhone("1-504-635-2847");
        author.setEmail("mckenzie@yahoo.com");

        AuthorDao.addAuthor(author);

        author = new Author();
        author.setFirstName("Olivia");
        author.setLastName("Parker");
        author.setStreet("Reese Blvd.");
        author.setCity("Huntersville");
        author.setState("NC");
        author.setPostalCode("28078");
        author.setPhone("1-919-604-8438");
        author.setEmail("parker@yahoo.com");

        AuthorDao.addAuthor(author);

        // retrieves a list of all authors from the db
        List<Author> authorList = AuthorDao.getAllAuthors();

        // determines if the two added authors were retrieved
        assertEquals(2, authorList.size());

    }


    // tests updateAuthor()
    @Test
    public void updateAuthor() {

        // adds an author to the db
        Author author = new Author();
        author.setFirstName("Cristina");
        author.setLastName("Rodriguez");
        author.setStreet("Sturdivant St.");
        author.setCity("Cary");
        author.setState("NC");
        author.setPostalCode("27511");
        author.setPhone("1-919-725-1749");
        author.setEmail("rodriguez@yahoo.com");

        AuthorDao.addAuthor(author);

        // updates the above author info
        author.setStreet("Dominian Village Dr.");
        author.setCity("Charlotte");
        author.setState("NC");

        // updates the author in the db with the new info
        AuthorDao.updateAuthor(author);

        // retrieves the updated author from the db
        Author author2 = AuthorDao.getAuthor(author.getAuthorId());

        // checks if the author in the db was updated with the new info
        assertEquals(author, author2);
    }

}
