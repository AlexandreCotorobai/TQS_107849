package tqs.hw1.bustickets.BDDWithSeleniumTests;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertTrue;

import java.util.Map;

import tqs.hw1.bustickets.pages.*;

public class TicketPurchaseSteps {
    private WebDriver driver;
    private HomePage homePage;
    private BuyPage buyPage;
    private DetailsPage detailsPage;

    private String ticketcode;

    @Given("I am on homepage")
    public void the_user_is_on_the_homepage() {
        driver = WebDriverManager.chromedriver().create();
        homePage = new HomePage(driver);
    }

    @When("I filter the origin to {string}")
    public void i_filter_the_origin(String origin) {
        homePage.setOrigin(origin);
    }

    @When("I filter the destination to {string}")
    public void i_filter_the_destination(String destination) {
        homePage.setDestination(destination);
    }

    @When("I select the day of departure for {string}")
    public void i_select_the_day_of_departure(String date) {
        homePage.selectDate(date);
    }

    @When("I select the currency {string}")
    public void i_select_the_currency(String currency) {
        homePage.selectCurrency(currency);
    }

    @When("I click on Submit")
    public void i_click_on_submit() {
        homePage.submitForm();
    }

    @Then("I should see the ticket list")
    public void i_should_see_the_ticket_list() {
        try {
            // Usefull because of delays in the api
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(homePage.buyButtonExists());
    }

    @When("I click on the first ticket")
    public void i_click_on_the_first_ticket() {

        homePage.clickOnBuyButton();
    }

    @Then("I should be in {string}")
    public void i_should_be_in(String expectedTitle) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        try {
            String actualTitle = driver.getTitle();
            assertTrue(actualTitle.contains(expectedTitle));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("I fill the form with the following data:")
    public void i_fill_the_form_with_the_following_data(DataTable dataTable) {
        buyPage = new BuyPage(driver);
        Map<String, String> data = dataTable.transpose().asMap(String.class, String.class);
        buyPage.enterFullName(data.get("Name"));
        buyPage.enterEmail(data.get("Email"));
        buyPage.enterPhone(data.get("Phone"));
        buyPage.enterCreditCardNumber(data.get("Card"));
        buyPage.enterExpirationDate(data.get("Expire Date"));
        buyPage.enterCVV(data.get("CVV"));
    }

    @When("I click on confirm purchase")
    public void i_click_on_confirm_purchase() {
        buyPage.confirmPurchase();
    }

    @When("I get my reservation code")
    public void i_get_my_reservation_code() {
        detailsPage = new DetailsPage(driver);
        ticketcode = detailsPage.getTicketDetails();
    }
    
    @When("I click on 'Back to homepage'")
    public void i_click_on_back_to_homepage() {
        detailsPage.clickBusTicketsLink();
    }
    
    @When("I type my reservation code")
    public void i_type_my_reservation_code() {
        detailsPage.typeReservationCode(ticketcode);
        detailsPage.clickSubmitButton();
    }

}
