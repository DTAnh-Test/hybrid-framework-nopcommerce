package pageObject;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import pageUis.LoginPageUi;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void sendKeyToEmailTextbox(String content) {
        waitForElementVisible(driver, LoginPageUi.EMAIL_TEXTBOX);
        sendkeyToElement(driver, LoginPageUi.EMAIL_TEXTBOX, content);
    }

    public void sendKeyToPasswordTextbox(String content) {
        waitForElementVisible(driver, LoginPageUi.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, LoginPageUi.PASSWORD_TEXTBOX, content);
    }

    public void clickLoginButton() {
        waitForListElementClickAble(driver, LoginPageUi.LOGIN_BUTTON);
        clickToElement(driver, LoginPageUi.LOGIN_BUTTON);
    }
}
