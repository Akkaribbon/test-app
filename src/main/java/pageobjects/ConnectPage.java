package pageobjects;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@DefaultUrl("https://demo.anymind.com/widget{1}")
public class ConnectPage extends PageObject {

    public ConnectPage(WebDriver driver) {
        super(driver);
    }

    private static final Logger logger = LoggerFactory.getLogger(ConnectPage.class);

    @FindBy(xpath = "//div[@class='poweredby']/p/a")
    private WebElementFacade anyMindLink;

    @FindBy(xpath = "//div[@class='handler']//a[@class='btn btn-default']")
    private WebElementFacade connectButton;


    public MainPage clickAnyMindLink() {
        logger.debug("Click on 'AnyMind.com' link on connect page");
        anyMindLink.waitUntilClickable().then().click();
        String currentHandle = getDriver().getWindowHandle();
        for(String handle : getDriver().getWindowHandles()){
            if(!handle.equals(currentHandle)){
                getDriver().switchTo().window(handle);
                break;
            }
        }
        return new MainPage(getDriver());
    }

    public PhonePage clickConnectButton() {
        logger.debug("Click on connect button");
        connectButton.waitUntilClickable().then().click();
        return new PhonePage(getDriver());
    }
}
