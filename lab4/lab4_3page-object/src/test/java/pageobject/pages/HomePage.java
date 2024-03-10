package pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(xpath = "/html/body/div[2]/div/h1")
    private WebElement title;

    @FindBy(name = "fromPort")
    private WebElement fromPortSelect;

    @FindBy(name = "toPort")
    private WebElement toPortSelect;

    @FindBy(xpath = "/html/body/div[3]/form/div/input")
    private WebElement findFlightsButton;

    public HomePage(WebDriver driver) {
        String URL = "https://blazedemo.com";
        driver.get(URL);
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public String getFromPort() {
        return fromPortSelect.getAttribute("value");
    }

    public void selectFromPort(String fromPort) {
        fromPortSelect.sendKeys(fromPort);
    }

    public String getToPort() {
        return toPortSelect.getAttribute("value");
    }

    public void selectToPort(String toPort) {
        toPortSelect.sendKeys(toPort);
    }

    public void clickFindFlights() {
        findFlightsButton.click();
    }

}