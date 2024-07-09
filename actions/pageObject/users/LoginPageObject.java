package pageObject.users;

import commons.BasePage;
import commons.PageGenaratorManager;
import org.openqa.selenium.WebDriver;
import pageUis.users.LoginPageUi;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeyToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, LoginPageUi.EMAIL_TEXTBOX);
        sendkeyToElement(driver, LoginPageUi.EMAIL_TEXTBOX, emailAddress);
    }

    public void sendKeyToPasswordTextbox(String content) {
        waitForElementVisible(driver, LoginPageUi.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUi.PASSWORD_TEXTBOX, content);
    }

    public HomePageObject clickLoginButton() {
        waitForListElementClickAble(driver, LoginPageUi.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUi.LOGIN_BUTTON);
        return PageGenaratorManager.getHonePage(driver);
    }

    public HomePageObject loginWithUser(String emailAddress, String password){
        sendKeyToEmailTextbox(emailAddress);
        sendKeyToPasswordTextbox(password);
        clickLoginButton();
        return PageGenaratorManager.getHonePage(driver);
    }
}
