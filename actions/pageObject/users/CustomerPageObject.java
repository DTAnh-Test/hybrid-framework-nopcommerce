package pageObject.users;

import org.openqa.selenium.WebDriver;
import pageUis.users.CustomerPageUi;

public class CustomerPageObject extends SideBarMyAccountPageObject {
    private WebDriver driver;

    public CustomerPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    public String getFirstNameAttributeValue(String attributeName) {
        waitForElementVisible(driver, CustomerPageUi.FIRST_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUi.FIRST_NAME_TEXTBOX, attributeName);
    }

    public String getLastNameAttributeValue(String attributeName) {
        waitForElementVisible(driver, CustomerPageUi.LAST_NAME_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUi.LAST_NAME_TEXTBOX, attributeName);
    }

    public String getEmailAttributeValue(String attributeName) {
        waitForElementVisible(driver, CustomerPageUi.EMAIL_TEXTBOX);
        return getElementAttribute(driver, CustomerPageUi.EMAIL_TEXTBOX, attributeName);
    }

}
