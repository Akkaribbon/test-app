import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Managed;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import pageobjects.*;
import utilities.PropertiesReader;

import static net.serenitybdd.core.pages.PageObject.withParameters;


@RunWith(SerenityRunner.class)
public class ConnectionTest {

    @Managed
    private WebDriver driver;

    private ConnectPage connectPage;

    @Before
    public void startApp(){
        connectPage  = new ConnectPage(driver);
        connectPage.open(withParameters("?widgetId=j05DwctZQhU"));
    }


    @Test
    public void checkAnyMindLink() {
        MainPage mainPage = connectPage.clickAnyMindLink();

        Assert.assertTrue("Incorrect opened page after click on AnyMind.com link",
                mainPage.getCurrentUrl().contains("https://anymind.com/"));
    }

    @Test
    public void checkValidationForIncorrectPhoneNumber() {
        PhonePage phonePage = connectPage.clickConnectButton()
                .populateIncorrectPhoneNumber(PropertiesReader.getIncorrectPhone());

        Assert.assertTrue("Lack of incorrect number alert, after populate input with number not valid",
                phonePage.checkIfValidationForIncorrectNumberAppears());
        Assert.assertEquals("Incorrect alert text on not valid phone number validation",
                "Podaj swój numer telefonu. Przyślemy na niego kod do logowania.",
                phonePage.getTextFromValidationAlertForIncorrectNumber() );
    }

    @Test
    public void checkValidationForIncorrectPassword() {
        PasswordPage passwordPage = connectPage.clickConnectButton()
                .populateCorrectPhoneNumber(PropertiesReader.getPhoneNumber())
                .clickNextButton()
                .populateIncorrectPassword(PropertiesReader.getIncorrectPassword());

        Assert.assertTrue("Lack of incorrect password alert, after populate input with password not valid",
                passwordPage.checkIfValidationForIncorrectPasswordAppears());
        Assert.assertEquals("Incorrect alert text on not valid password validation",
                "Hasło niepoprawne.",
                passwordPage.getTextFromValidationAlertForIncorrectPassword());
    }

    @Test
    public void LogInToAnyMindApplication() {
        CallPage callPage = connectPage.clickConnectButton()
                .populateCorrectPhoneNumber(PropertiesReader.getPhoneNumber())
                .clickNextButton()
                .populateCorrectPassword(PropertiesReader.getPassword())
                .clickLogInButton();

        Assert.assertTrue("User is not log in", callPage.checkIfUserIsLoggedIn());
    }
}