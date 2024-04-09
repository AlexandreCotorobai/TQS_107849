package tqs.hw1.bustickets.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DetailsPage {
    
    @FindBy(xpath = "//*[@id='root']/div/div/div/div/div[3]/p")
    private WebElement ticketDetails;

    @FindBy(linkText = "Bus Tickets")
    private WebElement busTicketsLink;

    @FindBy(css = ".py-1:nth-child(1)")
    private WebElement reservationCodeInput;

    @FindBy(css = ".ml-2")
    private WebElement submitButton;

    public DetailsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public String getTicketDetails() {
        String res = ticketDetails.getText();
        return res.split(": ")[1];
    }

    // Methods to interact with elements
    public void clickBusTicketsLink() {
        busTicketsLink.click();
    }

    public void typeReservationCode(String ticketcode) {
        reservationCodeInput.clear();
        reservationCodeInput.sendKeys(ticketcode);
    }

    public void clickSubmitButton() {
        submitButton.click();
    }
}
