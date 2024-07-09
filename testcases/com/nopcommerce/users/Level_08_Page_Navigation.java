package com.nopcommerce.users;

import commons.BaseTest;
import commons.PageGenaratorManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pageObject.users.*;

public class Level_08_Page_Navigation extends BaseTest {

    private WebDriver driver;

    private HomePageObject homePage;
    private RegisterPageObject registerPage;
    private LoginPageObject loginPage;
    private CustomerPageObject customerPage;
    private AddressesPageObject addressesPage;
    private DownloadableProductsPageObject downloadablePage;
    private RewardPointsPageObject rewardPointsPage;

    @Parameters({"browser", "userUrl"})
    @BeforeClass
    public void beforeClass(String browserName, String url) {
        driver = getBrowserDriver(browserName, url);

        homePage = PageGenaratorManager.getHonePage(driver);
    }

    @Test
    public void User_01_Register() {

        registerPage = homePage.clickRegisterLink();
        registerPage.clearFirstNameTextbox();
        registerPage.clearLastNameTextbox();
        registerPage.clearEmailTextbox();
        registerPage.clearPasswordTextbox();
        registerPage.clearConfirmPasswordTextbox();

        String emailAddress = getEmailAddress();

        registerPage.sendKeyToFirstNameTextbox("John");
        registerPage.sendKeyToLastNameTextbox("Wick");
        registerPage.sendKeyToEmailTextbox(emailAddress);
        registerPage.sendKeyToPasswordTextbox("123456");
        registerPage.sendKeyToConfirmPasswordTextbox("123456");

        registerPage.clickRegisterButton();

        Assert.assertTrue(registerPage.isRegisterSuccessMessageDisplayed());
        Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

        homePage = registerPage.clickContinueButton();

        homePage.clickLogOutLink();

        loginPage = homePage.clickLoginLink();
        loginPage.sendKeyToEmailTextbox(emailAddress);
        loginPage.sendKeyToPasswordTextbox("123456");
        homePage = loginPage.clickLoginButton();

        customerPage = homePage.clickMyAccountLink();

        Assert.assertEquals(customerPage.getFirstNameAttributeValue("value"), "John");
        Assert.assertEquals(customerPage.getLastNameAttributeValue("value"), "Wick");
        Assert.assertEquals(customerPage.getEmailAttributeValue("value"), emailAddress);
    }

    @Test
    public void User_02_Switch_Multiple_Page(){
        // Các page không thuộc SideBar sẽ không gọi được
        // Các page thuộc SideBar sẽ gọi các hàm mở trong SideBar dùng được

        // Customer info -> Downloadable products
        downloadablePage = customerPage.openDownloadablePage();

        // Downloadable products -> Addresses
        addressesPage = downloadablePage.openAddressesPage();

        // Addresses -> Reward points
        rewardPointsPage = addressesPage.openRewardPointsPage();

        // Reward points -> Customer info
        customerPage = rewardPointsPage.openCustomerInfoPage();

        // Customer info -> Addresses
        addressesPage = customerPage.openAddressesPage();

        // Addresses -> Downloadable products
        downloadablePage = addressesPage.openDownloadablePage();
    }

    @AfterClass
    public void afterClass() {
        quitBrowserDriver();
    }

}
