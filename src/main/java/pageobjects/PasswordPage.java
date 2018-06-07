package pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class PasswordPage extends PageObject {

    public PasswordPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerFactory.getLogger(PasswordPage.class);

    @FindBy(id = "phone-input")
    private WebElementFacade passwordInput;

    @FindBy(xpath = "//div[@class='input-handler']/button")
    private WebElementFacade logInButton;

    @FindBy(xpath = "//div[@class='alert-cloud']/span[@class='alert-text']")
    private WebElementFacade incorrectPasswordAlert;


    public PasswordPage populateCorrectPassword(String password) {
        logger.debug("Populate correct password");
        waitABit(300);
        enter(password).into(passwordInput);
        return this;
    }

    public PasswordPage populateIncorrectPassword(String password) {
        logger.debug("Populate incorrect password");
        waitABit(300);
        enter(password + Keys.ENTER).into(passwordInput);
        return this;
    }

    public CallPage clickLogInButton() {
        logger.debug("Click on next button");
        logInButton.waitUntilClickable().then().click();
        return new CallPage(getDriver());
    }

    public boolean checkIfValidationForIncorrectPasswordAppears() {
        return incorrectPasswordAlert.isPresent();
    }

    public String getTextFromValidationAlertForIncorrectPassword() {
        return incorrectPasswordAlert.getText();
    }

}
