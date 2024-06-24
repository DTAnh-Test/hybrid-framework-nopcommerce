package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePageObject extends BasePageFactory {
    // Chứa những action của page: click/ select/getText/...
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@class='ico-register']")
    WebElement registerLink;

    @FindBy(xpath = "//a[@class='ico-account']")
    WebElement myAccountLink;

    @FindBy(css = "a.ico-login")
    WebElement loginLink;

    @FindBy(css = "a.ico-logout")
    WebElement logoutLink;

    public void clickRegisterLink() {
        waitForListElementClickAble(driver, registerLink);
        clickToElement(driver, registerLink);
    }

    public void clickMyAccountLink() {
        waitForListElementClickAble(driver, myAccountLink);
        clickToElement(driver, myAccountLink);
    }

    public void clickLoginLink() {
        waitForListElementClickAble(driver, loginLink);
        clickToElement(driver, loginLink);
    }

    public void clickLogOutLink() {
        waitForListElementClickAble(driver, logoutLink);
        clickToElement(driver, logoutLink);
    }
}
