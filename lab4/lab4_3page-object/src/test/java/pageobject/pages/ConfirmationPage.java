package pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage {

    @FindBy(xpath ="/html/body/div[2]/div/h1")
    private WebElement title;

    @FindBy(xpath ="/html/body/div[2]/div/table/tbody/tr[1]/td[2]")
    private WebElement id;

    @FindBy(xpath ="/html/body/div[2]/div/table/tbody/tr[2]/td[2]")
    private WebElement status;

    @FindBy(xpath ="/html/body/div[2]/div/table/tbody/tr[3]/td[2]")
    private WebElement amount;

    @FindBy(xpath ="/html/body/div[2]/div/table/tbody/tr[4]/td[2]")
    private WebElement cardNumber;

    @FindBy(xpath ="/html/body/div[2]/div/table/tbody/tr[5]/td[2]")
    private WebElement expirationDate;

    @FindBy(xpath ="/html/body/div[2]/div/table/tbody/tr[6]/td[2]")
    private WebElement authCode;

    @FindBy(xpath ="/html/body/div[2]/div/table/tbody/tr[7]/td[2]")
    private WebElement date;

    public ConfirmationPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getId() {
        return id.getText();
    }

    public String getStatus() {
        return status.getText();
    }

    public String getAmount() {
        return amount.getText();
    }

    public String getCardNumber() {
        return cardNumber.getText();
    }

    public String getExpirationDate() {
        return expirationDate.getText();
    }

    public String getAuthCode() {
        return authCode.getText();
    }

    public String getDate() {
        return date.getText();
    }
}