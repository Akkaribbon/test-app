package pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class CallPage extends PageObject {

    public CallPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//div[@class='account-section']/p/span[@class='link']/span[text()='WYLOGUJ']")
    private WebElementFacade logOutLink;

    @FindBy(xpath = "//div[@class='user-profile container']")
    private WebElementFacade userProfileContainer;

    public boolean checkIfUserIsLoggedIn() {
        userProfileContainer.waitUntilPresent();
        return logOutLink.withTimeoutOf(6, TimeUnit.SECONDS).isPresent();
    }



}
