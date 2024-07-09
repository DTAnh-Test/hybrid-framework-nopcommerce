package pageObject.users;

import org.openqa.selenium.WebDriver;

public class RewardPointsPageObject extends SideBarMyAccountPageObject {
    WebDriver driver;

    public RewardPointsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
