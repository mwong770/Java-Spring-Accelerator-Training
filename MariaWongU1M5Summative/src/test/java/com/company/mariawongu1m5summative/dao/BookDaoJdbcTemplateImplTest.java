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

import java.text.ParseException;
import java.text.SimpleDateFormat;

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
        publisher.setName("Mathews");
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
        book.setPrice(4.63);
        book.setIsbn("New Orleans");
        try {
            book.setPublishDate(new SimpleDateFormat("dd/MM/yyyy").parse("28/01/2018"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setTitle("70114");

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
        publisher.setName("Mathews");
        publisher.setStreet("Read Blvd.");
        publisher.setCity("New Orleans");
        publisher.setState("LA");
        publisher.setPostalCode("70114");
        publisher.setPhone("1-504-382-5729");
        publisher.setEmail("mathews@yahoo.com");

        publisher = PublisherDao.addPublisher(publisher);

        // adds two books to the db
        Book book = new Book();
        book.setAuthorId(author.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(4.63);
        book.setIsbn("New Orleans");
        try {
            book.setPublishDate(new SimpleDateFormat("dd/MM/yyyy").parse("28/01/2018"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setTitle("70114");

        BookDao.addBook(book);

        book = new Book();
        book.setAuthorId(author.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(27.00);
        book.setIsbn("88886656");
        try {
            book.setPublishDate(new SimpleDateFormat("dd/MM/yyyy").parse("28/01/2018"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setTitle("hgkjhg");

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
        publisher.setName("Mathews");
        publisher.setStreet("Read Blvd.");
        publisher.setCity("New Orleans");
        publisher.setState("LA");
        publisher.setPostalCode("70114");
        publisher.setPhone("1-504-382-5729");
        publisher.setEmail("mathews@yahoo.com");

        publisher = PublisherDao.addPublisher(publisher);

        // adds a book to the db
        Book book = new Book();
        book.setAuthorId(author.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(4.63);
        book.setIsbn("New Orleans");
        try {
            book.setPublishDate(new SimpleDateFormat("dd/MM/yyyy").parse("28/01/2018"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setTitle("70114");

        book = BookDao.addBook(book);

        // updates the above book info
        book.setPrice(21.00);

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
        author.setFirstName("Thomas");
        author.setLastName("McKenzie");
        author.setStreet("Adams St.");
        author.setCity("New Orleans");
        author.setState("LA");
        author.setPostalCode("70043");
        author.setPhone("1-504-635-2847");
        author.setEmail("mckenzie@yahoo.com");

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
        publisher.setName("Mathews");
        publisher.setStreet("Read Blvd.");
        publisher.setCity("New Orleans");
        publisher.setState("LA");
        publisher.setPostalCode("70114");
        publisher.setPhone("1-504-382-5729");
        publisher.setEmail("mathews@yahoo.com");

        publisher = PublisherDao.addPublisher(publisher);

        // adds book with first author added
        Book book = new Book();
        book.setAuthorId(author.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(12.50);
        book.setIsbn("aaaaaaaaaaa");
        try {
            book.setPublishDate(new SimpleDateFormat("dd/MM/yyyy").parse("28/01/2018"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setTitle("70114");

        BookDao.addBook(book);

        // adds book with first author added
        book = new Book();
        book.setAuthorId(author.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(4.63);
        book.setIsbn("bbbbbbbbbb");
        try {
            book.setPublishDate(new SimpleDateFormat("dd/MM/yyyy").parse("12/01/2009"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setTitle("70114");

        BookDao.addBook(book);

        // adds book with second author added
        book = new Book();
        book.setAuthorId(author2.getAuthorId());
        book.setPublisherId(publisher.getPublisherId());
        book.setPrice(21.00);
        book.setIsbn("cccccccc");
        try {
            book.setPublishDate(new SimpleDateFormat("dd/MM/yyyy").parse("03/01/1999"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        book.setTitle("70114");

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
