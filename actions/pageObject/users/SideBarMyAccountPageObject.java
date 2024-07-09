package pageObject.users;

import commons.BasePage;
import commons.PageGenaratorManager;
import org.openqa.selenium.WebDriver;
import pageUis.users.SideBarMyAccountPageUi;

public class SideBarMyAccountPageObject extends BasePage {
    WebDriver driver;

    public SideBarMyAccountPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public DownloadableProductsPageObject openDownloadablePage() {
        waitForListElementClickAble(driver, SideBarMyAccountPageUi.DOWNLOADABLE_PAGE_LINK);
        clickToElement(driver, SideBarMyAccountPageUi.DOWNLOADABLE_PAGE_LINK);
        return PageGenaratorManager.getDownloadablePage(driver);
    }

    public AddressesPageObject openAddressesPage() {
        waitForListElementClickAble(driver, SideBarMyAccountPageUi.ADDRESSES_PAGE_LINK);
        clickToElement(driver, SideBarMyAccountPageUi.ADDRESSES_PAGE_LINK);
        return PageGenaratorManager.getAddressesPage(driver);
    }

    public RewardPointsPageObject openRewardPointsPage() {
        waitForListElementClickAble(driver, SideBarMyAccountPageUi.REWARDPOINTS_PAGE_LINK);
        clickToElement(driver, SideBarMyAccountPageUi.REWARDPOINTS_PAGE_LINK);
        return PageGenaratorManager.getRewardPointPage(driver);
    }


    public CustomerPageObject openCustomerInfoPage() {
        waitForListElementClickAble(driver, SideBarMyAccountPageUi.CUSTOMER_INFO_PAGE_LINK);
        clickToElement(driver, SideBarMyAccountPageUi.CUSTOMER_INFO_PAGE_LINK);
        return PageGenaratorManager.getCustomerPage(driver);
    }
}
