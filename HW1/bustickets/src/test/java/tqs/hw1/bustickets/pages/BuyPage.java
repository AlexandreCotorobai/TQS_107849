package tqs.hw1.bustickets.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BuyPage {
    @FindBy(id = "fullName")
    private WebElement fullNameInput;

    @FindBy(id = "email")
    private WebElement emailInput;

    @FindBy(id = "phone")
    private WebElement phoneInput;

    @FindBy(id = "creditCardNumber")
    private WebElement creditCardNumberInput;

    @FindBy(id = "expirationDate")
    private WebElement expirationDateInput;

    @FindBy(id = "cvv")
    private WebElement cvvInput;

    @FindBy(css = ".py-2")
    private WebElement confirmPurchaseButton;

    public BuyPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void enterFullName(String fullName) {
        fullNameInput.click();
        fullNameInput.sendKeys(fullName);
    }

    public void enterEmail(String email) {
        emailInput.click();
        emailInput.sendKeys(email);
    }

    public void enterPhone(String phone) {
        phoneInput.click();
        phoneInput.sendKeys(phone);
    }

    public void enterCreditCardNumber(String creditCardNumber) {
        creditCardNumberInput.click();
        creditCardNumberInput.sendKeys(creditCardNumber);
    }

    public void enterExpirationDate(String expirationDate) {
        expirationDateInput.click();
        expirationDateInput.sendKeys(expirationDate);
    }

    public void enterCVV(String cvv) {
        cvvInput.click();
        cvvInput.sendKeys(cvv);
    }

    public void confirmPurchase() {
        confirmPurchaseButton.click();
    }

}