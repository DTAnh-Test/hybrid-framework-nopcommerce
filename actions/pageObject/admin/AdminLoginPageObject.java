package pageObject.admin;

import commons.BasePage;
import commons.PageGenaratorManager;
import org.openqa.selenium.WebDriver;
import pageUis.admin.AdminLoginPageUi;

public class AdminLoginPageObject extends BasePage {
    WebDriver driver;

    public AdminLoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void inputToEmailTextbox(String emailAddress) {
        waitForElementVisible(driver, AdminLoginPageUi.ADMIN_EMAIL_TEXTBOX);
        clearToElement(driver, AdminLoginPageUi.ADMIN_EMAIL_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUi.ADMIN_EMAIL_TEXTBOX, emailAddress);
    }

    public void inputToPasswordTextbox(String password) {
        waitForElementVisible(driver, AdminLoginPageUi.ADMIN_PASSWORD_TEXTBOX);
        clearToElement(driver, AdminLoginPageUi.ADMIN_PASSWORD_TEXTBOX);
        sendkeyToElement(driver, AdminLoginPageUi.ADMIN_PASSWORD_TEXTBOX, password);
    }

    public void clickToLoginButton() {
        waitForListElementClickAble(driver, AdminLoginPageUi.ADMIN_BUTTON_LOGIN);
        clickToElement(driver, AdminLoginPageUi.ADMIN_BUTTON_LOGIN);
    }

    public AdminDashboardPageObject loginAsAdmin(String emailAddress, String password){
        inputToEmailTextbox(emailAddress);
        inputToPasswordTextbox(password);
        clickToLoginButton();
        return PageGenaratorManager.getAdminDashboardPage(driver);
    }
}
