package com.company.mariawongu1m5summative.dao;

import com.company.mariawongu1m5summative.model.Author;
import com.company.mariawongu1m5summative.model.Book;
import com.company.mariawongu1m5summative.model.Publisher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BookDaoJdbcTemplateImplTest {

    @Autowired
    protected AuthorDao AuthorDao;
    @Autowired
    protected BookDao BookDao;
    @Autowired
    protected PublisherDao PublisherDao;

    @Before
    public void setUp() throws Exception {

        // removes books from the db
        // books must be deleted first b/c it uses authorId and publisherId as FK
        List<Book> bookList = BookDao.getAllBooks();

        bookList.stream()
                .forEach(book -> BookDao.deleteBook(book.getBookId()));

        // removes authors from the db
        List<Author> authorList = AuthorDao.getAllAuthors();

        authorList.stream()
                .forEach(author -> AuthorDao.deleteAuthor(author.getAuthorId()));

        // removes publishers from the db
        List<Publisher> publisherList = PublisherDao.getAllPublishers();

        publisherList.stream()
                .forEach(publisher -> PublisherDao.deletePublisher(publisher.getPublisherId()));

    }

    @After
    public void tearDown() throws Exception {
    }

    // tests addBook(), getBook() and deleteBook()
    @Test
    public void addGetDeleteBook() {

        // adds author to populate foreign key in book
        Author author = new Author();
        author.setFirstName("Thomas");
        author.setLastName("McKenzie");
        author.setStreet("Adams St.");
        author.setCity("New Orleans");
        author.setState("LA");
        author.setPostalCode("70043");
        author.setPhone("1-504-635-2847");
        author.setEmail("mckenzie@yahoo.com");

        author = AuthorDao.addAuthor(author);

        // adds publisher to populate foreign key in book
        Publisher publisher = new Publisher();
        publisher.setName("DX Publishing");
        publisher.setStreet("Read Blvd.");
        publisher.setCity("New Orleans");
        publisher.setState("LA");
        publisher.setPostalCode("70114");
        publisher.setPhone("1-504-382-5729");
        publisher.setEmail("mathews@yahoo.com");

        publisher = PublisherDao.addPublisher(publisher);

        // creates and adds a book to the db
        Book book = new Book();
        book.setAuthorId(author.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("4.63"));
        book.setIsbn("978-13-601970-1");
        book.setPublishDate(LocalDate.of(2018, 1, 28));
        book.setTitle("The Dark Side");

        book = BookDao.addBook(book);

        // uses the id of the added book to retrieve it from the db
        Book book2 = BookDao.getBook(book.getBookId());

        // checks if the book was added to the db and retrieved successfully
        assertEquals(book, book2);

        // uses the id of the book, author, and publisher created above to delete them from the db
        BookDao.deleteBook(book.getBookId());
        AuthorDao.deleteAuthor(author.getAuthorId());
        PublisherDao.deletePublisher(publisher.getPublisherId());

        // uses the id of the added book to retrieve it from the db
        book2 = BookDao.getBook(book.getBookId());

        // checks if the book was deleted from the db
        assertNull(book2);

    }

    // tests getAllBooks()
    @Test
    public void getAllBooks() {

        // adds author to populate foreign key in book
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

        // adds publisher to populate foreign key in book
        Publisher publisher = new Publisher();
        publisher.setName("Readers Press");
        publisher.setStreet("Adams St.");
        publisher.setCity("New Orleans");
        publisher.setState("LA");
        publisher.setPostalCode("70043");
        publisher.setPhone("1-504-635-2847");
        publisher.setEmail("mckenzie@yahoo.com");

        publisher = PublisherDao.addPublisher(publisher);

        // adds two books to the db
        Book book = new Book();
        book.setAuthorId(author.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("14.60"));
        book.setIsbn("934-17-385762-3");
        book.setPublishDate(LocalDate.of(2016, 3, 20));
        book.setTitle("Another Afternoon");

        BookDao.addBook(book);

        book = new Book();
        book.setAuthorId(author.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("27.00"));
        book.setIsbn("472-64-947255-4");
        book.setPublishDate(LocalDate.of(2009, 3, 23));
        book.setTitle("When You're Not Home");

        BookDao.addBook(book);

        // retrieves a list of all books from the db
        List<Book> bookList = BookDao.getAllBooks();

        // determines if the two added books were retrieved
        assertEquals(2, bookList.size());

    }

    // tests updateBook()
    @Test
    public void updateBook() {

        // adds author to populate foreign key in book
        Author author = new Author();
        author.setFirstName("Cristina");
        author.setLastName("Rodriguez");
        author.setStreet("Sturdivant St.");
        author.setCity("Cary");
        author.setState("NC");
        author.setPostalCode("27511");
        author.setPhone("1-919-725-1749");
        author.setEmail("rodriguez@yahoo.com");

        author = AuthorDao.addAuthor(author);

        // adds publisher to populate foreign key in book
        Publisher publisher = new Publisher();
        publisher.setName("ELI Magazine");
        publisher.setStreet("Sturdivant St.");
        publisher.setCity("Cary");
        publisher.setState("NC");
        publisher.setPostalCode("27511");
        publisher.setPhone("1-919-725-1749");
        publisher.setEmail("rodriguez@yahoo.com");

        publisher = PublisherDao.addPublisher(publisher);

        // adds a book to the db
        Book book = new Book();
        book.setAuthorId(author.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("43.00"));
        book.setIsbn("368-24-193654-2");
        book.setPublishDate(LocalDate.of(2018, 1, 28));
        book.setTitle("A Look at New Orleans");

        book = BookDao.addBook(book);

        // updates the above book info
        book.setPrice(new BigDecimal("24.00"));

        // updates the book in the db with the new info
        BookDao.updateBook(book);

        // retrieves the updated book from the db
        Book book2 = BookDao.getBook(book.getBookId());

        // checks if the book in the db was updated with the new info
        assertEquals(book, book2);
    }

    // tests getBooksByAuthor()
    @Test
    public void getBooksByAuthor() {

        // adds two different authors
        Author author = new Author();
        author.setFirstName("Jaqueline");
        author.setLastName("Randol");
        author.setStreet("Peace St.");
        author.setCity("New Orleans");
        author.setState("LA");
        author.setPostalCode("70043");
        author.setPhone("1-504-468-2585");
        author.setEmail("randol@yahoo.com");

        AuthorDao.addAuthor(author);

        Author author2 = new Author();
        author2.setFirstName("Olivia");
        author2.setLastName("Parker");
        author2.setStreet("Reese Blvd.");
        author2.setCity("Huntersville");
        author2.setState("NC");
        author2.setPostalCode("28078");
        author2.setPhone("1-919-604-8438");
        author2.setEmail("parker@yahoo.com");

        AuthorDao.addAuthor(author2);

        // add publisher to populate foreign key in book
        Publisher publisher = new Publisher();
        publisher.setName("Black Publications");
        publisher.setStreet("Reese Blvd.");
        publisher.setCity("Huntersville");
        publisher.setState("NC");
        publisher.setPostalCode("28078");
        publisher.setPhone("1-919-604-8438");
        publisher.setEmail("parker@yahoo.com");

        publisher = PublisherDao.addPublisher(publisher);

        // adds book with first author added
        Book book = new Book();
        book.setAuthorId(author.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("12.50"));
        book.setIsbn("235-27-284752-5");
        book.setPublishDate(LocalDate.of(2018, 1, 28));
        book.setTitle("Stop!");

        BookDao.addBook(book);

        // adds book with first author added
        book = new Book();
        book.setAuthorId(author.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("38.00"));
        book.setIsbn("248-64-739502-6");
        book.setPublishDate(LocalDate.of(2009, 12, 1));
        book.setTitle("A Piece of Heaven");

        BookDao.addBook(book);

        // adds book with second author added
        book = new Book();
        book.setAuthorId(author2.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(new BigDecimal("21.00"));
        book.setIsbn("278-94-385037-3");
        book.setPublishDate(LocalDate.of(1999, 1, 3));
        book.setTitle("Dogs or Cats");

        BookDao.addBook(book);

        // gets a list of all books by the first author added
        List<Book> bookList = BookDao.getBooksByAuthor(author.getAuthorId());

        // checks to see if both books by the first author are returned
        assertEquals(2, bookList.size());

        // gets a list of all books by the second author added
        bookList = BookDao.getBooksByAuthor(author2.getAuthorId());

        // checks to see if the book by the second author is returned
        assertEquals(1, bookList.size());

        // requests a book by an author that does not exist
        bookList = BookDao.getBooksByAuthor(20);

        // checks if no books are returned
        assertEquals(0, bookList.size());

    }

}
