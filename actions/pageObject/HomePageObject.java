package pageObject;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUis.HomePageUi;

public class HomePageObject extends BasePage {
    // Chứa những action của page: click/ select/getText/...
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public RegisterPageObject clickRegisterLink() {
        waitForListElementClickAble(driver, HomePageUi.REGISTER_LINK);
        clickToElement(driver, HomePageUi.REGISTER_LINK);
        return PageGenaratorManager.getRegisterPage(driver);
    }

    public CustomerPageObject clickMyAccountLink() {
        waitForListElementClickAble(driver, HomePageUi.MY_ACCOUNT_LINK);
        clickToElement(driver, HomePageUi.MY_ACCOUNT_LINK);
        return PageGenaratorManager.getCustomerPage(driver);
    }

    public LoginPageObject clickLoginLink() {
        waitForListElementClickAble(driver, HomePageUi.LOGIN_LINK);
        clickToElement(driver, HomePageUi.LOGIN_LINK);
        return PageGenaratorManager.getLoginPage(driver);
    }

    public void clickLogOutLink() {
        waitForListElementClickAble(driver, HomePageUi.LOG_OUT_LINK);
        clickToElement(driver, HomePageUi.LOG_OUT_LINK);
    }
}
