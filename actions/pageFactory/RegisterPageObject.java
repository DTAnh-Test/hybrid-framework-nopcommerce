package pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPageObject extends BasePageFactory {
    private WebDriver driver;

    public RegisterPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @CacheLookup
    @FindBy(xpath = "//button[@id='register-button']")
    WebElement registerButton;

    @FindBy(xpath = "//span[@data-valmsg-for='FirstName']")
    WebElement firstNameErrorMessage;

    @FindBy(xpath = "//span[@data-valmsg-for='LastName']")
    WebElement lastNameErrorMessage;

    @FindBy(xpath = "//span[@data-valmsg-for='Email']")
    WebElement emailErrorMessage;

    @FindBy(xpath = "//span[@data-valmsg-for='ConfirmPassword']")
    WebElement confirmPasswordErrorMessage;

    @FindBy(xpath = "//input[@id='FirstName']")
    WebElement firstNameTextbox;

    @FindBy(xpath = "//input[@id='LastName']")
    WebElement lastNameTextbox;

    @FindBy(xpath = "//input[@id='Email']")
    WebElement emailTextbox;

    @FindBy(xpath = "//input[@id='Password']")
    WebElement passwordTextbox;

    @FindBy(xpath = "//input[@id='ConfirmPassword']")
    WebElement confirmPasswordTextbox;

    @FindBy(xpath = "//div[@class='result']")
    WebElement registerSuccessMessage;

    @FindBy(xpath = "//a[@class='button-1 register-continue-button']")
    WebElement continueButton;

    public void clickRegisterButton() {
        waitForListElementClickAble(driver, registerButton);
        clickToElement(driver, registerButton);
    }

    public String getFirstNameErrorMessage() {
        waitForElementVisible(driver, firstNameErrorMessage);
        return getElementText(driver, firstNameErrorMessage);
    }

    public String getLastNameErrorMessage() {
        waitForElementVisible(driver, lastNameErrorMessage);
        return getElementText(driver, lastNameErrorMessage);
    }

    public String getEmailErrorMessage() {
        waitForElementVisible(driver, emailErrorMessage);
        return getElementText(driver, emailErrorMessage);
    }

    public String getConfirmPasswordErrorMessage() {
        waitForListElementVisible(driver, confirmPasswordErrorMessage);
        return getElementText(driver, confirmPasswordErrorMessage);
    }

    public void sendKeyToFirstNameTextbox(String content) {
        waitForElementVisible(driver, firstNameTextbox);
        sendkeyToElement(driver, firstNameTextbox, content);
    }

    public void sendKeyToLastNameTextbox(String content) {
        waitForElementVisible(driver, lastNameTextbox);
        sendkeyToElement(driver, lastNameTextbox, content);
    }

    public void sendKeyToEmailTextbox(String content) {
        waitForElementVisible(driver, emailTextbox);
        sendkeyToElement(driver, emailTextbox, content);
    }

    public void sendKeyToPasswordTextbox(String content) {
        waitForElementVisible(driver, passwordTextbox);
        sendkeyToElement(driver, passwordTextbox, content);
    }

    public void sendKeyToConfirmPasswordTextbox(String content) {
        waitForElementVisible(driver, confirmPasswordTextbox);
        sendkeyToElement(driver, confirmPasswordTextbox, content);
    }

    public boolean isEmailErrorMessageDisplayed() {
        waitForListElementVisible(driver, emailErrorMessage);
        return isElementDisplayed(driver, emailErrorMessage);
    }

    public void clearFirstNameTextbox() {
        waitForListElementVisible(driver, firstNameTextbox);
        clearToElement(driver, firstNameTextbox);
    }

    public void clearLastNameTextbox() {
        waitForListElementVisible(driver, lastNameTextbox);
        clearToElement(driver, lastNameTextbox);
    }

    public void clearEmailTextbox() {
        waitForListElementVisible(driver, emailTextbox);
        clearToElement(driver, emailTextbox);
    }

    public void clearPasswordTextbox() {
        waitForListElementVisible(driver, passwordTextbox);
        clearToElement(driver, passwordTextbox);
    }

    public void clearConfirmPasswordTextbox() {
        waitForListElementVisible(driver, confirmPasswordTextbox);
        clearToElement(driver, confirmPasswordTextbox);
    }

    public boolean isConfirmPasswordErrorMessageDisplayed() {
        waitForListElementVisible(driver, confirmPasswordErrorMessage);
        return isElementDisplayed(driver, confirmPasswordErrorMessage);
    }

    public boolean isRegisterSuccessMessageDisplayed() {
        waitForElementVisible(driver, registerSuccessMessage);
        return isElementDisplayed(driver, registerSuccessMessage);
    }

    public String getRegisterSuccessMessage() {
        waitForElementVisible(driver, registerSuccessMessage);
        return getElementText(driver, registerSuccessMessage);
    }

    public void clickContinueButton() {
        waitForListElementClickAble(driver, continueButton);
        clickToElement(driver, continueButton);
    }
}
