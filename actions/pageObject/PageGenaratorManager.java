package pageObject;

import org.openqa.selenium.WebDriver;

public class PageGenaratorManager {
    
    public static HomePageObject getHonePage(WebDriver driver){
        return new HomePageObject(driver);
    }

    public static LoginPageObject getLoginPage(WebDriver driver){
        return new LoginPageObject(driver);
    }

    public static RegisterPageObject getRegisterPage(WebDriver driver){
        return new RegisterPageObject(driver);
    }

    public static CustomerPageObject getCustomerPage(WebDriver driver){
        return new CustomerPageObject(driver);
    }
}
