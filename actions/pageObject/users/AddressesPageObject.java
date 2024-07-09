package pageObject.users;

import org.openqa.selenium.WebDriver;

public class AddressesPageObject extends SideBarMyAccountPageObject {
    WebDriver driver;

    public AddressesPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
