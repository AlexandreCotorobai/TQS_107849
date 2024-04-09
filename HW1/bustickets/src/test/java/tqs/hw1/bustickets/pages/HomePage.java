package tqs.hw1.bustickets.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "origin")
    private WebElement originInput;

    @FindBy(id = "destination")
    private WebElement destinationInput;

    @FindBy(id = "date")
    private WebElement dateInput;

    @FindBy(id = "currency")
    private WebElement currencyDropdown;

    @FindBy(xpath = "//*[@id='root']/div/div/div/form/button")
    private WebElement submitButton;

    @FindBy(xpath = "//*[@id='root']/div/div/div/table/tbody/tr/td[10]/button")
    private WebElement buyButton;

    public HomePage(WebDriver driver) {
        String URL = "http://localhost:5173/";
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    public void setOrigin(String origin) {
        originInput.sendKeys(origin);
    }

    public void setDestination(String destination) {
        destinationInput.sendKeys(destination);
    }

    public void selectDate(String date) {
        dateInput.sendKeys(date);
    }

    public void selectCurrency(String currency) {
        currencyDropdown.sendKeys(currency);
    }

    public void submitForm() {
        submitButton.click();
    }

    public boolean buyButtonExists() {
        return buyButton.isDisplayed();
    }

    public void clickOnBuyButton() {
        buyButton.click();
    }
    
}
