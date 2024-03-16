package web;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.opentelemetry.api.internal.Utils;

import org.openqa.selenium.firefox.FirefoxDriver;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.DataTableType;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;

public class WebSteps {

    private final WebDriver driver = new FirefoxDriver();

    @Given("I am on the BlazeDemo homepage")
    public void i_am_on_the_blaze_demo_home_page() {
        driver.get("https://blazedemo.com/");
    }

    // @When("I write\\/select {string} on the {string} input")
    // public void i_select_on_the_input(String value, String input) {
    // WebElement element = driver.findElement(By.name(input));
    // element.sendKeys(value);
    // }

    @When("I select")
    public void setup(DataTable selectors) {
        List<Map<String, String>> rows = selectors.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            driver.findElement(By.name(row.get("Field"))).sendKeys(row.get("Value"));
        }
    }

    @When("I click on the {string} button")
    public void i_click_on_the_button(String button) {
        WebElement element = driver.findElement(By.xpath("//input[@type='submit' and @value='" + button + "']"));
        element.click();
    }

    @When("I select the {int} flight")
    public void i_click_on_the_button_on_flight(Integer flight) {
        List<WebElement> rows = driver.findElements(By.xpath("//tr[.//input[@type='submit']]"));
        WebElement element = null;
        for (WebElement row : rows) {
            WebElement flightNumber = row.findElement(By.xpath(".//td[2]")); // assuming the flight number is in the
                                                                             // second td
            if (Integer.parseInt(flightNumber.getText()) == flight) {
                element = row.findElement(By.xpath(".//input[@type='submit']"));
                break;
            }
        }
        if (element == null) {
            throw new RuntimeException("Flight not found");
        }
        element.click();
    }

    @Then("I should be in {string}")
    public void i_should_see_in_the_title(String title) {
        try {
            String actualTitle = driver.getTitle();
            assert actualTitle.contains(title);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After()
    public void closeBrowser() {
        driver.quit();
    }

}