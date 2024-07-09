package pageObject.users;

import commons.BasePage;
import commons.PageGenaratorManager;
import org.openqa.selenium.WebDriver;
import pageUis.users.RegisterPageUi;

public class RegisterPageObject extends BasePage {
    private WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegisterButton() {
        waitForListElementClickAble(driver, RegisterPageUi.REGISTER_BUTTON);
        clickToElement(driver, RegisterPageUi.REGISTER_BUTTON);
    }

    public String getFirstNameErrorMessage() {
        waitForElementVisible(driver, RegisterPageUi.FIRST_NAME_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUi.FIRST_NAME_ERROR_MESSAGE);
    }

    public String getLastNameErrorMessage() {
        waitForElementVisible(driver, RegisterPageUi.LAST_NAME_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUi.LAST_NAME_ERROR_MESSAGE);
    }

    public String getEmailErrorMessage() {
        waitForElementVisible(driver, RegisterPageUi.EMAIL_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUi.EMAIL_ERROR_MESSAGE);
    }

    public String getConfirmPasswordErrorMessage() {
        waitForListElementVisible(driver, RegisterPageUi.CONFIRM_PASSWORD_ERROR_MESSAGE);
        return getElementText(driver, RegisterPageUi.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    public void sendKeyToFirstNameTextbox(String content) {
        waitForElementVisible(driver, RegisterPageUi.FIRST_NAME_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUi.FIRST_NAME_TEXTBOX, content);
    }

    public void sendKeyToLastNameTextbox(String content) {
        waitForElementVisible(driver, RegisterPageUi.LAST_NAME_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUi.LAST_NAME_TEXTBOX, content);
    }

    public void sendKeyToEmailTextbox(String content) {
        waitForElementVisible(driver, RegisterPageUi.EMAIL_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUi.EMAIL_TEXTBOX, content);
    }

    public void sendKeyToPasswordTextbox(String content) {
        waitForElementVisible(driver, RegisterPageUi.PASSWORD_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUi.PASSWORD_TEXTBOX, content);
    }

    public void sendKeyToConfirmPasswordTextbox(String content) {
        waitForElementVisible(driver, RegisterPageUi.CONFIRM_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, RegisterPageUi.CONFIRM_PASSWORD_TEXTBOX, content);
    }

    public boolean isEmailErrorMessageDisplayed() {
        waitForListElementVisible(driver, RegisterPageUi.EMAIL_ERROR_MESSAGE);
        return isElementDisplayed(driver, RegisterPageUi.EMAIL_ERROR_MESSAGE);
    }

    public void clearFirstNameTextbox() {
        waitForListElementVisible(driver, RegisterPageUi.FIRST_NAME_TEXTBOX);
        clearToElement(driver, RegisterPageUi.FIRST_NAME_TEXTBOX);
    }

    public void clearLastNameTextbox() {
        waitForListElementVisible(driver, RegisterPageUi.LAST_NAME_TEXTBOX);
        clearToElement(driver, RegisterPageUi.LAST_NAME_TEXTBOX);
    }

    public void clearEmailTextbox() {
        waitForListElementVisible(driver, RegisterPageUi.EMAIL_TEXTBOX);
        clearToElement(driver, RegisterPageUi.EMAIL_TEXTBOX);
    }

    public void clearPasswordTextbox() {
        waitForListElementVisible(driver, RegisterPageUi.PASSWORD_TEXTBOX);
        clearToElement(driver, RegisterPageUi.PASSWORD_TEXTBOX);
    }

    public void clearConfirmPasswordTextbox() {
        waitForListElementVisible(driver, RegisterPageUi.CONFIRM_PASSWORD_TEXTBOX);
        clearToElement(driver, RegisterPageUi.CONFIRM_PASSWORD_TEXTBOX);
    }

    public boolean isConfirmPasswordErrorMessageDisplayed() {
        waitForListElementVisible(driver, RegisterPageUi.CONFIRM_PASSWORD_ERROR_MESSAGE);
        return isElementDisplayed(driver, RegisterPageUi.CONFIRM_PASSWORD_ERROR_MESSAGE);
    }

    public boolean isRegisterSuccessMessageDisplayed() {
        waitForElementVisible(driver, RegisterPageUi.REGISTER_SUCCESS_MESSAGE);
        return isElementDisplayed(driver, RegisterPageUi.REGISTER_SUCCESS_MESSAGE);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, RegisterPageUi.REGISTER_SUCCESS_MESSAGE);
        return getElementText(driver, RegisterPageUi.REGISTER_SUCCESS_MESSAGE);
    }

    public HomePageObject clickContinueButton() {
        waitForListElementClickAble(driver, RegisterPageUi.CONTINUE_BUTTON);
        clickToElement(driver, RegisterPageUi.CONTINUE_BUTTON);
        return PageGenaratorManager.getHonePage(driver);
    }
}
