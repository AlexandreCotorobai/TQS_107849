package pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ReservePage {

    @FindBy(xpath = "/html/body/div[2]/h3")
    private WebElement title;

    @FindBy(xpath ="/html/body/div[2]/table/tbody/tr[1]/td[1]/input")
    private WebElement chooseFlightButton;

    @FindBy(xpath ="/html/body/div[2]/table/tbody/tr[1]/td[2]")
    private WebElement flightNumber;
    
    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[1]/td[3]")
    private WebElement airline;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[1]/td[4]")
    private WebElement departureTime;

    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[1]/td[5]")
    private WebElement arrivalTime;
    
    @FindBy(xpath = "/html/body/div[2]/table/tbody/tr[1]/td[6]")
    private WebElement price;

    public ReservePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getTitle() {
        return title.getText();
    }

    public void clickChooseFlightButton() {
        chooseFlightButton.click();
    }

    public String getFlightNumber() {
        return flightNumber.getText();
    }

    public String getAirline() {
        return airline.getText();
    }

    public String getDepartureTime() {
        return departureTime.getText();
    }

    public String getArrivalTime() {
        return arrivalTime.getText();
    }

    public String getPrice() {
        return price.getText();
    }


}
