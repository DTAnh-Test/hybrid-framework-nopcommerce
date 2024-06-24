package pageFactory;

import commons.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageUis.LoginPageUi;

public class LoginPageObject extends BasePageFactory {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//input[@class='email']")
    WebElement emailTextbox;

    @FindBy(xpath = "//input[@class='password']")
    WebElement passwordTextbox;

    @FindBy(xpath = "//button[@class='button-1 login-button']")
    WebElement loginButton;

    public void sendKeyToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, emailTextbox);
        sendkeyToElement(driver, emailTextbox, emailAddress);
    }

    public void sendKeyToPasswordTextbox(String content) {
        waitForElementVisible(driver, passwordTextbox);
        sendkeyToElement(driver, passwordTextbox, content);
    }

    public void clickLoginButton() {
        waitForListElementClickAble(driver, loginButton);
        clickToElement(driver, loginButton);
    }
}
