package book;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


import org.slf4j.Logger;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BookSteps {
    private Library library = new Library();
    private List<Book> result;

    static final Logger log = getLogger(lookup().lookupClass());

    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDate iso8601Date(String year, String month, String day) {
        return Utils.localDateFromDateParts(year, month, day);
    }

    @DataTableType
    public Book bookEntry(Map<String, String> tableEntry) {
        return new Book(
                tableEntry.get("title"),
                tableEntry.get("author"),
                Utils.isoTextToLocalDate(tableEntry.get("published")));
    }

    @Given("the following books")
    public void setup(DataTable books) {
        List<Map<String, String>> rows = books.asMaps(String.class, String.class);
        library = new Library();
        for (Map<String, String> row : rows) {
            Book book = bookEntry(row);
            library.addBook(book);
        }
    }

    

    @Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
    public void addNewBook(String title, String author, LocalDate published) throws ParseException {
        log.debug("Adding book '{}' written by '{}' and published in {}", title, author, published);
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @Given("another book with the title {string}, written by {string}, published in {iso8601Date}")
    public void addAnotherNewBook(String title, String author, LocalDate published) throws ParseException {
        log.debug("Adding book '{}' written by '{}' and published in {}", title, author, published);
        Book book = new Book(title, author, published);
        library.addBook(book);
    }

    @When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
    public void setSearchParameters(LocalDate from, LocalDate to) {
        log.debug("Searching for books published between {} and {}", from, to);
        result = library.findBooks(from, to);
    }

    @Then("{int} books should have been found")
    public void verifyAmountOfBooksFound(int booksFound) {
        log.debug("Books found: {} (expected {})", result.size(), booksFound);
        assertEquals(booksFound, result.size());
    }

    @Then("Book {int} should have the title {string}")
    public void verifyBookAtPosition(int position, String title) {
        log.debug("Book at position {}: '{}' (expected '{}')", position, result.get(position - 1).getTitle(), title);
        assertEquals(title, result.get(position - 1).getTitle());
    }

    @When("the customer searches for books written by {string}")
    public void setSearchParameters(String author) {
        result = library.findBooksByAuthor(author);
    }

}