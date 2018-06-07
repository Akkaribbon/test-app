package pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;


public class MainPage extends PageObject {

    public MainPage(WebDriver driver) {
        super(driver);
    }


    @FindBy(xpath = "//div[@class='join-us']/a[@data-modal-id='expert']")
    private WebElementFacade signInButton;

    public String getCurrentUrl() {
        signInButton.withTimeoutOf(5, TimeUnit.SECONDS).waitUntilPresent();
        return getDriver().getCurrentUrl();
    }
}
