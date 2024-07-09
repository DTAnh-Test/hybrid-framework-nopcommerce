package pageObject.users;

import org.openqa.selenium.WebDriver;

public class DownloadableProductsPageObject extends SideBarMyAccountPageObject {
    WebDriver driver;

    public DownloadableProductsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
