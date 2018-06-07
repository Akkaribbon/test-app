package pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhonePage extends PageObject {

    public PhonePage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerFactory.getLogger(PhonePage.class);

    @FindBy(id="phone-input")
    private WebElementFacade phoneNumberInput;

    @FindBy(xpath = "//div[@class='widgt-input-handler']/button")
    private WebElementFacade nextButton;

    @FindBy(xpath = "//div[@class='alert-cloud']/span[@class='alert-text']")
    private WebElementFacade incorrectNumberAlert;

    public PhonePage populateCorrectPhoneNumber(String phoneNumber) {
        logger.debug("Populate phone input with correct number");
        enter(phoneNumber).into(phoneNumberInput);
        return this;
    }

    public PhonePage populateIncorrectPhoneNumber(String phoneNumber) {
        logger.debug("Populate phone input with incorrect number");
        enter(phoneNumber + Keys.ENTER).into(phoneNumberInput);
        return this;
    }

    public PasswordPage clickNextButton() {
        logger.debug("Click on next button");
        nextButton.waitUntilClickable().then().click();
        return new PasswordPage(getDriver());
    }

    public boolean checkIfValidationForIncorrectNumberAppears() {
        return incorrectNumberAlert.isPresent();
    }

    public String getTextFromValidationAlertForIncorrectNumber() {
        return incorrectNumberAlert.getText();
    }
}
